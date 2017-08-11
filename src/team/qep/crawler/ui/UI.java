package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import team.qep.crawler.basic.Default;

public class UI implements MouseListener {
	private static final Point mousePosition = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	private boolean[] flag = new boolean[20];// 组件使用标志---true正在使用

	private JFrame ctlJF = new JFrame();
	private JPanel ctlJP = new JPanel();// 主面板
	private JLabel datetime = new JLabel();// 时间
	private JButton close = new JButton();// 关闭窗口
	private JButton zoom = new JButton(); // 缩放窗口

	private JPanel sidebarJP = new JPanel();// 侧边栏面板
	private JButton issueTask1 = new JButton();//发布精确与模糊任务
	private JPanel issueTask1JP = new IssueTasks1();
	private JButton issueTask2 = new JButton();// 发布即时任务
	private JPanel issueTask2JP = new IssueTasks2();
	private JButton taskControl = new JButton();// 任务控制
	private JPanel taskControlJP = new TaskControl();
	private JButton dataMonitoring = new JButton();// 数据监控
	private JPanel dataMonitoringJP = new DataMonitoring();
	private JButton dataDisplay = new JButton();// 数据展示
	private JPanel dataDisplayJP = new DataDisplay();
	private JButton historyRecord = new JButton();// 历史记录
	private JPanel historyRecordJP = new HistoryRecord();

	public UI() {
		this.Init();
		this.setBounds();
		this.setColour();
		this.listener();

		flag[Default.map.get("issueTask1")] = true;
		issueTask1.setBackground(new Color(20, 20, 20));
		issueTask1.setIcon(new ImageIcon(Default.getIcon("issueTask1w")));

		sidebarJP.add(issueTask1);
		sidebarJP.add(issueTask2);
		sidebarJP.add(taskControl);
		sidebarJP.add(dataMonitoring);
		sidebarJP.add(dataDisplay);
		sidebarJP.add(historyRecord);

		ctlJP.add(sidebarJP);
		ctlJP.add(issueTask1JP);
		ctlJP.add(zoom);
		ctlJP.add(close);
		ctlJP.add(datetime);

		ctlJF.setContentPane(ctlJP);
		ctlJF.setVisible(true);
	}

	private void Init() {
		Init.initJFrame(ctlJF, "ctlJF", Default.JFrameX, Default.JFrameY);
		Init.initJPanel(ctlJP, "ctlJP");
		Init.initJButton(close, "close");
		Init.initJButton(zoom, "zoom");
		Init.initJLable(datetime, "datetime");
		new Timer().schedule(new TimerTask() {
			public void run() {
				datetime.setText(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));
			}
		}, 0, 1000);
		
		Init.initJPanel(sidebarJP, "sidebarJP");
		Init.initJButton(issueTask1, "issueTask1");
		Init.initJPanel(issueTask1JP, "issueTask1JP");
		Init.initJButton(issueTask2, "issueTask2");
		Init.initJPanel(issueTask2JP, "issueTask2JP");
		Init.initJButton(taskControl, "taskControl");
		Init.initJPanel(taskControlJP, "taskControlJP");
		Init.initJButton(dataMonitoring, "dataMonitoring");
		Init.initJPanel(dataMonitoringJP, "dataMonitoringJP");
		Init.initJButton(dataDisplay, "dataDisplay");
		Init.initJPanel(dataDisplayJP, "dataDisplayJP");
		Init.initJButton(historyRecord, "historyRecord");
		Init.initJPanel(historyRecordJP, "historyRecordJP");
	}

	private void setBounds() {
		ctlJP.setBounds(0, 0, Default.JFrameX, Default.JFrameY);
		zoom.setBounds(Default.JFrameX - 60, 0, 30, 30);
		close.setBounds(Default.JFrameX - 30, 0, 30, 30);
		datetime.setBounds(Default.JFrameX - 300, 2, 300, 30);

		sidebarJP.setBounds(0, 0, 50, Default.JFrameY);
		issueTask1.setBounds(0, 0, 50, 50);
		issueTask1JP.setBounds(50, 30, Default.JFrameX-50, Default.JFrameX-30);
		issueTask2.setBounds(0, 50, 50, 50);
		issueTask2JP.setBounds(50, 30, Default.JFrameX-50, Default.JFrameX-30);
		taskControl.setBounds(0, 100, 50, 50);
		taskControlJP.setBounds(50, 30, Default.JFrameX-50, Default.JFrameX-30);
		dataMonitoring.setBounds(0, 150, 50, 50);
		dataMonitoringJP.setBounds(50, 30, Default.JFrameX-50, Default.JFrameX-30);
		dataDisplay.setBounds(0, 200, 50, 50);
		dataDisplayJP.setBounds(50, 30, Default.JFrameX-50, Default.JFrameX-30);
		historyRecord.setBounds(0, 250, 50, 50);
		historyRecordJP.setBounds(50, 30, Default.JFrameX-50, Default.JFrameX-30);
	}

	private void setColour() {
		// 默认其余组件都未使用
		for (Integer values : Default.map.values()) {
			flag[values] = false;
		}
		// 面板色
		ctlJP.setBackground(new Color(20, 20, 20));
		sidebarJP.setBackground(new Color(90, 90, 90));

		// 按键色与图片
		close.setBackground(new Color(20, 20, 20));
		close.setIcon(new ImageIcon(Default.getIcon("closeb")));
		zoom.setBackground(new Color(20, 20, 20));
		zoom.setIcon(new ImageIcon(Default.getIcon("zoomb")));
		issueTask1.setBackground(new Color(90, 90, 90));
		issueTask1.setIcon(new ImageIcon(Default.getIcon("issueTask1b")));
		issueTask2.setBackground(new Color(90, 90, 90));
		issueTask2.setIcon(new ImageIcon(Default.getIcon("issueTask2b")));
		taskControl.setBackground(new Color(90, 90, 90));
		taskControl.setIcon(new ImageIcon(Default.getIcon("taskControlb")));
		dataMonitoring.setBackground(new Color(90, 90, 90));
		dataMonitoring.setIcon(new ImageIcon(Default.getIcon("dataMonitoringb")));
		dataDisplay.setBackground(new Color(90, 90, 90));
		dataDisplay.setIcon(new ImageIcon(Default.getIcon("dataDisplayb")));
		historyRecord.setBackground(new Color(90, 90, 90));
		historyRecord.setIcon(new ImageIcon(Default.getIcon("historyRecordb")));
	}

	private void listener() {
		ctlJF.addMouseListener(this);
		ctlJF.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point tem = ctlJF.getLocation();
				ctlJF.setLocation(tem.x + e.getX() - mousePosition.x, tem.y + e.getY() - mousePosition.y);
			}
		});
		zoom.addMouseListener(this);
		close.addMouseListener(this);
		issueTask1.addMouseListener(this);
		issueTask2.addMouseListener(this);
		taskControl.addMouseListener(this);
		dataMonitoring.addMouseListener(this);
		dataDisplay.addMouseListener(this);
		historyRecord.addMouseListener(this);

	}

	private void toggleOptions(JButton jb,JPanel jp) {// 切换选项
		this.setColour();// 全部恢复为默认
		jb.setContentAreaFilled(true);// 显示背景
		jb.setBackground(new Color(20, 20, 20));// 设置背景色
		jb.setIcon(new ImageIcon(Default.getIcon(jb.getName() + "w")));// 设置图标icon
		flag[Default.map.get(jb.getName())] = true;// 已使用此组件
		ctlJF.remove(issueTask1JP);
		ctlJF.remove(issueTask2JP);
		ctlJF.remove(taskControlJP);
		ctlJF.remove(dataMonitoringJP);
		ctlJF.remove(dataDisplayJP);
		ctlJF.remove(historyRecordJP);
		ctlJF.add(jp);
		ctlJP.updateUI();
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("issueTask1".equals(e.getComponent().getName())) {
			this.toggleOptions(issueTask1,issueTask1JP);

		} else if ("issueTask2".equals(e.getComponent().getName())) {
			this.toggleOptions(issueTask2,issueTask2JP);

		} else if ("taskControl".equals(e.getComponent().getName())) {
			this.toggleOptions(taskControl,taskControlJP);

		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			this.toggleOptions(dataMonitoring,dataMonitoringJP);

		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			this.toggleOptions(dataDisplay,dataDisplayJP);

		} else if ("historyRecord".equals(e.getComponent().getName())) {
			this.toggleOptions(historyRecord,historyRecordJP);

		} else if ("zoom".equals(e.getComponent().getName())) {
			zoom.setIcon(new ImageIcon(Default.getIcon("zoom")));
			ctlJF.setExtendedState(JFrame.ICONIFIED);
		} else if ("close".equals(e.getComponent().getName())) {
			System.exit(0);// 退出程序
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
		if ("issueTask1".equals(e.getComponent().getName())) {
			issueTask1.setContentAreaFilled(false);
		} else if ("issueTask2".equals(e.getComponent().getName())) {
			issueTask2.setContentAreaFilled(false);
		} else if ("taskControl".equals(e.getComponent().getName())) {
			taskControl.setContentAreaFilled(false);
		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			dataMonitoring.setContentAreaFilled(false);
		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			dataDisplay.setContentAreaFilled(false);
		} else if ("historyRecord".equals(e.getComponent().getName())) {
			historyRecord.setContentAreaFilled(false);
		} else if ("ctlJF".equals(e.getComponent().getName())) { // 得到窗口当前的位置
			mousePosition.x = e.getX();
			mousePosition.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 释放
	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("issueTask1".equals(e.getComponent().getName())) {
			issueTask1.setIcon(new ImageIcon(Default.getIcon("issueTask1w")));
		} else if ("issueTask2".equals(e.getComponent().getName())) {
			issueTask2.setIcon(new ImageIcon(Default.getIcon("issueTask2w")));
		} else if ("taskControl".equals(e.getComponent().getName())) {
			taskControl.setIcon(new ImageIcon(Default.getIcon("taskControlw")));
		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			dataMonitoring.setIcon(new ImageIcon(Default.getIcon("dataMonitoringw")));
		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			dataDisplay.setIcon(new ImageIcon(Default.getIcon("dataDisplayw")));
		} else if ("historyRecord".equals(e.getComponent().getName())) {
			historyRecord.setIcon(new ImageIcon(Default.getIcon("historyRecordw")));
		} else if ("zoom".equals(e.getComponent().getName())) {
			zoom.setIcon(new ImageIcon(Default.getIcon("zoomw")));
		} else if ("close".equals(e.getComponent().getName())) {
			close.setIcon(new ImageIcon(Default.getIcon("closew")));
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("issueTask1".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(issueTask1.getName())]) {// 若未使用组件,恢复其颜色
				issueTask1.setIcon(new ImageIcon(Default.getIcon("issueTask1b")));
			}
		} else if ("issueTask2".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(issueTask2.getName())]) {
				issueTask2.setIcon(new ImageIcon(Default.getIcon("issueTask2b")));
			}
		} else if ("taskControl".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(taskControl.getName())]) {
				taskControl.setIcon(new ImageIcon(Default.getIcon("taskControlb")));
			}
		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(dataMonitoring.getName())]) {
				dataMonitoring.setIcon(new ImageIcon(Default.getIcon("dataMonitoringb")));
			}
		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(dataDisplay.getName())]) {
				dataDisplay.setIcon(new ImageIcon(Default.getIcon("dataDisplayb")));
			}
		} else if ("historyRecord".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(historyRecord.getName())]) {
				historyRecord.setIcon(new ImageIcon(Default.getIcon("historyRecordb")));
			}
		} else if ("zoom".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(zoom.getName())]) {
				zoom.setIcon(new ImageIcon(Default.getIcon("zoomb")));
			}
		} else if ("close".equals(e.getComponent().getName())) {
			if (!flag[Default.map.get(close.getName())]) {
				close.setIcon(new ImageIcon(Default.getIcon("closeb")));
			}
		}
	}

	public void p(String str) {
		System.out.println(str);
	}

	public void p(int i) {
		System.out.println(i);
	}

	public static void main(String[] args) {
		new UI();
	}
}
