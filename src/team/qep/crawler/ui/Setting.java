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

import team.qep.crawler.util.Constant;

public class Setting extends JPanel implements MouseListener {

	private JLabel setting = new JLabel("设  置  中  心");

	public Setting() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(setting);
	}

	private void loadingData() {// 装载数据

	}

	private void Init() {
		Init.initJLable(setting, "setting");
	}

	private void setBounds() {
		setting.setBounds(320, 0, 300, 40);

	}

	private void setColour() {
		this.setBackground(new Color(20, 20, 20));
		
		setting.setFont(new Font("微软雅黑", 0, 26));
		setting.setForeground(new Color(0, 255, 255));
		
	}
	private void listener() {
	
	}

	public void mouseClicked(MouseEvent e) {// 单击
	
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		

	}

	public void mouseExited(MouseEvent e) {// 离开
	
	}

}
