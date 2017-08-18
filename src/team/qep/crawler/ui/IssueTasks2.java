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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Promptinformation;

public class IssueTasks2 extends JPanel implements MouseListener {
	private boolean flag = true;// true---电商 false---新闻博客

	private JLabel timely = new JLabel("Timely Crawl");

	private JTextArea timelyURLSet = new JTextArea();
	private JScrollPane timelyURLSetJSP = new JScrollPane(timelyURLSet); // 待发布的及时url集合

	private JComboBox<String> template = new JComboBox<String>(); // 即时爬取模板
	private JButton timelyUrlPublish = new JButton(); // 即时任务发布
	private JButton refresh = new JButton(); // 刷新数据

	private String[] columnNames; // 表格列名
	private String[][] data; // 表格数据
	private DefaultTableModel timelyDataSetModel;
	private JTable timelyDataSet = new JTable();
	private JScrollPane timelyDataJSP = new JScrollPane(timelyDataSet); // 支持的url集合

	public IssueTasks2() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(timely);
		this.add(timelyURLSetJSP);

		this.add(template);
		this.add(timelyUrlPublish);
		this.add(refresh);
		this.add(timelyDataJSP);
	}

	private void loadingData() {// 装载数据
		// 模板配置
		for (String str : Constant.Template) {
			template.addItem(str);
		}

		columnNames = Constant.E_CommerceCcolumnNames;
		 data = new String[0][];//得到数据
		timelyDataSetModel = new DefaultTableModel(data, columnNames);
		timelyDataSet.setModel(timelyDataSetModel);
	}

	private void Init() {
		Init.initJLable(timely, "timely");
		Init.initJComboBox(template, "template");

		Init.initJTextArea(timelyURLSet, "timelyURLSet");
		Init.initJScrollPane(timelyURLSetJSP, "timelyURLSetJSP");

		Init.initJButton(refresh, "refresh");
		Init.initJButton(timelyUrlPublish, "timelyUrlPublish");

		Init.initJTable(timelyDataSet, "timelyDataSet");
		Init.initJScrollPane(timelyDataJSP, "timelyDataJSP");
	}

	private void setBounds() {
		timely.setBounds(400, 0, 200, 40);
		timelyURLSetJSP.setBounds(80, 50, 670, 190);
		template.setBounds(780, 60, 120, 33);
		timelyUrlPublish.setBounds(780, 121, 120, 40);
		refresh.setBounds(780, 190, 120, 40);
		timelyDataJSP.setBounds(40, 280, 894, 280);
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));

		timely.setFont(new Font("微软雅黑", 0, 26));
		timely.setForeground(new Color(0, 255, 255));
		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("refresh")));
		timelyUrlPublish.setBackground(new Color(150, 150, 150));
		timelyUrlPublish.setIcon(new ImageIcon(Constant.getIcon("timelyUrlPublish")));
	}

	private void listener() {
		template.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (template.getSelectedIndex() < 2) {
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		});
		refresh.addMouseListener(this);
		timelyUrlPublish.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("timelyUrlPublish".equals(e.getComponent().getName())) {
			String timelyURL = timelyURLSet.getText();
			String configure = template.getSelectedItem().toString();

			if (!timelyURL.equals("")) {
				if (Task.timelyUrlPublish(timelyURL, configure)) {
					// 发布成功

					// //立即获取数据or手动刷新<--
					// data=Task.getTimelyUrlData();
					// if(flag){
					// columnNames = Constant.E_CommerceCcolumnNames;
					// }else{
					// columnNames = Constant.BlogNewsCcolumnNames;
					// }
					// timelyDataSetModel = new
					// DefaultTableModel(data,columnNames);
					// timelyDataSet.setModel(timelyDataSetModel);

					new Promptinformation(null,
							"Successful submission has been done automatically with duplicate tasks and unsupported tasks.",
							1);// １为普通窗口2为确认对话窗口
					timelyURLSet.setText("");
					template.setSelectedIndex(0);
				} else {
					// 发布失败
					new Promptinformation(null, "The task submission failed. Check network connections!", 1);// １为普通窗口2为确认对话窗口
				}
			} else {
				// 空任务
				new Promptinformation(null, "Please enter the correct url with the currently supported url", 1);// １为普通窗口2为确认对话窗口
			}

		} else if ("refresh".equals(e.getComponent().getName())) {
			// 刷新获取数据
			data = Task.getTimelyUrlData();
			if (flag) {
				columnNames = Constant.E_CommerceCcolumnNames;
			} else {
				columnNames = Constant.BlogNewsCcolumnNames;
			}
			timelyDataSetModel = new DefaultTableModel(data, columnNames);
			timelyDataSet.setModel(timelyDataSetModel);
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
		}

	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("timelyUrlPublish".equals(e.getComponent().getName())) {
			timelyUrlPublish.setBackground(new Color(150, 150, 150));
		} else if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(new Color(150, 150, 150));
		}
	}

}
