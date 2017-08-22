package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import team.qep.crawler.util.Promptinformation;
import team.qep.crawler.util.StringManipulation;

public class DataMonitoring extends JPanel implements MouseListener {
	private JLabel dataDisplay = new JLabel("数  据  监  控");

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

		this.add(dataDisplay);
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
		}
		
		//定时刷新数据
//		new Timer().schedule(new TimerTask() {
//			public void run() {
//			   ((CategoryPlot) lineChart.getPlot()).setDataset(CrawlerChart.getLineDataSet());
//			}
//		},0,2000); //数据刷新频率 --- 可设置
	}

	private void Init() {
		Init.initJLable(dataDisplay, "dataDisplay");

		Init.initJComboBox(runTask, "runTask");
		Init.initJComboBox(selectKeyword, "selectKeyword");

		Init.initJButton(savePicture, "savePicture");
		Init.initJButton(refresh, "refresh");
		Init.initJButton(refresh, "refresh");
	}

	private void setBounds() {
		dataDisplay.setBounds(320, 0, 300, 40);
		runTask.setBounds(60, 64, 200, 33);
		selectKeyword.setBounds(330, 64, 180, 33);
		savePicture.setBounds(590, 60, 120, 40);
		refresh.setBounds(784, 60, 120, 40);
		lineChartJP.setBounds(15, 130, 944, 430);
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));

		dataDisplay.setFont(new Font("微软雅黑", 0, 26));
		dataDisplay.setForeground(new Color(0, 255, 255));
		savePicture.setBackground(new Color(150, 150, 150));
		savePicture.setIcon(new ImageIcon(Constant.getIcon("savePicture")));
		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("refresh")));
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
			if(runTask.getItemCount()>0){
				String url=runTask.getSelectedItem().toString();
				String keyWord=selectKeyword.getSelectedItem().toString();
				((CategoryPlot) lineChart.getPlot()).setDataset(CrawlerChart.getLineDataSet(url,keyWord));
				runTask.removeAllItems();
				String[] urlSet=StringManipulation.oneDuplicateRemoval(StringManipulation.toOneDimensionalArrays(Data.getALLUrlSet()));
				for(String str:urlSet){//所有任务集
					runTask.addItem(str);
				}
				runTask.setSelectedItem(url);
				//初始得到第一个url的关键字
				selectKeyword.removeAllItems();
				for(String str:Data.getKeyWords(Data.getALLUrlSet(),runTask.getSelectedItem().toString())){
					selectKeyword.addItem(str);
				}
				selectKeyword.setSelectedItem(keyWord);
			}
		}
	}
	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("savePicture".equals(e.getComponent().getName())) {
			savePicture.setBackground(new Color(255, 255, 255));
		} else if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(255, 255, 255));
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("savePicture".equals(e.getComponent().getName())) {
			savePicture.setBackground(new Color(150, 150, 150));
		} else if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(150, 150, 150));
		}
	}

}
