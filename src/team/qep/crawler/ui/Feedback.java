package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class Feedback extends JPanel implements MouseListener {
	private JLabel feedback = new JLabel("建 议 反 馈");

	private JTextArea proposal = new JTextArea();
	private JScrollPane proposalJSP = new JScrollPane(proposal); //功能建议面板
	private JButton submit = new JButton(); //提交建议

	public Feedback() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(feedback);
		this.add(proposalJSP);
		this.add(submit);
	}

	private void loadingData() {// 装载数据

	}

	private void Init() {
		Init.initJLable(feedback, "feedback");

		Init.initJTextArea(proposal, "proposal");
		Init.initJScrollPane(proposalJSP, "proposalJSP");

		Init.initJButton(submit, "submit");
	}

	private void setBounds() {
		feedback.setBounds(320, 0, 300, 40);

		proposalJSP.setBounds(50, 50, 500, 500);
		submit.setBounds(500, 262,180, 40);
	}

	private void setColour() {
		this.setBackground(Theme.PanelColor);
		feedback.setFont(Theme.TitleFont);
		feedback.setForeground(Theme.TitleColor);
		submit.setBackground(Theme.ButtonColor);
		submit.setIcon(Constant.getIcon("submit"));
	}

	private void listener() {
		submit.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("submit".equals(e.getComponent().getName())) {

		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("submit".equals(e.getComponent().getName())) {
			submit.setBackground(Color.WHITE);
		} 
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("submit".equals(e.getComponent().getName())) {
			submit.setBackground(Theme.ButtonColor);
		} 
	}

}
