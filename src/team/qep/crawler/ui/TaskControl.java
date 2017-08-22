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

import team.qep.crawler.server.Data;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Constant;
import team.qep.crawler.util.Promptinformation;

public class TaskControl extends JPanel implements MouseListener {

	private JLabel task = new JLabel("任  务  中  心");

	private String[] columnNames; // 表格列名
	private String[][] data; // 表格数据
	private DefaultTableModel taskDataSetModel;
	private JTable taskDataSet = new JTable();
	private JScrollPane taskDataJSP = new JScrollPane(taskDataSet); // 未终止的任务数据集
	
	private JButton refresh = new JButton();// 刷新
	private JButton startTask = new JButton();// 开始任务
	private JButton suspendTask = new JButton();// 暂停任务
	private JButton endTask = new JButton();// 结束任务

	public TaskControl() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(task);
		this.add(taskDataJSP);

		this.add(refresh);
		this.add(startTask);
		this.add(suspendTask);
		this.add(endTask);
	}

	private void loadingData() {// 装载数据
		columnNames = Constant.TaskCcolumnNames;
		data=Data.getRunUrlSet();
		taskDataSetModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		taskDataSet.setModel(taskDataSetModel);
	}

	private void Init() {
		Init.initJLable(task, "task");

		Init.initJTable(taskDataSet, "taskDataSet");
		Init.initJScrollPane(taskDataJSP, "taskDataJSP");

		Init.initJButton(refresh, "refresh");
		Init.initJButton(startTask, "startTask");
		Init.initJButton(suspendTask, "suspendTask");
		Init.initJButton(endTask, "endTask");
	}

	private void setBounds() {
		task.setBounds(320, 0, 300, 40);
		taskDataJSP.setBounds(20, 50, 934, 400);

		refresh.setBounds(30, 500, 180, 40);
		startTask.setBounds(270, 500, 180, 40);
		suspendTask.setBounds(515, 500, 180, 40);
		endTask.setBounds(760, 500, 180, 40);
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));

		task.setFont(new Font("微软雅黑", 0, 26));
		task.setForeground(new Color(0, 255, 255));
		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("refresh")));
		startTask.setBackground(new Color(150, 150, 150));
		startTask.setIcon(new ImageIcon(Constant.getIcon("startTask")));
		suspendTask.setBackground(new Color(150, 150, 150));
		suspendTask.setIcon(new ImageIcon(Constant.getIcon("suspendTask")));
		endTask.setBackground(new Color(150, 150, 150));
		endTask.setIcon(new ImageIcon(Constant.getIcon("endTask")));
	}

	private void listener() {
		refresh.addMouseListener(this);
		startTask.addMouseListener(this);
		suspendTask.addMouseListener(this);
		endTask.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("refresh".equals(e.getComponent().getName())) {
			data=Data.getRunUrlSet();
			taskDataSetModel = new DefaultTableModel(data, columnNames) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			taskDataSet.setModel(taskDataSetModel);
		} else if ("startTask".equals(e.getComponent().getName())) {
			//只能开始任务状态为暂停的  
			int selectedRow = taskDataSet.getSelectedRow();
			if (selectedRow != -1) {
				if(taskDataSet.getValueAt(selectedRow, 4).toString().equals("暂停中")){
					if(Task.modifyTaskStatus(taskDataSet.getValueAt(selectedRow, 0).toString(),taskDataSet.getValueAt(selectedRow, 2).toString(),Constant.KeyValue.get("Run"))){

						data=Data.getRunUrlSet();
						taskDataSetModel = new DefaultTableModel(data, columnNames) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						taskDataSet.setModel(taskDataSetModel);
						
						new Promptinformation(null, "运行成功", Constant.KeyValue.get("Info"));
					}else{
						new Promptinformation(null, "启用失败,请检查网络连接", Constant.KeyValue.get("Info"));
					}
				}else{
					new Promptinformation(null, "无法启用,请重新选择", Constant.KeyValue.get("Info"));
				}
			}
		} else if ("suspendTask".equals(e.getComponent().getName())) {
			//只能暂停任务状态为运行中  
			int selectedRow = taskDataSet.getSelectedRow();
			if (selectedRow != -1) {
				if(taskDataSet.getValueAt(selectedRow, 4).toString().equals("运行中")){
					if(Task.modifyTaskStatus(taskDataSet.getValueAt(selectedRow, 0).toString(),taskDataSet.getValueAt(selectedRow, 2).toString(),Constant.KeyValue.get("Wait"))){

						data=Data.getRunUrlSet();
						taskDataSetModel = new DefaultTableModel(data, columnNames) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						taskDataSet.setModel(taskDataSetModel);
						
						new Promptinformation(null, "暂停成功", Constant.KeyValue.get("Info"));
					}else{
						new Promptinformation(null, "暂停失败,请检查网络连接", Constant.KeyValue.get("Info"));
					}
				}else{
					new Promptinformation(null, "无法暂停,请重新选择", Constant.KeyValue.get("Info"));
				}
			}
		} else if ("endTask".equals(e.getComponent().getName())) {
			int selectedRow = taskDataSet.getSelectedRow();
			if (selectedRow != -1) {
				new Promptinformation(null, "确定要终止此任务?", Constant.KeyValue.get("Confirm"));
				if(Promptinformation.flag){
					Promptinformation.flag=false;//状态改回去

					if(Task.modifyTaskStatus(taskDataSet.getValueAt(selectedRow, 0).toString(),taskDataSet.getValueAt(selectedRow, 2).toString(),Constant.KeyValue.get("Complete"))){
						data=Data.getRunUrlSet();
						taskDataSetModel = new DefaultTableModel(data, columnNames) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						taskDataSet.setModel(taskDataSetModel);
						
						new Promptinformation(null, "终止成功", Constant.KeyValue.get("Info"));
					}else{
						new Promptinformation(null, "终止失败,请检查网络连接", Constant.KeyValue.get("Info"));
					}
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(255, 255, 255));
		} else if ("startTask".equals(e.getComponent().getName())) {
			startTask.setBackground(new Color(255, 255, 255));
		} else if ("suspendTask".equals(e.getComponent().getName())) {
			suspendTask.setBackground(new Color(255, 255, 255));
		} else if ("endTask".equals(e.getComponent().getName())) {
			endTask.setBackground(new Color(255, 255, 255));
		}

	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(150, 150, 150));
		}else if ("startTask".equals(e.getComponent().getName())) {
			startTask.setBackground(new Color(150, 150, 150));
		} else if ("suspendTask".equals(e.getComponent().getName())) {
			suspendTask.setBackground(new Color(150, 150, 150));
		} else if ("endTask".equals(e.getComponent().getName())) {
			endTask.setBackground(new Color(150, 150, 150));
		}
	}

}
