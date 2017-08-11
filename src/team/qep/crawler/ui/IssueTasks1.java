package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import team.qep.crawler.basic.Default;
import team.qep.crawler.server.Data;
import team.qep.crawler.server.Log;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Operationstring;
import team.qep.crawler.util.Promptinformation;
import team.qep.crawler.view.Init2;

public class IssueTasks1 extends JPanel implements MouseListener {
	private JTable supportUrlSet = new JTable(
			new DefaultTableModel(Operationstring.toTwoimensional(Default.getSupportFuzzyUrl()), new String[] { "SupportURL" }) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
	private JScrollPane supportUrlSetJS = new JScrollPane(supportUrlSet);
	private JScrollPane supportedJSP = new JScrollPane(supportUrlSet); //支持的url集合

	private JLabel fuzzy = new JLabel("Fuzzy Crawl");
	private JTextArea fuzzyURLSet = new JTextArea();
	private JScrollPane fuzzyURLSetJSP = new JScrollPane(fuzzyURLSet); //待发布的模糊url集合
	private JComboBox<String> fuzzyUrlPriority = new JComboBox<String>(); //模糊任务优先度
	private JButton fuzzyUrlPublish = new JButton(); //模糊任务发布

	private JLabel exact = new JLabel("Exact Crawl");
	private JComboBox<String> exactURLSet = new JComboBox<String>(); //待发布的精确url
	private JTextField keyWord = new JTextField(); //关键字
	private JComboBox<String> exactUrlPriority = new JComboBox<String>(); //精确任务优先度
	private JButton exactUrlPublish = new JButton(); //精确任务发布

	public IssueTasks1() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(fuzzy);
		this.add(supportedJSP);
		this.add(fuzzyURLSetJSP);
		this.add(fuzzyUrlPriority);
		this.add(fuzzyUrlPublish);
		this.add(fuzzyUrlPublish);
		this.add(exact);
		this.add(exactURLSet);
		this.add(keyWord);
		this.add(exactUrlPriority);
		this.add(exactUrlPublish);
	}
	private void loadingData() {//装载数据
		for(int i=1 ; i<5 ; i++){
			fuzzyUrlPriority.addItem("      Priority     "+String.valueOf(i));
			exactUrlPriority.addItem("      Priority     "+String.valueOf(i));
		}
		
		for(String str:Default.getSupportExactUrl()){
			exactURLSet.addItem(str);
		}
	}
	private void Init() {
		this.setBackground(new Color(20, 20, 20));

		Init.initJTable(supportUrlSet, "supportUrlSet");
		Init.initJScrollPane(supportedJSP, "supportedJSP");
		
		Init.initJLable(fuzzy, "fuzzy");
		Init.initJTextArea(fuzzyURLSet, "fuzzyURLSet");
		Init.initJScrollPane(fuzzyURLSetJSP, "fuzzyURLSetJSP");
		Init.initJComboBox(fuzzyUrlPriority, "fuzzyUrlPriority");
		Init.initJButton(fuzzyUrlPublish, "fuzzyUrlPublish");
		
		Init.initJLable(exact, "exact");
		Init.initJComboBox(exactURLSet, "exactURLSet");
		Init.initJTextField(keyWord, "keyWord");
		Init.initJComboBox(exactUrlPriority, "exactUrlPriority");
		Init.initJButton(exactUrlPublish, "exactUrlPublish");
	}

	private void setBounds() {
		supportedJSP.setBounds(50, 35, 200, 508);

		fuzzy.setBounds(400, 10, 200, 32);
		fuzzyURLSetJSP.setBounds(350, 60, 230, 330);
		fuzzyUrlPriority.setBounds(365, 430,200, 32);
		fuzzyUrlPublish.setBounds(380, 500,170, 40);
		
		exact.setBounds(720, 10, 200, 35);
		exactURLSet.setBounds(680, 120, 230, 35);
		keyWord.setBounds(680,225, 230, 35);
		exactUrlPriority.setBounds(695, 345, 200, 32);
		exactUrlPublish.setBounds(710, 450,170, 40);
	}
	private void setColour() {
		fuzzy.setFont(new Font("微软雅黑",0,26));
		fuzzy.setForeground(new Color(0,255,255));
		fuzzyUrlPublish.setBackground(new Color(150, 150, 150));
		fuzzyUrlPublish.setIcon(new ImageIcon(Default.getDynamicIconPath("fuzzyUrlPublish")));
		
		exact.setFont(new Font("微软雅黑",0,26));
		exact.setForeground(new Color(0,255,255));
		exactUrlPublish.setBackground(new Color(150, 150, 150));
		exactUrlPublish.setIcon(new ImageIcon(Default.getDynamicIconPath("exactUrlPublish")));
	}
	private void listener() {
		supportUrlSet.addMouseListener(this);
		fuzzyUrlPublish.addMouseListener(this);
		exactUrlPublish.addMouseListener(this);
	}
	

	public void mouseClicked(MouseEvent e) {// 单击
		if ("fuzzyUrlPublish".equals(e.getComponent().getName())) {

		} else if ("exactUrlPublish".equals(e.getComponent().getName())) {
		
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}
	
	public void mouseEntered(MouseEvent e) {// 进入
		if ("fuzzyUrlPublish".equals(e.getComponent().getName())) {
			fuzzyUrlPublish.setBackground(new Color(255,255,255));
		} else 	if ("exactUrlPublish".equals(e.getComponent().getName())) {
			exactUrlPublish.setBackground(new Color(255,255,255));

		}

	}
	public void mouseExited(MouseEvent e) {// 离开
		if ("fuzzyUrlPublish".equals(e.getComponent().getName())) {
			fuzzyUrlPublish.setBackground(new Color(150,150,150));
		}else if ("exactUrlPublish".equals(e.getComponent().getName())) {
			exactUrlPublish.setBackground(new Color(150,150,150));
		}
	}

	
}