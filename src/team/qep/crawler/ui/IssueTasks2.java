package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import team.qep.crawler.server.Data;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Constant;
import team.qep.crawler.util.ConvertJSON;
import team.qep.crawler.util.Promptinformation;
import team.qep.crawler.util.StringManipulation;

public class IssueTasks2 extends JPanel implements MouseListener {
	private boolean flag = true;// true---电商 false---新闻博客

	private JTextArea timelyURLSet = new JTextArea();
	private JScrollPane timelyURLSetJSP = new JScrollPane(timelyURLSet); // 待发布的及时url集合

	private JButton timelyUrlPublish = new JButton(); // 即时任务发布
	private JButton refresh = new JButton(); // 刷新数据
	private JButton export = new JButton(); // 导出为文件或excel

	private String[][] UrlData; // 即时爬取的数据
	
	private DefaultTableModel timelyTaskUrlSetModel;
	private JTable timelyTaskUrlSet = new JTable();
	private JScrollPane timelyTaskUrlSetJSP = new JScrollPane(timelyTaskUrlSet); //即时任务的url集
	
	private DefaultTableModel ecDataModel;
	private JTable ecDataJT = new JTable();
	private JScrollPane ecDataJSP = new JScrollPane(ecDataJT); //电商数据视图
	private JTextArea bnDataJTA = new JTextArea();
	private JScrollPane bnDataJSP = new JScrollPane(bnDataJTA); //新闻博客数据视图

	public IssueTasks2() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(timelyURLSetJSP);
		this.add(timelyUrlPublish);
		this.add(refresh);
		this.add(export);
		this.add(timelyTaskUrlSetJSP);
		this.add(ecDataJSP);
	}

	private void loadingData() {// 装载数据
		timelyTaskUrlSetModel = new DefaultTableModel(new String[0][], new String[]{"即时任务URL"}){
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
		};
		timelyTaskUrlSet.setModel(timelyTaskUrlSetModel);
		
		ecDataModel = new DefaultTableModel(new String[0][], Constant.E_CommerceCcolumnNames);
		ecDataJT.setModel(ecDataModel);
	}

	private void Init() {
		Init.initJTextArea(timelyURLSet, "timelyURLSet");
		Init.initJScrollPane(timelyURLSetJSP, "timelyURLSetJSP");

		Init.initJButton(timelyUrlPublish, "timelyUrlPublish");
		Init.initJButton(refresh, "refresh");
		Init.initJButton(export, "export");
		Init.initJTable(timelyTaskUrlSet, "timelyTaskUrlSet");
		Init.initJScrollPane(timelyTaskUrlSetJSP, "timelyTaskUrlSetJSP");
		
		Init.initJTable(ecDataJT, "ecDataJT");
		Init.initJScrollPane(ecDataJSP, "ecDataJSP");
		Init.initJTextArea(bnDataJTA, "bnDataJTA");
		Init.initJScrollPane(bnDataJSP, "bnDataJSP");
		bnDataJTA.setFont(new Font("微软雅黑", 0, 16));// 设置字体格式
		timelyTaskUrlSet.setFont(new Font("serif", 0, 16));// 设置表格字体
		ecDataJT.setFont(new Font("serif", 0, 16));// 设置表格字体
	}

	private void setBounds() {
		timelyURLSetJSP.setBounds(30, 20, 800, 220);
		timelyUrlPublish.setBounds(850,40,94, 40);
		refresh.setBounds(850, 110, 94, 40);
		export.setBounds(850, 180, 94, 40);
	
		timelyTaskUrlSetJSP.setBounds(20, 262, 190, 320);
		ecDataJSP.setBounds(230, 262,724, 320);
		bnDataJSP.setBounds(230, 262,724, 320);
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));

		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("refresh")));
		timelyUrlPublish.setBackground(new Color(150, 150, 150));
		timelyUrlPublish.setIcon(new ImageIcon(Constant.getIcon("timelyUrlPublish")));
		export.setBackground(new Color(150, 150, 150));
		export.setIcon(new ImageIcon(Constant.getIcon("export")));
	}

	private void listener() {
		timelyUrlPublish.addMouseListener(this);
		refresh.addMouseListener(this);
		export.addMouseListener(this);
		timelyTaskUrlSet.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("timelyUrlPublish".equals(e.getComponent().getName())) {
			String timelyURL = timelyURLSet.getText();
			if (!timelyURL.equals("")) {
				if (Task.timelyUrlPublish(timelyURL)) {
					new Promptinformation(null,"即时任务发布成功",Constant.KeyValue.get("Info"));// １为普通窗口2为确认对话窗口
					timelyURLSet.setText("");

				} else {
					new Promptinformation(null, "请检查网络连接", Constant.KeyValue.get("Info"));// １为普通窗口2为确认对话窗口
				}
			} else {
				// 空任务
				new Promptinformation(null, "请输入混合url", Constant.KeyValue.get("Info"));// １为普通窗口2为确认对话窗口
			}
		} else if ("refresh".equals(e.getComponent().getName())) {
			// 刷新获取数据
			UrlData = Data.getTimelyUrlData();//得到总数据
			String[][] data = StringManipulation.twoToTwo(UrlData, 0);
			timelyTaskUrlSetModel = new DefaultTableModel(data, new String[]{"即时任务URL"});
			timelyTaskUrlSet.setModel(timelyTaskUrlSetModel);
			
			//初始化数据
			ecDataModel = new DefaultTableModel(new String[0][], Constant.E_CommerceCcolumnNames);
			ecDataJT.setModel(ecDataModel);
			bnDataJTA.setText("");
		}else if ("timelyTaskUrlSet".equals(e.getComponent().getName())) {
			int selectedRow = timelyTaskUrlSet.getSelectedRow();
			if (selectedRow != -1) {
				this.remove(ecDataJSP);
				this.remove(bnDataJSP);

				if(UrlData[selectedRow][1].toString().equals(String.valueOf(Constant.KeyValue.get("EC")))){//电商
					String str=UrlData[selectedRow][2].replace("'", "\"").replace("u\"", "\"");
					String[][] data = StringManipulation.toTwoDimensionalArrays(ConvertJSON.toStringArray(str),6);
			
					ecDataModel = new DefaultTableModel(data,Constant.E_CommerceCcolumnNames);
					ecDataJT.setModel(ecDataModel);
					this.add(ecDataJSP);
				}else if(UrlData[selectedRow][1].equals(String.valueOf(Constant.KeyValue.get("BN")))){//新闻
					bnDataJTA.setText(UrlData[selectedRow][2]);
					this.add(bnDataJSP);
				}
				this.updateUI();
			}
		}else if ("export".equals(e.getComponent().getName())) {
		
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("timelyUrlPublish".equals(e.getComponent().getName())) {
			timelyUrlPublish.setBackground(new Color(255, 255, 255));
		} else if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(255, 255, 255));
		} else if ("export".equals(e.getComponent().getName())) {
			export.setBackground(new Color(255, 255, 255));
		}

	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("timelyUrlPublish".equals(e.getComponent().getName())) {
			timelyUrlPublish.setBackground(new Color(150, 150, 150));
		} else if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(150, 150, 150));
		}else if ("export".equals(e.getComponent().getName())) {
			export.setBackground(new Color(150, 150, 150));
		}
	}

}
