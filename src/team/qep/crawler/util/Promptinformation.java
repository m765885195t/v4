package team.qep.crawler.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.server.Task;
import team.qep.crawler.ui.Init;
import team.qep.crawler.view.Init2;
import team.qep.crawler.view.Myjpanel;

//自定义消息提示框
public class Promptinformation implements MouseListener {
	public static boolean flag=true; //true是   false为否
	private JDialog  infoJD = new JDialog();
	private JPanel infoJP   = new JPanel();
	private JButton  jb1  = new JButton();
	private JButton  jb2  = new JButton();
	private JButton  jb3  = new JButton();
	private JTextArea  info = new JTextArea();
				
	public Promptinformation(JFrame jf,String str,int mode){
    	infoJD = new JDialog(jf,true);
    	this.Init();
		this.setBounds();
		this.setColour();
		this.listener();
		info.setText(str);
		
		infoJP.add(info);
		
		if(mode==Constant.KeyValue.get("Info")){
			infoJP.add(jb1);
		}else if(mode==Constant.KeyValue.get("Confirm")){
			infoJP.add(jb2);
			infoJP.add(jb3);
		}
		info.setText(str);
		
		infoJP.add(info);
		infoJD.add(infoJP);
		infoJD.setVisible(true);
	}

	private void Init() {
		Init2.Init2JDialog(infoJD, "infoJD", 380, 150);
		Init2.Init2JPanel(infoJP, "infoJP");
		
		Init2.Init2JTextArea(info, "info");
		info.setBorder(null);//去掉边框
		info.setEditable(false);//屏蔽输入

		Init2.Init2JButton(jb1, "jb1");
		Init2.Init2JButton(jb2, "jb2");
		Init2.Init2JButton(jb3, "jb3");
	}

	private void setBounds() {
		infoJP.setBounds(0, 0, 380, 150);
		jb1.setBounds(160, 100, 32, 32);
		jb2.setBounds(80, 100, 32, 32);
		jb3.setBounds(260, 100, 32, 32);
		info.setBounds(20, 10, 340,80);
	}

	private void setColour() {
		infoJP.setBackground(new Color(80,80,80));
		
		info.setFont(new Font("微软雅黑",0,20));
		info.setForeground(new Color(255,255,255));
		info.setOpaque(false);//设为透明
		jb1.setBackground(new Color(150, 150, 150));
		jb1.setIcon(new ImageIcon(Constant.getIcon("jb1b")));
//		jb1.setContentAreaFilled(false);//按键透明
		jb2.setBackground(new Color(150, 150, 150));
		jb2.setIcon(new ImageIcon(Constant.getIcon("jb2b")));
//		jb2.setContentAreaFilled(false);//按键透明
		jb3.setBackground(new Color(150, 150, 150));
		jb3.setIcon(new ImageIcon(Constant.getIcon("jb3b")));
//		jb3.setContentAreaFilled(false);//按键透明
	}

	private void listener() {
		jb1.addMouseListener(this);
		jb2.addMouseListener(this);
		jb3.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if("jb1".equals(e.getComponent().getName())){
			infoJD.dispose() ;
	    }else if("jb2".equals(e.getComponent().getName())){
	    	Promptinformation.flag=true;
	    	infoJD.dispose();
	    }else if("jb3".equals(e.getComponent().getName())){
	    	Promptinformation.flag=false;
	    	infoJD.dispose();
	    }
	}

	public void mousePressed(MouseEvent e) {// 按下
		if ("jb1".equals(e.getComponent().getName())) {
			jb1.setContentAreaFilled(false);
		} else if ("jb2".equals(e.getComponent().getName())) {
			jb2.setContentAreaFilled(false);
		} else if ("jb3".equals(e.getComponent().getName())) {
			jb3.setContentAreaFilled(false);
		} 
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("jb1".equals(e.getComponent().getName())) {
			jb1.setIcon(new ImageIcon(Constant.getIcon("issueTask1w")));
		} else if ("jb2".equals(e.getComponent().getName())) {
			jb2.setIcon(new ImageIcon(Constant.getIcon("issueTask2w")));
		} else if ("jb3".equals(e.getComponent().getName())) {
			jb3.setIcon(new ImageIcon(Constant.getIcon("resourceSchedulingw")));
		} 
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("jb1".equals(e.getComponent().getName())) {
			jb1.setIcon(new ImageIcon(Constant.getIcon("jb1b")));
		} else if ("jb2".equals(e.getComponent().getName())) {
			jb2.setIcon(new ImageIcon(Constant.getIcon("jb2b")));
		} else if ("jb3".equals(e.getComponent().getName())) {
			jb3.setIcon(new ImageIcon(Constant.getIcon("jb3b")));
		} 
	}
}
