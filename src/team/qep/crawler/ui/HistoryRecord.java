package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import team.qep.crawler.basic.Constant;

public class HistoryRecord extends JPanel implements MouseListener {
	private JLabel historyRecord = new JLabel("History  Record");

	private String[] columnNames; // 表格列名
	private String[][] data; // 表格数据
	private DefaultTableModel taskDataSetModel;
	private JTable taskDataSet = new JTable();
	private JScrollPane taskDataJSP = new JScrollPane(taskDataSet); // 历史人任务记录
	
	private JPanel pieChart=CrawlerChart.createPieChart();// 历史任务记录
	private JButton refresh = new JButton();// 刷新
	private JButton deleteData = new JButton();// 删除数据

	public HistoryRecord() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(historyRecord);
		this.add(taskDataJSP);
		this.add(pieChart);
		this.add(refresh);
		this.add(deleteData);
	}

	private void loadingData() {// 装载数据
		columnNames = Constant.HistoricalTaskCcolumnNames;
		data = new String[0][];//得到数据
		taskDataSetModel = new DefaultTableModel(data, columnNames);
		taskDataSet.setModel(taskDataSetModel);
	}

	private void Init() {
		Init.initJLable(historyRecord, "historyRecord");

		Init.initJTable(taskDataSet, "taskDataSet");
		Init.initJScrollPane(taskDataJSP, "taskDataJSP");
		
		Init.initJButton(refresh, "refresh");
		Init.initJButton(deleteData, "deleteData");

	}

	private void setBounds() {
		historyRecord.setBounds(380, 0, 300, 40);
		
		taskDataJSP.setBounds(30, 50, 420, 520);
		pieChart.setBounds(500, 50, 444, 450);
		refresh.setBounds(500, 530, 170, 42);
		deleteData.setBounds(770, 530, 170, 42);
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));

		historyRecord.setFont(new Font("微软雅黑", 0, 26));
		historyRecord.setForeground(new Color(0, 255, 255));
		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("refresh")));
		deleteData.setBackground(new Color(150, 150, 150));
		deleteData.setIcon(new ImageIcon(Constant.getIcon("deleteData")));
	}

	private void listener() {
		refresh.addMouseListener(this);
		deleteData.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("refresh".equals(e.getComponent().getName())) {
			
		}else if ("deleteData".equals(e.getComponent().getName())) {
			
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(255, 255, 255));
		}else if ("deleteData".equals(e.getComponent().getName())) {
			deleteData.setBackground(new Color(255, 255, 255));
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(150, 150, 150));
		}else if ("deleteData".equals(e.getComponent().getName())) {
			deleteData.setBackground(new Color(150, 150, 150));
		}
	}

}
