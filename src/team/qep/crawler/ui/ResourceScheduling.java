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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import team.qep.crawler.server.Data;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Constant;
import team.qep.crawler.util.MyDocument;
import team.qep.crawler.util.Promptinformation;
import team.qep.crawler.util.Regex;

public class ResourceScheduling extends JPanel implements MouseListener {

	private JLabel resourceScheduling = new JLabel("资  源  中  心");//资源调度

	private String[] columnNames; // 表格列名
	private String[][] data; // 表格数据
	private DefaultTableModel taskDataSetModel;
	private JTable resourcesSet = new JTable();//从机资源集合
	private JScrollPane resourcesJSP = new JScrollPane(resourcesSet); // 未终止的任务数据集
	
	private JButton refresh = new JButton();// 刷新
	private JTextField ip = new JTextField(15);// 添加从机的ip (要端口号?)
	private JButton add = new JButton();// 添加从机
	private JButton delete = new JButton();// 删除从机
	private JButton start = new JButton();// 启用从机(启用成功后状态为就绪状态)
	private JButton stop = new JButton();// 终止从机(只能终止就绪状态的从机)

	public ResourceScheduling() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(resourceScheduling);
		this.add(resourcesJSP);

		this.add(refresh);
		this.add(ip);
		this.add(add);
		this.add(delete);
		this.add(start);
		this.add(stop);
	}

	private void loadingData() {// 装载数据
		columnNames = Constant.ResourceSchedulingCcolumnNames;
		data=Data.getResourceInformation();
		taskDataSetModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		resourcesSet.setModel(taskDataSetModel);
	}

	private void Init() {
		Init.initJLable(resourceScheduling, "resourceScheduling");

		Init.initJTable(resourcesSet, "resourcesSet");
		Init.initJScrollPane(resourcesJSP, "resourcesJSP");

		Init.initJButton(refresh, "refresh");
		Init.initJTextField(ip,"ip");
		ip.setDocument(new MyDocument(15));

		Init.initJButton(add, "add");
		Init.initJButton(delete, "delete");
		Init.initJButton(start,"start");
		Init.initJButton(stop,"stop");
	}

	private void setBounds() {
		resourceScheduling.setBounds(320, 0, 300, 40);
		
		resourcesJSP.setBounds(50, 50, 670, 520);
		
		refresh.setBounds(770, 100, 150, 40);
		start.setBounds(770, 170, 150, 40);
		stop.setBounds(770,240, 150, 40);
		
		ip.setBounds(770, 350, 150, 30);
		add.setBounds(770, 410, 150, 40);
		delete.setBounds(770, 480, 150, 40);
		
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));
		resourceScheduling.setFont(new Font("微软雅黑", 0, 26));
		resourceScheduling.setForeground(new Color(0, 255, 255));
		
		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("refresh")));
		add.setBackground(new Color(150, 150, 150));
		add.setIcon(new ImageIcon(Constant.getIcon("add")));
		delete.setBackground(new Color(150, 150, 150));
		delete.setIcon(new ImageIcon(Constant.getIcon("delete")));
		start.setBackground(new Color(150, 150, 150));
		start.setIcon(new ImageIcon(Constant.getIcon("start")));
		stop.setBackground(new Color(150, 150, 150));
		stop.setIcon(new ImageIcon(Constant.getIcon("stop")));
	}

	private void listener() {
		refresh.addMouseListener(this);
		add.addMouseListener(this);
		delete.addMouseListener(this);
		start.addMouseListener(this);
		stop.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("refresh".equals(e.getComponent().getName())) {
			data=Data.getResourceInformation();
			taskDataSetModel = new DefaultTableModel(data, columnNames) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			resourcesSet.setModel(taskDataSetModel);
		} else if ("add".equals(e.getComponent().getName())) {
			if(Regex.RE_matching(ip.getText(), Regex.regIP)){
				if(Task.addDeleteResource(1,ip.getText())){
					new Promptinformation(null, "添加成功", Constant.KeyValue.get("Info"));
					
					data=Data.getResourceInformation();
					taskDataSetModel = new DefaultTableModel(data, columnNames) {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					resourcesSet.setModel(taskDataSetModel);
					
					ip.setText("");
				}else{
	 				new Promptinformation(null, "添加失败,请检查网络连接", Constant.KeyValue.get("Info"));
				}
			}else{
 				new Promptinformation(null, "请输入正确的IP地址", Constant.KeyValue.get("Info"));
			}
		} else if ("delete".equals(e.getComponent().getName())) {
			int selectedRow = resourcesSet.getSelectedRow();
			if (selectedRow != -1) {
				String status=resourcesSet.getValueAt(selectedRow, 2).toString();
				if(status.equals("已停止") || status.equals("就绪")){
					new Promptinformation(null, "删除成功", Constant.KeyValue.get("Confirm"));
					if(Promptinformation.flag){
						if(Task.addDeleteResource(0,resourcesSet.getValueAt(selectedRow, 0).toString())){
							new Promptinformation(null, "删除成功", Constant.KeyValue.get("Info"));
						
							data=Data.getResourceInformation();
							taskDataSetModel = new DefaultTableModel(data, columnNames) {
								public boolean isCellEditable(int row, int column) {
									return false;
								}
							};
							resourcesSet.setModel(taskDataSetModel);
						
						}else{
							new Promptinformation(null, "删除失败,请检查网络连接", Constant.KeyValue.get("Info"));
						}
					}
				}else{
					new Promptinformation(null, "无法删除正在运行的从机", Constant.KeyValue.get("Info"));
				}
			}
		} else if ("start".equals(e.getComponent().getName())) {
			//只能启用状态为停止的  
			int selectedRow = resourcesSet.getSelectedRow();
			if (selectedRow != -1) {
				if(resourcesSet.getValueAt(selectedRow, 2).toString().equals("已停止")){
					if(Task.modifyResourceStatus(resourcesSet.getValueAt(selectedRow, 0).toString(),Constant.KeyValue.get("Start"))){
						
						data=Data.getResourceInformation();
						taskDataSetModel = new DefaultTableModel(data, columnNames) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						resourcesSet.setModel(taskDataSetModel);
						
						new Promptinformation(null, "启用成功", Constant.KeyValue.get("Info"));
					}else{
						new Promptinformation(null, "启用失败,请检查网络连接", Constant.KeyValue.get("Info"));
					}
				}else{
					new Promptinformation(null, "无法启用,请重新选择", Constant.KeyValue.get("Info"));
				}
			}
		}else if ("stop".equals(e.getComponent().getName())) {
			//只能停止状态为就绪的  
			int selectedRow = resourcesSet.getSelectedRow();
			if (selectedRow != -1) {
				if(resourcesSet.getValueAt(selectedRow, 2).toString().equals("就绪")){
					if(Task.modifyResourceStatus(resourcesSet.getValueAt(selectedRow, 0).toString(),Constant.KeyValue.get("Stop"))){
						
						data=Data.getResourceInformation();
						taskDataSetModel = new DefaultTableModel(data, columnNames) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						resourcesSet.setModel(taskDataSetModel);
						
						new Promptinformation(null, "停止成功", Constant.KeyValue.get("Info"));
					}else{
						new Promptinformation(null, "停止失败,请检查网络连接", Constant.KeyValue.get("Info"));
					}
				}else{
					new Promptinformation(null, "无法停止,请重新选择", Constant.KeyValue.get("Info"));
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
		} else if ("add".equals(e.getComponent().getName())) {
			add.setBackground(new Color(255, 255, 255));
		} else if ("delete".equals(e.getComponent().getName())) {
			delete.setBackground(new Color(255, 255, 255));
		} else if ("start".equals(e.getComponent().getName())) {
			start.setBackground(new Color(255, 255, 255));
		} else if ("stop".equals(e.getComponent().getName())) {
			stop.setBackground(new Color(255, 255, 255));
		}

	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(150, 150, 150));
		}else if ("add".equals(e.getComponent().getName())) {
			add.setBackground(new Color(150, 150, 150));
		} else if ("delete".equals(e.getComponent().getName())) {
			delete.setBackground(new Color(150, 150, 150));
		} else if ("start".equals(e.getComponent().getName())) {
			start.setBackground(new Color(150, 150, 150));
		}else if ("stop".equals(e.getComponent().getName())) {
			stop.setBackground(new Color(150, 150, 150));
		}
	}

}
