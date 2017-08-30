package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;

import team.qep.crawler.server.Data;
import team.qep.crawler.util.Constant;
import team.qep.crawler.util.StringManipulation;

public class DataMonitoring extends JPanel implements MouseListener {
	private JLabel dataMonitoring = new JLabel("实   时   监    控");
	private String url=null;
	private String keyWord=null;
	
	private Timer timer =null;
	private JComboBox<String> runTask = new JComboBox<String>(); //运行中的任务
	private JComboBox<String> selectKeyword  = new JComboBox<String>(); //选择关键字
	private JButton savePicture = new JButton();// 保存当前进度图
	private JButton dataMonitoringRefresh = new JButton();// 刷新
	private JFreeChart lineChart = CrawlerChart.getLineChart();
	private JPanel lineChartJP=new ChartPanel(lineChart);

	public DataMonitoring() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(dataMonitoring);
		this.add(runTask);
		this.add(selectKeyword);
		this.add(savePicture);
		this.add(dataMonitoringRefresh);
		this.add(lineChartJP);
	}

	private void loadingData() {// 装载数据
		runTask.removeAllItems();//更新任务列表
		String[] urlSet=StringManipulation.oneDuplicateRemoval(StringManipulation.toOneDimensionalArrays(Data.getALLUrlSet()));
		for(String str:urlSet){
			runTask.addItem(str);
		}
		selectKeyword.removeAllItems();
		if(runTask.getItemCount()>0){
			for(String str:Data.getKeyWords(Data.getRunUrlSet(),runTask.getSelectedItem().toString())){
				selectKeyword.addItem(str);
			}
		}
	}
	private void Init() {
		Init.initJLable(dataMonitoring, "dataMonitoring");

		Init.initJComboBox(runTask, "runTask");
		Init.initJComboBox(selectKeyword, "selectKeyword");

		Init.initJButton(savePicture, "savePicture");
		Init.initJButton(dataMonitoringRefresh, "dataMonitoringRefresh");
	}

	private void setBounds() {
		dataMonitoring.setBounds(320, 0, 300, 35);
		runTask.setBounds(60, 60, 200, 33);
		selectKeyword.setBounds(330, 60, 180, 33);
		savePicture.setBounds(590, 56, 120, 40);
		dataMonitoringRefresh.setBounds(784, 56, 120, 40);
		lineChartJP.setBounds(15, 125, 944, 440);
	}

	private void setColour() {
		this.setBackground(Theme.Panel5);

		dataMonitoring.setFont(Theme.TitleFont);
		dataMonitoring.setForeground(Theme.TitleColor);
		savePicture.setBackground(Theme.ButtonColor);
		savePicture.setIcon(Constant.getIcon("savePicture"));
		dataMonitoringRefresh.setBackground(Theme.ButtonColor);
		dataMonitoringRefresh.setIcon(Constant.getIcon("dataMonitoringRefresh"));
	}

	private void listener() {
		runTask.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				selectKeyword.removeAllItems();
				if(runTask.getItemCount()>0){
					//初始得到第一个url的关键字
					for(String str:Data.getKeyWords(Data.getRunUrlSet(),runTask.getSelectedItem().toString())){
						selectKeyword.addItem(str);
					}
				}
			}
		});
		savePicture.addMouseListener(this);
		dataMonitoringRefresh.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("savePicture".equals(e.getComponent().getName())) {
			if(CrawlerChart.savePicture(lineChart)){
				new Promptinformation(null, "当前进度图图片保存成功(./image/schedule/)", Constant.KeyValue.get("Info"));
			}
		} else if ("dataMonitoringRefresh".equals(e.getComponent().getName())) {
			if(timer!=null){//取消定时任务
				timer.cancel();
				timer=null;
			}
			if(runTask.getItemCount()>0){//存在任务url
				url=runTask.getSelectedItem().toString();//得到选择
				keyWord=selectKeyword.getSelectedItem().toString();
				
				if(!Constant.RefreshInterval.equals("0")){//更新数据
					timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						public void run() {
							((CategoryPlot) lineChart.getPlot()).setDataset(CrawlerChart.getLineDataSet(url,keyWord));
						}
					},0,Integer.valueOf(Constant.RefreshInterval)*1000);
				}else{
					((CategoryPlot) lineChart.getPlot()).setDataset(CrawlerChart.getLineDataSet(url,keyWord));
				}
			}
			loadingData();//更新任务列表
		}
	}
	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("savePicture".equals(e.getComponent().getName())) {
			savePicture.setBackground(Color.WHITE);
		} else if ("dataMonitoringRefresh".equals(e.getComponent().getName())) {
			dataMonitoringRefresh.setBackground(Color.WHITE);
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("savePicture".equals(e.getComponent().getName())) {
			savePicture.setBackground(Theme.ButtonColor);
		} else if ("dataMonitoringRefresh".equals(e.getComponent().getName())) {
			dataMonitoringRefresh.setBackground(Theme.ButtonColor);
		}
	}

}
