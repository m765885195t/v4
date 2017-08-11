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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Promptinformation;
import team.qep.crawler.view.Crawlergraph;

public class DataDisplay extends JPanel implements MouseListener {
	private JLabel dataDisplay = new JLabel("Data  Display");

	private JComboBox<String> runTask = new JComboBox<String>(); //运行中的任务
	private JTextField taskStatus = new JTextField(); // 任务状态
	private JButton savePicture = new JButton();// 保存当前进度图
	private JButton refresh = new JButton();// 刷新
	private JPanel lineChart;//进度图savePicture

	public DataDisplay() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(dataDisplay);
		this.add(runTask);
		this.add(taskStatus);
		this.add(savePicture);
		this.add(refresh);
		this.add(lineChart);
	}

	private void loadingData() {// 装载数据
		lineChart = Crawlergraph.createLineChart();
		
		for(String str:Task.getRunUrl()){//得到正在运行的任务(精确+模糊)
			runTask.addItem(str);
		}
	}

	private void Init() {

		Init.initJLable(dataDisplay, "dataDisplay");

		Init.initJComboBox(runTask, "runTask");
		Init.initJTextField(taskStatus, "taskStatus");
	    taskStatus.setEditable(false);//屏蔽输入

		Init.initJButton(savePicture, "savePicture");
		Init.initJButton(refresh, "refresh");
		Init.initJButton(refresh, "refresh");
	}

	private void setBounds() {
		dataDisplay.setBounds(400, 0, 200, 40);
		runTask.setBounds(20, 40, 934, 380);
		taskStatus.setBounds(80, 490, 180, 40);
		savePicture.setBounds(400, 490, 180, 40);
		refresh.setBounds(700, 490, 180, 40);
		lineChart.setBounds(700, 490, 180, 40);
	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));

		dataDisplay.setFont(new Font("微软雅黑", 0, 26));
		dataDisplay.setForeground(new Color(0, 255, 255));
		savePicture.setBackground(new Color(150, 150, 150));
		savePicture.setIcon(new ImageIcon(Constant.getIcon("startTask")));
		refresh.setBackground(new Color(150, 150, 150));
		refresh.setIcon(new ImageIcon(Constant.getIcon("suspendTask")));
	}

	private void listener() {
		savePicture.addMouseListener(this);
		refresh.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("savePicture".equals(e.getComponent().getName())) {

		} else if ("refresh".equals(e.getComponent().getName())) {
		
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
