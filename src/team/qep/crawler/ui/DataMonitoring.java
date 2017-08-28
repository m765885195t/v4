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
	private JLabel dataMonitoring = new JLabel("数  据  监  控");
	private String url=null;
	private String keyWord=null;
	
	private Timer timer =null;
	private JComboBox<String> runTask = new JComboBox<String>(); //运行中的任务
	private JComboBox<String> selectKeyword  = new JComboBox<String>(); //选择关键字
	private JButton savePicture = new JButton();// 保存当前进度图
	private JButton refresh = new JButton();// 刷新
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
		this.add(refresh);
		this.add(lineChartJP);
	}

	private void loadingData() {// 装载数据
		//去重后的url集-----展示的数据为所有历史任务和正在运行的任务
		String[] urlSet=StringManipulation.oneDuplicateRemoval(StringManipulation.toOneDimensionalArrays(Data.getRunUrlSet()));
		for(String str:urlSet){//所有任务集
			runTask.addItem(str);
		}
		if(runTask.getItemCount()>0){
			//初始得到第一个url的关键字
			for(String str:Data.getKeyWords(Data.getRunUrlSet(),runTask.getSelectedItem().toString())){
				selectKeyword.addItem(str);
			}
			 url=runTask.getSelectedItem().toString();
			 keyWord=selectKeyword.getSelectedItem().toString();
			if(!Constant.RefreshInterval.equals("0")){
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
	}
	private void Init() {
		Init.initJLable(dataMonitoring, "dataMonitoring");

		Init.initJComboBox(runTask, "runTask");
		Init.initJComboBox(selectKeyword, "selectKeyword");

		Init.initJButton(savePicture, "savePicture");
		Init.initJButton(refresh, "refresh");
		Init.initJButton(refresh, "refresh");
	}

	private void setBounds() {
		dataMonitoring.setBounds(320, 0, 300, 40);
		runTask.setBounds(60, 64, 200, 33);
		selectKeyword.setBounds(330, 64, 180, 33);
		savePicture.setBounds(590, 60, 120, 40);
		refresh.setBounds(784, 60, 120, 40);
		lineChartJP.setBounds(15, 130, 944, 430);
	}

	private void setColour() {
		this.setBackground(Theme.PanelColor);

		dataMonitoring.setFont(Theme.TitleFont);
		dataMonitoring.setForeground(Theme.TitleColor);
		savePicture.setBackground(Theme.ButtonColor);
		savePicture.setIcon(Constant.getIcon("savePicture"));
		refresh.setBackground(Theme.ButtonColor);
		refresh.setIcon(Constant.getIcon("refresh"));
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
		refresh.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("savePicture".equals(e.getComponent().getName())) {
			if(CrawlerChart.savePicture(lineChart)){
				new Promptinformation(null, "当前进度图图片保存成功(./image/schedule/)", Constant.KeyValue.get("Info"));
			}
		} else if ("refresh".equals(e.getComponent().getName())) {
			if(timer!=null){//取消定时任务
				timer.cancel();
				timer=null;
			}
			if(runTask.getItemCount()>0){//存在任务url
				url=runTask.getSelectedItem().toString();//保留上次的状态
				keyWord=selectKeyword.getSelectedItem().toString();
				
				runTask.removeAllItems();//重新更新任务url数据
				String[] urlSet=StringManipulation.oneDuplicateRemoval(StringManipulation.toOneDimensionalArrays(Data.getALLUrlSet()));
				for(String str:urlSet){
					runTask.addItem(str);
				}
				selectKeyword.removeAllItems();//恢复默认的url(若此任务未完成)

				if(runTask.getItemCount()>0){//如果更新完后有任务url数据
					//更新关键字
					for(String str:Data.getKeyWords(Data.getRunUrlSet(),runTask.getSelectedItem().toString())){
					selectKeyword.addItem(str);
					}
					selectKeyword.setSelectedItem(keyWord);

					url=runTask.getSelectedItem().toString();//重新获取信息
					keyWord=selectKeyword.getSelectedItem().toString();
			
					if(!Constant.RefreshInterval.equals("0")){
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
			}else{//初次添加数据
				String[] urlSet=StringManipulation.oneDuplicateRemoval(StringManipulation.toOneDimensionalArrays(Data.getALLUrlSet()));
				for(String str:urlSet){//所有任务集
					runTask.addItem(str);
				}
				for(String str:Data.getKeyWords(Data.getRunUrlSet(),runTask.getSelectedItem().toString())){
					selectKeyword.addItem(str);
				}
			}
		}
	}
	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("savePicture".equals(e.getComponent().getName())) {
			savePicture.setBackground(Color.WHITE);
		} else if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(Color.WHITE);
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("savePicture".equals(e.getComponent().getName())) {
			savePicture.setBackground(Theme.ButtonColor);
		} else if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(Theme.ButtonColor);
		}
	}

}
