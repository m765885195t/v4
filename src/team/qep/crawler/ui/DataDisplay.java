package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.server.Data;
import team.qep.crawler.server.Task;

public class DataDisplay extends JPanel implements MouseListener {
	private boolean flag = true;// true---电商 false---新闻博客

	private JLabel dataDisplay = new JLabel("Data Display");

	private JComboBox<String> selectUrl = new JComboBox<String>();  //选择url(模糊or电商)
	private JComboBox<String> selectKeyword  = new JComboBox<String>(); //选择关键字
	private JButton refresh = new JButton();// 刷新

	private String[] columnNames; // 表格列名
	private String[][] data; // 表格数据
	private DefaultTableModel taskDataSetModel;
	private JTable taskDataSet = new JTable();
	private JScrollPane taskDataJSP = new JScrollPane(taskDataSet); // 未终止的任务数据集
	
	
	public DataDisplay() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(dataDisplay);
		this.add(selectUrl);
		this.add(selectKeyword);
		this.add(refresh);
		this.add(taskDataJSP);
	}

	private void loadingData() {// 装载数据
//		for(String[] str:Data.getALLUrlSet()){//所有任务集q
//			selectUrl.addItem(str[0]);
//		}
		
		//初始得到第一个url的关键字
//		for(String str:Task.getKeyWords(Constant.KeyValue.get("Complete"),selectUrl.getSelectedItem().toString())){
//			selectKeyword.addItem(str);
//		}
		
		columnNames = Constant.E_CommerceCcolumnNames;
		data = new String[0][];//得到数据
		taskDataSetModel = new DefaultTableModel(data, columnNames);
		taskDataSet.setModel(taskDataSetModel);
	}

	private void Init() {
		Init.initJLable(dataDisplay, "dataDisplay");

		Init.initJComboBox(selectUrl, "selectUrl");
		Init.initJComboBox(selectKeyword, "selectKeyword");
		Init.initJButton(refresh, "refresh");

		Init.initJTable(taskDataSet, "taskDataSet");
		Init.initJScrollPane(taskDataJSP, "taskDataJSP");
	}

	private void setBounds() {
		dataDisplay.setBounds(380, 0, 300, 40);
		selectUrl.setBounds(130, 70, 200, 33);
		selectKeyword.setBounds(430,70, 150, 33);
		refresh.setBounds(700, 65, 150, 40);
		taskDataJSP.setBounds(30, 140, 914, 430);
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));

		dataDisplay.setFont(new Font("微软雅黑", 0, 26));
		dataDisplay.setForeground(new Color(0, 255, 255));
		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("refresh")));
	}

	private void listener() {
		//监听选择框------选择url
		selectUrl.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				selectKeyword.removeAllItems();
				selectKeyword.addItem("");
//				for(String str:Task.getKeyWords(Constant.UIKeyValue.get("stop"),selectUrl.getSelectedItem().toString())){
//					selectKeyword.addItem(str);
//				}
				if(selectUrl.getSelectedIndex()<Constant.division){//设置表格列名 电商or新闻博客
					flag=true;
				}else{
					flag=false;
				}
			}
		});
		selectUrl.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				selectKeyword.removeAllItems();
				selectKeyword.addItem("");
//				for(String str:Task.getKeyWords(Constant.UIKeyValue.get("stop"),selectUrl.getSelectedItem().toString())){
//					selectKeyword.addItem(str);
//				}
			}
		});
		refresh.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("refresh".equals(e.getComponent().getName())) {
			String url = selectUrl.getSelectedItem().toString();
			String keyWord = selectKeyword.getSelectedItem().toString();
			
			data=Task.getUrlData(url,keyWord);
			if(flag){
				columnNames = Constant.E_CommerceCcolumnNames;
			}else{
				columnNames = Constant.BlogNewsCcolumnNames;
			}
			
			taskDataSetModel = new DefaultTableModel(data,columnNames);
			taskDataSet.setModel(taskDataSetModel);
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(255, 255, 255));
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(150, 150, 150));
		}
	}

}
