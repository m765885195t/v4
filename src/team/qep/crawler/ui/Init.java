package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;


public class Init{
	//---------窗口的初始化方式
	public static void initJFrame(JFrame jf,String str,int x,int y){//主窗口,窗口名,窗口大小
		jf.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		jf.setLayout(null);//清除布局方式
//		jf.setResizable(false);//不可改变大小
		jf.setSize(x, y);
		jf.setName(str);//设置窗口名
		jf.setTitle(str);//窗口标题
		jf.setLocationRelativeTo(null);//起始位置为屏幕中央
		jf.setUndecorated(true);//去掉标题栏
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//隐藏当前窗口，并释放窗体占有的其他资源

	}
	public static void initJDialog(JDialog jd,String str,int x,int y){//主窗口,窗口名,窗口大小
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
	public static void initJTextArea (JTextArea jta,String str){
//		jta.setOpaque(false);//设为透明
		jta.setFont(new Font("serif",1,20));//设置字体格式
		jta.setName(str);//设置文本域的名字
		jta.setForeground(Color.black);
//		jta.setEditable(false);//屏蔽输入
//		jta.setFocusable(false);//消除光标
		jta.setLineWrap(true);//自动换行

	}
	//---------文本框的初始化
	public static void initJTextField(JTextField jtf,String str){
//		jtf.setOpaque(false);//设为透明
		jtf.setBorder(null);//去掉边框
		jtf.setFont(new Font("微软雅黑",1, 22));//设置字体格式
		jtf.setName(str);//设置文本框的名字
		jtf.setForeground(Color.black);//设置前景色为灰
		jtf.setBackground(Color.white);//设置背景色为白
//		jtf.setEditable(false);//屏蔽输入
//		jtf.setFocusable(false);//消除光标
//		jtf.setDocument(new MyDocument(16));//限制密码输入长度
	}

	//标签初始化
	public static void initJLable(JLabel jl,String str){
		jl.setName(str);//设置文本框的名字
//		jl.setFont(new Font("serif",0,20));
		jl.setFont(new Font("微软雅黑",0,20));
//		jl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//进入框为手
		jl.setForeground(Color.white);
	}
	//---------按键初始化
	public static void initJButton(JButton jb,String str){
		jb.setName(str);	
		jb.setFocusable(false);//消除光标
//		jb.setContentAreaFilled(false);//按键透明
		jb.setBorder(null);//去掉边框
		jb.setFont(new Font("微软雅黑",0,20));
		
//		jb.setBorder(BorderFactory.createLineBorder(new Color(51,153,204)));
//		jb.setForeground(new Color(	51,153,204));
//		jb.setBackground(new Color(245,245,245));
//		jb.setAutoscrolls(true); 

	}
	//---------面板初始化
	public static void initJPanel(JPanel jp,String str){
//		jp.setOpaque(false);//设置面板透明
		jp.setName(str);//设置面板名
		jp.setLayout(null);//清楚面板的布局方式
	}
	//---------滚动面板初始化
	public static void initJScrollPane(JScrollPane jsp,String str){
		jsp.setOpaque(false);//设置面板透明
		jsp.getViewport().setOpaque(false);//组件透明
		jsp.setName(str);//设置面板名
//		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		jsp.setLayout(null);//清楚面板的布局方式
	}
	//---------表格初始化
	public static void initJTable(JTable jt,String str){
		//表设置
		jt.setName(str);
		jt.setFont(new Font("serif",0,21));//设置表格字体
		jt.setRowHeight(40); //表格间距      
		
		//表格主体渲染器
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);//水平居中对齐
		jt.setDefaultRenderer(Object.class, tcr);

		//表头设置
        JTableHeader header = jt.getTableHeader();//获取头部   
        header.setReorderingAllowed(false);//不可拖动列
        header.setForeground(Color.blue);//字体颜色
        header.setFont(new Font("微软雅黑", 0, 20));

		//表头渲染器
//		DefaultTableCellRenderer head = new DefaultTableCellRenderer();// 设置table内容居中
//		head.setOpaque(false); 
//		head.setHorizontalAlignment(JLabel.CENTER);//水平居中对齐
//        header.setDefaultRenderer(head);//为表头设置渲染器
	}

	public static void initJComboBox(JComboBox choice, String str) {
		choice.setName(str);
		choice.setFont(new Font("微软雅黑",0,20));
	}
	public static void initJFileChooser(JFileChooser JFileChooser, String str) {
		JFileChooser.setName(str);
	}
}
	