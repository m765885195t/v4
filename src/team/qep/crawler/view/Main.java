package team.qep.crawler.view;

import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {
		try {
//		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			new UI2();
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
}	