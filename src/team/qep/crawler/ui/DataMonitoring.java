package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import team.qep.crawler.basic.Constant;
import team.qep.crawler.server.Task;
import team.qep.crawler.view.Crawlergraph;

public class DataMonitoring extends JPanel implements MouseListener {
	private JLabel dataDisplay = new JLabel("Data  Monitoring");

	private JComboBox<String> runTask = new JComboBox<String>(); //运行中的任务
	private JTextField taskStatus = new JTextField(); // 任务状态
	private JButton savePicture = new JButton();// 保存当前进度图
	private JButton refresh = new JButton();// 刷新
	private JPanel lineChart;//进度图savePicture

	public DataMonitoring() {
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
		dataDisplay.setBounds(380, 0, 300, 40);
		runTask.setBounds(60, 64, 200, 33);
		taskStatus.setBounds(330, 64, 180, 33);
		savePicture.setBounds(590, 60, 120, 40);
		refresh.setBounds(784, 60, 120, 40);
		lineChart.setBounds(15, 130, 944, 430);
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
