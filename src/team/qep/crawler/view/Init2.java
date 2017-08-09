package team.qep.crawler.view;


import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

//所有UI界面的默认初始化

public class Init2{
	//---------窗口的初始化方式
	public static void Init2JFrame(JFrame jf,String str,int x,int y){//主窗口,窗口名,窗口大小
		 jf.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		jf.setLayout(null);//清除布局方式
//		jf.setResizable(false);//不可改变大小
		jf.setSize(x, y);
		jf.setName(str);//设置窗口名
		jf.setTitle(str);//窗口标题
		jf.setLocationRelativeTo(null);//起始位置为屏幕中央
		jf.setUndecorated(true);//去掉标题栏
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//隐藏当前窗口，并释放窗体占有的其他资源
//		jf.setVisible(true);//窗口可见

	}
	public static void Init2JDialog(JDialog jd,String str,int x,int y){//主窗口,窗口名,窗口大小
		jd.setLayout(null);//清除布局方式
//		jf.setResizable(false);//不可改变大小
		jd.setSize(x, y);
		jd.setName(str);//设置窗口名
		jd.setTitle(str);//窗口标题
		jd.setLocationRelativeTo(null);//起始位置为屏幕中央
		jd.setUndecorated(true);//去掉标题栏
//		jd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//隐藏当前窗口，并释放窗体占有的其他资源

	}

	//文本域初始化
	public static void Init2JTextArea (JTextArea in,String str){
//		in.setOpaque(false);//设为透明
		in.setFont(new Font("serif",1,20));//设置字体格式
		in.setName(str);//设置文本域的名字
		in.setForeground(Color.black);
//		in.setEditable(false);//屏蔽输入
//		in.setFocusable(false);//消除光标
		in.setLineWrap(true);//自动换行

	}
	//---------文本框的初始化
	public static void Init2JTextField(JTextField username,String str){
//		username.setOpaque(false);//设为透明
//		username.setBorder(null);//去掉边框
		username.setFont(new Font("微软雅黑",0, 17));//设置字体格式
		username.setName(str);//设置文本框的名字
		username.setForeground(Color.black);//设置前景色为灰
		username.setBackground(Color.white);//设置背景色为白
		username.setEditable(false);//屏蔽输入
//		username.setFocusable(false);//消除光标
//		username.setDocument(new MyDocument(16));//限制密码输入长度

	}

	//标签初始化
	public static void Init2JLable(JLabel jl,String str){
		jl.setName(str);//设置文本框的名字
//		jl.setFont(new Font("serif",0,20));
		jl.setFont(new Font("微软雅黑",0,20));
//		jl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//进入框为手
		jl.setForeground(Color.white);
	}
	//---------按键初始化
	public static void Init2JButton(JButton jb,String str){
		jb.setName(str);	
		jb.setFocusable(false);//消除光标
//		jb.setContentAreaFilled(false);//按键透明
		jb.setBorder(null);//去掉边框
		jb.setFont(new Font("微软雅黑",0,20));
		
//		jb.setBorder(BorderFactory.createLineBorder(new Color(51,153,204)));
		jb.setForeground(new Color(	51,153,204));
		jb.setBackground(new Color(245,245,245));
		jb.setAutoscrolls(true); 

	}
	//---------面板初始化
	public static void Init2JPanel(JPanel jp,String str){
//		jp.setOpaque(false);//设置面板透明
		jp.setName(str);//设置面板名
		jp.setLayout(null);//清楚面板的布局方式
	}
	//---------滚动面板初始化
	public static void Init2JScrollPane(JScrollPane jsp,String str){
		jsp.setOpaque(false);//设置面板透明
		jsp.getViewport().setOpaque(false);//组件透明
		jsp.setName(str);//设置面板名
//		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		jsp.setLayout(null);//清楚面板的布局方式
	}
	//---------表格初始化
	public static void Init2JTable(JTable jt,String str){
		jt.setName(str);
		jt.setFont(new Font("serif",0,18));//设置字体
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		jt.setDefaultRenderer(Object.class, tcr);
//		jt.setForeground(Color.white);//设置字体颜色
		jt.setOpaque(false);//表格透明
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        JTableHeader header = jt.getTableHeader();//获取头部   
        header.setPreferredSize(new Dimension(30, 28));   //大小间距
        header.setOpaque(false);//设置头部为透明  
        header.getTable().setOpaque(false);//设置头部里面的表格透明  
        header.setDefaultRenderer(renderer);  
        TableCellRenderer headerRenderer = header.getDefaultRenderer();   
        if (headerRenderer instanceof JComponent){
        	//文字居中
            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);   
            ((JComponent) headerRenderer).setOpaque(false);   
        }
		jt.setRowHeight(40);       
		jt.getTableHeader().setReorderingAllowed(false);//不可拖动列
	    jt.setIntercellSpacing(new Dimension(0, 0));   

	}
	
	public static void Init2JComboBox(JComboBox choice, String str) {
		choice.setName(str);
		choice.setFont(new Font("serif",0,17));

	//		choice.setBackground(Color.red);
	}
	public static void Init2JFileChooser(JFileChooser JFileChooser, String str) {
		JFileChooser.setName(str);
	}
	//进度条初始化
	public static void Init2JProgressBar(JProgressBar jpb,String str){
//		jp.setOpaque(false);
		jpb.setName(str);
		jpb.setStringPainted(true);// 设置进度条上的字符串显示，false则不能显示  
		jpb.setVisible(true);
	}
}
