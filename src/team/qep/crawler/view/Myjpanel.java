package team.qep.crawler.view;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Myjpanel extends JPanel {
	Image img;
	public Myjpanel(String path){ 
		img =Toolkit.getDefaultToolkit().getImage(path); 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img,(getWidth()-img.getWidth(this))/2,(getHeight()-img.getHeight(this))/2, null);// 绘制图片显示在面板正中央
//		g.drawImage(img,100,100, null);// 绘制图片显示在面板正中央

	} 
 }