package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import team.qep.crawler.util.Constant;

public class HelpDescription extends JPanel implements MouseListener {
	private static int layer=0;
	
	private JLabel helpDescription = new JLabel("使  用  说  明");

	private JLabel help = new JLabel();
	private JButton last = new JButton();// 上一项
	private JButton next = new JButton();// 下一项

	public HelpDescription() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(helpDescription);
		this.add(help);
		this.add(last);
		this.add(next);
	}

	private void loadingData() {// 装载数据
		help.setIcon(new ImageIcon(Constant.getHelpPicturePath(layer)));
	}

	private void Init() {
		Init.initJLable(helpDescription, "helpDescription");
		Init.initJLable(help, "help");
		Init.initJButton(last, "last");
		Init.initJButton(next, "next");
	}

	private void setBounds() {
		helpDescription.setBounds(320, 0, 300, 40);

		help.setBounds(80, 60, 800,420);
		last.setBounds(254, 520, 120, 40);
		next.setBounds(600, 520, 120, 40);
	}

	private void setColour() {
		this.setBackground(Theme.PanelColor);
		
		helpDescription.setFont(Theme.TitleFont);
		helpDescription.setForeground(Theme.TitleColor);
		last.setBackground(Theme.ButtonColor);
		last.setIcon(Constant.getIcon("last"));
		next.setBackground(Theme.ButtonColor);
		next.setIcon(Constant.getIcon("next"));
	}

	private void listener() {
		last.addMouseListener(this);
		next.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("last".equals(e.getComponent().getName())) {
			layer=(layer+6)%7;
			help.setIcon(new ImageIcon(Constant.getHelpPicturePath(layer)));
		} else if ("next".equals(e.getComponent().getName())) {
			layer=(layer+1)%7;
			help.setIcon(new ImageIcon(Constant.getHelpPicturePath(layer)));
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("last".equals(e.getComponent().getName())) {
			last.setBackground(Color.WHITE);
		} else if ("next".equals(e.getComponent().getName())) {
			next.setBackground(Color.WHITE);
		}

	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("last".equals(e.getComponent().getName())) {
			last.setBackground(Theme.ButtonColor);
		}else if ("next".equals(e.getComponent().getName())) {
			next.setBackground(Theme.ButtonColor);
		}
	}

}
