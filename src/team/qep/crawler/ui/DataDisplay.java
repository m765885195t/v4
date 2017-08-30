package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import team.qep.crawler.server.Data;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Constant;
import team.qep.crawler.util.ConvertJSON;
import team.qep.crawler.util.StringManipulation;

public class DataDisplay extends JPanel implements MouseListener, ActionListener {
	private boolean flag = true;// true---电商 false---新闻博客

	private JLabel dataDisplay = new JLabel("数   据   展   示");

	private JComboBox<String> selectUrl = new JComboBox<String>();  //选择url(模糊or电商)
	private JComboBox<String> selectKeyword  = new JComboBox<String>(); //选择关键字
	private JButton dataDisplayRefresh = new JButton();// 刷新
	private String[][] data;
	private DefaultTableModel ecDataModel;
	private JTable ecDataJT = new JTable();
	private JScrollPane ecDataJSP = new JScrollPane(ecDataJT); // 未终止的任务数据集
	private JTextArea bnDataJTA = new JTextArea();
	private JScrollPane bnDataJSP = new JScrollPane(bnDataJTA); //新闻博客数据视图

	public DataDisplay() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(dataDisplay);
		this.add(selectUrl);
		this.add(selectKeyword);
		this.add(dataDisplayRefresh);
		this.add(ecDataJSP);
	}

	private void loadingData() {// 装载数据
		//去重后的url集-----展示的数据为所有任务
		String[] urlSet=StringManipulation.oneDuplicateRemoval(StringManipulation.toOneDimensionalArrays(Data.getALLUrlSet()));
		for(String str:urlSet){//所有任务集
			selectUrl.addItem(str);
		}
		if(selectUrl.getItemCount()>0){
			for(String str:Data.getKeyWords(Data.getALLUrlSet(),selectUrl.getSelectedItem().toString())){
				selectKeyword.addItem(str);
			}
		}
		
		ecDataModel = new DefaultTableModel(new String[0][], Constant.E_CommerceCcolumnNames){
			public void setValueAt(Object aValue, int row, int column){	
			}
		};
		ecDataJT.setModel(ecDataModel);
	}

	private void Init() {
		Init.initJLable(dataDisplay, "dataDisplay");

		Init.initJComboBox(selectUrl, "selectUrl");
		Init.initJComboBox(selectKeyword, "selectKeyword");
		Init.initJButton(dataDisplayRefresh, "dataDisplayRefresh");

		Init.initJTable(ecDataJT, "ecDataJT");
		Init.initJScrollPane(ecDataJSP, "ecDataJSP");
		Init.initJTextArea(bnDataJTA, "bnDataJTA");
		Init.initJScrollPane(bnDataJSP, "bnDataJSP");
	}

	private void setBounds() {
		dataDisplay.setBounds(320, 0, 300, 35);
		selectUrl.setBounds(130, 60, 200, 33);
		selectKeyword.setBounds(430,60, 150, 33);
		dataDisplayRefresh.setBounds(700, 55, 150, 40);
		ecDataJSP.setBounds(20, 125, 934, 440);
		bnDataJSP.setBounds(20, 125, 934, 440);
	}

	private void setColour() {
		this.setBackground(Theme.Panel7);

		dataDisplay.setFont(Theme.TitleFont);
		dataDisplay.setForeground(Theme.TitleColor);
		dataDisplayRefresh.setBackground(Theme.ButtonColor);
		dataDisplayRefresh.setIcon(Constant.getIcon("dataDisplayRefresh"));
		bnDataJTA.setFont(Theme.Tablefont);// 设置字体格式
		bnDataJTA.setEditable(false);//屏蔽输入
		bnDataJTA.setFocusable(false);//消除光标
		bnDataJTA.setFont(Theme.Tablefont);

	}

	private void listener() {
		selectUrl.addActionListener(this);
		selectKeyword.addActionListener(this);
		dataDisplayRefresh.addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == selectUrl) {
			selectKeyword.removeAllItems();
			for(String str:Data.getKeyWords(Data.getRunUrlSet(),selectUrl.getSelectedItem().toString())){
				selectKeyword.addItem(str);
			}
		}else if(e.getSource() == selectKeyword) {
			this.remove(ecDataJSP);
			this.remove(bnDataJSP);
			String url = selectUrl.getSelectedItem().toString();
			String keyWord = selectKeyword.getSelectedItem().toString();
			data=Data.getUrlData(url,keyWord);
			
			if(Constant.SupportFuzzyUrl.indexOf(url)<Constant.division){
//				String str=data[selectedRow][2].replace("'", "\"").replace("u\"", "\"");
//				String[][] data = StringManipulation.toTwoDimensionalArrays(ConvertJSON.toStringArray(str),6);
		
				ecDataModel = new DefaultTableModel(data, Constant.E_CommerceCcolumnNames){
					public void setValueAt(Object aValue, int row, int column){}
				};
				ecDataJT.setModel(ecDataModel);
				this.add(ecDataJSP);
			}else{
//				bnDataJTA.setText(UrlData[selectedRow][2].replace("\n", System.getProperty("line.separator")));
				bnDataJTA.setCaretPosition(0);
				this.add(bnDataJSP);
			}
			this.updateUI();
		}
	}
	public void mouseClicked(MouseEvent e) {// 单击
		if ("dataDisplayRefresh".equals(e.getComponent().getName())) {
			selectUrl.removeAllItems();
			String[] urlSet=StringManipulation.oneDuplicateRemoval(StringManipulation.toOneDimensionalArrays(Data.getALLUrlSet()));
			for(String str:urlSet){//所有任务集
				selectUrl.addItem(str);
			}
			selectKeyword.removeAllItems();
			if(selectUrl.getItemCount()>0){
				for(String str:Data.getKeyWords(Data.getALLUrlSet(),selectUrl.getSelectedItem().toString())){
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
		if ("dataDisplayRefresh".equals(e.getComponent().getName())) {
			dataDisplayRefresh.setBackground(Color.WHITE);
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("dataDisplayRefresh".equals(e.getComponent().getName())) {
			dataDisplayRefresh.setBackground(Theme.ButtonColor);
		}
	}


}
