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
import javax.swing.JTextArea;

import team.qep.crawler.basic.Default;
import team.qep.crawler.view.Init2;
import team.qep.crawler.view.Myjpanel;

//自定义消息提示框
public class Promptinformation implements MouseListener {
	public static int flag; //0为是   1为否
	private JDialog  jd;
	private Myjpanel jp   = new Myjpanel("");
	private JButton  jb1  = new JButton("");
	private JButton  jb2  = new JButton("");
	private JButton  jb3  = new JButton("");
	private JTextArea   info = new JTextArea();
				
	public Promptinformation(JFrame jf,String str,int k){
    	this.flag=1;
		jd = new JDialog(jf,true);
		this.init();
		info.setText(str);
		
		jp.add(info);
		if(k==1){
			jp.add(jb1);
		}else{
			jp.add(jb2);
			jp.add(jb3);
		}
		info.setText(str);
		jp.add(info);
		jd.add(jp);
		jd.setVisible(true);
	}
	public void init(){
		Init2.Init2JDialog(jd, "jd", 380, 150);
		Init2.Init2JPanel(jp, "jp");
		jp.setBackground(new Color(80,80,80));
		jp.setBounds(0, 0, 380, 150);
		
		Init2.Init2JButton(jb1, "jb1");
		jb1.setIcon(new ImageIcon(Default.getDynamicIconPath("define")));//任务icon
		jb1.setBounds(160, 100, 32, 32);
		jb1.setContentAreaFilled(false);//按键透明
		jb1.addMouseListener(this);

		Init2.Init2JButton(jb2, "jb2");
		jb2.setIcon(new ImageIcon(Default.getDynamicIconPath("determine")));//任务icon
		jb2.setBounds(80, 100, 32, 32);
		jb2.setContentAreaFilled(false);//按键透明
		jb2.addMouseListener(this);
		
		Init2.Init2JButton(jb3, "jb3");
		jb3.setIcon(new ImageIcon(Default.getDynamicIconPath("cancel")));//任务icon
		jb3.setBounds(260, 100, 32, 32);
		jb3.setContentAreaFilled(false);//按键透明
		jb3.addMouseListener(this);

		Init2.Init2JTextArea(info, "info");
		info.setFont(new Font("微软雅黑",0,20));
		info.setOpaque(false);//设为透明
		info.setBorder(null);//去掉边框
		info.setEditable(false);//屏蔽输入
		info.setBounds(20, 10, 340,80);
		info.setForeground(new Color(255,255,255));
	}
	
	public void mouseClicked(MouseEvent e) {
		if("jb1".equals(e.getComponent().getName())){
			jd.dispose() ;
	    }else if("jb2".equals(e.getComponent().getName())){
	    	this.flag=0;
	    	jd.dispose();
	    }else if("jb3".equals(e.getComponent().getName())){
	    	this.flag=1;
	    	jd.dispose();
	    }
	}
	public void mouseEntered(MouseEvent e){//鼠标进入组件时执行的操作 
		if("jb1".equals(e.getComponent().getName())){
			jb1.setIcon(new ImageIcon(Default.getDynamicIconPath("define1")));//任务icon
		}else if("jb2".equals(e.getComponent().getName())){
			jb2.setIcon(new ImageIcon(Default.getDynamicIconPath("determine1")));//任务icon
		}else if("jb3".equals(e.getComponent().getName())){
			jb3.setIcon(new ImageIcon(Default.getDynamicIconPath("cancel1")));//任务icon
		}
	}
	public void mouseExited(MouseEvent e) {//鼠标离开组件时执行的操作 
		if("jb1".equals(e.getComponent().getName())){
			jb1.setIcon(new ImageIcon(Default.getDynamicIconPath("define")));//任务icon
		}else if("jb2".equals(e.getComponent().getName())){
			jb2.setIcon(new ImageIcon(Default.getDynamicIconPath("determine")));//任务icon
		}else if("jb3".equals(e.getComponent().getName())){
			jb3.setIcon(new ImageIcon(Default.getDynamicIconPath("cancel")));//任务icon
		}
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	
	public static void main(String[] args){
		new Promptinformation(null, "Please check whether the networhether the ",2);//１为普通窗口2为确认对话窗口
	}
	
	//V1.0
	public static void informationprompt(JFrame jf,String content){
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(jf,content, "Prompt", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,options,options[0]); 
	}
	public static void errorPrompt(JFrame jf,String content){
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(jf,content, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,options,options[0]); 
	}
	public static void warningPrompt(JFrame jf,String content){
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(jf, content, "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
	}
	public static int confirmPrompt(JFrame jf,String content){
		Object[] options = {"YES","NO"};
		return JOptionPane.showOptionDialog(jf, content, "confirm", JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[0]);
	}
}
