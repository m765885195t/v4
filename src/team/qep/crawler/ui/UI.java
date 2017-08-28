package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import team.qep.crawler.util.Constant;

public class UI implements MouseListener {
	private static final Point mousePosition = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	private boolean[] flag = new boolean[100];// 组件使用标志---true正在使用

	private JFrame ctlJF = new JFrame();
	private JPanel ctlJP = new JPanel();// 主面板
	private JLabel datetime = new JLabel();// 时间
	private JButton close = new JButton();// 关闭窗口
	private JButton zoom = new JButton(); // 缩放窗口

	private JLabel prompt = new JLabel();// 提示信息

	private JPanel sidebarJP = new JPanel();// 侧边栏面板
	private JButton issueTask1 = new JButton();// 发布精确与模糊任务
	private JPanel issueTask1JP = new IssueTasks1();
	private JButton issueTask2 = new JButton();// 发布即时任务
	private JPanel issueTask2JP = new IssueTasks2();
	private JButton resourceScheduling = new JButton();// 资源监控
	private JPanel resourceSchedulingJP = new ResourceScheduling();
	private JButton taskControl = new JButton();// 任务控制
	private JPanel taskControlJP = new TaskControl();
	private JButton dataMonitoring = new JButton();// 数据监控
	private JPanel dataMonitoringJP = new DataMonitoring();
	private JButton dataDisplay = new JButton();// 数据展示
	private JPanel dataDisplayJP = new DataDisplay();
	private JButton historyRecord = new JButton();// 历史记录
	private JPanel historyRecordJP = new HistoryRecord();
	private JButton helpDescription = new JButton();//帮助说明
	private JPanel helpDescriptionJP = new HelpDescription();
	private JButton setting = new JButton();// 设置
	private JPanel settingJP = new Setting();
	private JButton feedback = new JButton();// 联系反馈
	private JPanel feedbackJP = new Feedback();

	public UI() {
		this.Init();
		this.setBounds();
		this.setColour();
		this.listener();

		flag[Constant.UIKeyValue.get("issueTask1")] = true;
		issueTask1.setBackground(new Color(20, 20, 20));
		issueTask1.setIcon(Constant.getIcon("issueTask1w"));

		sidebarJP.add(issueTask1);
		sidebarJP.add(issueTask2);
		sidebarJP.add(resourceScheduling);
		sidebarJP.add(taskControl);
		sidebarJP.add(dataMonitoring);
		sidebarJP.add(dataDisplay);
		sidebarJP.add(historyRecord);
		sidebarJP.add(helpDescription);
		sidebarJP.add(setting);
		sidebarJP.add(feedback);

		ctlJP.add(sidebarJP);
		ctlJP.add(issueTask1JP);
		ctlJP.add(zoom);
		ctlJP.add(close);
		ctlJP.add(datetime);

		ctlJF.setContentPane(ctlJP);
		ctlJF.setVisible(true);
	}

	private void Init() {
		Init.initJFrame(ctlJF, "ctlJF", Constant.JFrame_Width, Constant.JFrame_Height);
		Init.initJPanel(ctlJP, "ctlJP");
		Init.initJButton(close, "close");
		Init.initJButton(zoom, "zoom");
		Init.initJLable(datetime, "datetime");
		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				datetime.setText(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));
			}
		}, 0, 1000);
		Init.initJLable(prompt, "prompt");
		prompt.setFont(new Font("微软雅黑", 0, 23));
		prompt.setOpaque(true);// 不透明

		Init.initJPanel(sidebarJP, "sidebarJP");
		Init.initJButton(issueTask1, "issueTask1");
		Init.initJPanel(issueTask1JP, "issueTask1JP");
		Init.initJButton(issueTask2, "issueTask2");
		Init.initJPanel(issueTask2JP, "issueTask2JP");
		Init.initJButton(resourceScheduling, "resourceScheduling");
		Init.initJPanel(resourceSchedulingJP, "resourceSchedulingJP");
		Init.initJButton(taskControl, "taskControl");
		Init.initJPanel(taskControlJP, "taskControlJP");
		Init.initJButton(dataMonitoring, "dataMonitoring");
		Init.initJPanel(dataMonitoringJP, "dataMonitoringJP");
		Init.initJButton(dataDisplay, "dataDisplay");
		Init.initJPanel(dataDisplayJP, "dataDisplayJP");
		Init.initJButton(historyRecord, "historyRecord");
		Init.initJPanel(historyRecordJP, "historyRecordJP");
		Init.initJButton(helpDescription, "helpDescription");
		Init.initJPanel(helpDescriptionJP, "helpDescriptionJP");
		Init.initJButton(setting, "setting");
		Init.initJPanel(settingJP, "settingJP");
		Init.initJButton(feedback, "feedback");
		Init.initJPanel(feedbackJP, "feedbackJP");
	}

	private void setBounds() {
		ctlJP.setBounds(0, 0, Constant.JFrame_Width, Constant.JFrame_Height);
		zoom.setBounds(Constant.JFrame_Width - 60, 0, 30, 30);
		close.setBounds(Constant.JFrame_Width - 30, 0, 30, 30);
		datetime.setBounds(Constant.JFrame_Width - 340, 2, 300, 30);

		sidebarJP.setBounds(0, 0, 50, Constant.JFrame_Height);
		issueTask1.setBounds(0, 0 * 50, 50, 50);
		issueTask2.setBounds(0, 1 * 50, 50, 50);
		resourceScheduling.setBounds(0, 2 * 50, 50, 50);
		taskControl.setBounds(0, 3 * 50, 50, 50);
		dataMonitoring.setBounds(0, 4 * 50, 50, 50);
		historyRecord.setBounds(0, 5 * 50, 50, 50);
		dataDisplay.setBounds(0, 6 * 50, 50, 50);
		feedback.setBounds(0, Constant.JFrame_Height - 3 * 50, 50, 50);
		helpDescription.setBounds(0, Constant.JFrame_Height - 2 * 50, 50, 50);
		setting.setBounds(0, Constant.JFrame_Height - 1 * 50, 50, 50);

		issueTask1JP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		issueTask2JP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		resourceSchedulingJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		taskControlJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		dataMonitoringJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		dataDisplayJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		historyRecordJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		feedbackJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		helpDescriptionJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
		settingJP.setBounds(50, 30, Constant.JFrame_Width - 50, Constant.JFrame_Width - 30);
	}

	private void setColour() {
		// 默认其余组件都未使用
		for (Integer values : Constant.UIKeyValue.values()) {
			flag[values] = false;
		}
		// 面板色
		ctlJP.setBackground(Theme.PanelColor);
		sidebarJP.setBackground(Theme.SidebarColor);
		prompt.setBackground(Theme.SidebarColor);// 提示的标签色
		// 按键色与图片
		close.setBackground(Theme.PanelColor);
		close.setIcon(Constant.getIcon("closeb"));
		zoom.setBackground(Theme.PanelColor);
		zoom.setIcon(Constant.getIcon("zoomb"));
		issueTask1.setBackground(Theme.SidebarColor);
		issueTask1.setIcon(Constant.getIcon("issueTask1b"));
		issueTask2.setBackground(Theme.SidebarColor);
		issueTask2.setIcon(Constant.getIcon("issueTask2b"));
		resourceScheduling.setBackground(Theme.SidebarColor);
		resourceScheduling.setIcon(Constant.getIcon("resourceSchedulingb"));
		taskControl.setBackground(Theme.SidebarColor);
		taskControl.setIcon(Constant.getIcon("taskControlb"));
		dataMonitoring.setBackground(Theme.SidebarColor);
		dataMonitoring.setIcon(Constant.getIcon("dataMonitoringb"));
		dataDisplay.setBackground(Theme.SidebarColor);
		dataDisplay.setIcon(Constant.getIcon("dataDisplayb"));
		historyRecord.setBackground(Theme.SidebarColor);
		historyRecord.setIcon(Constant.getIcon("historyRecordb"));
		feedback.setBackground(Theme.SidebarColor);
		feedback.setIcon(Constant.getIcon("feedbackb"));
		helpDescription.setBackground(Theme.SidebarColor);
		helpDescription.setIcon(Constant.getIcon("helpDescriptionb"));
		setting.setBackground(Theme.SidebarColor);
		setting.setIcon(Constant.getIcon("settingb"));
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
		resourceScheduling.addMouseListener(this);
		taskControl.addMouseListener(this);
		dataMonitoring.addMouseListener(this);
		dataDisplay.addMouseListener(this);
		historyRecord.addMouseListener(this);
		feedback.addMouseListener(this);
		helpDescription.addMouseListener(this);
		setting.addMouseListener(this);
	}

	private void toggleUIKeyValue(JButton jb, JPanel jp) {// 切换选项
		this.setColour();// 全部恢复为默认
		jb.setContentAreaFilled(true);// 显示背景
		jb.setBackground(new Color(20, 20, 20));// 设置背景色
		jb.setIcon(Constant.getIcon(jb.getName() + "w"));// 设置图标icon
		flag[Constant.UIKeyValue.get(jb.getName())] = true;// 已使用此组件
		ctlJP.remove(issueTask1JP);
		ctlJP.remove(issueTask2JP);
		ctlJP.remove(resourceSchedulingJP);
		ctlJP.remove(taskControlJP);
		ctlJP.remove(dataMonitoringJP);
		ctlJP.remove(dataDisplayJP);
		ctlJP.remove(feedbackJP);
		ctlJP.remove(historyRecordJP);
		ctlJP.remove(helpDescriptionJP);
		ctlJP.remove(settingJP);
		ctlJP.add(jp);
		ctlJP.updateUI();
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("issueTask1".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(issueTask1, issueTask1JP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("issueTask2".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(issueTask2, issueTask2JP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("resourceScheduling".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(resourceScheduling, resourceSchedulingJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("taskControl".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(taskControl, taskControlJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(dataMonitoring, dataMonitoringJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(dataDisplay, dataDisplayJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("historyRecord".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(historyRecord, historyRecordJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("feedback".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(feedback, feedbackJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("helpDescription".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(helpDescription, helpDescriptionJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("setting".equals(e.getComponent().getName())) {
			this.toggleUIKeyValue(setting, settingJP);
			ctlJF.getLayeredPane().remove(prompt);
			ctlJF.repaint();
		} else if ("zoom".equals(e.getComponent().getName())) {
			zoom.setIcon(Constant.getIcon("zoom"));
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
		} else if ("resourceScheduling".equals(e.getComponent().getName())) {
			resourceScheduling.setContentAreaFilled(false);
		} else if ("taskControl".equals(e.getComponent().getName())) {
			taskControl.setContentAreaFilled(false);
		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			dataMonitoring.setContentAreaFilled(false);
		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			dataDisplay.setContentAreaFilled(false);
		} else if ("historyRecord".equals(e.getComponent().getName())) {
			historyRecord.setContentAreaFilled(false);
		} else if ("feedback".equals(e.getComponent().getName())) {
			feedback.setContentAreaFilled(false);
		} else if ("helpDescription".equals(e.getComponent().getName())) {
			helpDescription.setContentAreaFilled(false);
		}  else if ("setting".equals(e.getComponent().getName())) {
			setting.setContentAreaFilled(false);
		} else if ("ctlJF".equals(e.getComponent().getName())) { // 得到窗口当前的位置
			mousePosition.x = e.getX();
			mousePosition.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 释放
	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("issueTask1".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(issueTask1.getName())]) {// 若未使用组件,显示提示
				issueTask1.setIcon(Constant.getIcon("issueTask1w"));
				prompt.setBounds(50, 0, 125, 50);
				prompt.setText("全 网 爬 取");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("issueTask2".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(issueTask2.getName())]) {// 只使用的组件
				issueTask2.setIcon(Constant.getIcon("issueTask2w"));
				prompt.setBounds(50, 1 * 50, 125, 50);
				prompt.setText("即 时 爬 取");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("resourceScheduling".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(resourceScheduling.getName())]) {// 只让为使用的组件
				resourceScheduling.setIcon(Constant.getIcon("resourceSchedulingw"));
				prompt.setBounds(50, 2 * 50, 125, 50);
				prompt.setText("资 源 配 置");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("taskControl".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(taskControl.getName())]) {// 只让为使用的组件
				taskControl.setIcon(Constant.getIcon("taskControlw"));
				prompt.setBounds(50, 3 * 50, 125, 50);
				prompt.setText("任 务 中 心");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(dataMonitoring.getName())]) {// 只让为使用的组件
				dataMonitoring.setIcon(Constant.getIcon("dataMonitoringw"));
				prompt.setBounds(50, 4 * 50, 125, 50);
				prompt.setText("数 据 监 控");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(dataDisplay.getName())]) {// 只让为使用的组件
				dataDisplay.setIcon(Constant.getIcon("dataDisplayw"));
				prompt.setBounds(50, 6 * 50, 125, 50);
				prompt.setText("数 据 展 示");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("historyRecord".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(historyRecord.getName())]) {// 只让为使用的组件
				historyRecord.setIcon(Constant.getIcon("historyRecordw"));
				prompt.setBounds(50, 5 * 50, 125, 50);
				prompt.setText("数 据 统 计");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("feedback".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(feedback.getName())]) {// 只让为使用的组件
				feedback.setIcon(Constant.getIcon("feedbackw"));
				prompt.setBounds(50, Constant.JFrame_Height - 3 * 50, 125, 50);
				prompt.setText("功 能 建 议");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		}else if ("helpDescription".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(helpDescription.getName())]) {// 只让为使用的组件
				helpDescription.setIcon(Constant.getIcon("helpDescriptionw"));
				prompt.setBounds(50, Constant.JFrame_Height - 2 * 50, 125, 50);
				prompt.setText("使 用 说 明");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		}else if ("setting".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(setting.getName())]) {// 只让为使用的组件
				setting.setIcon(Constant.getIcon("settingw"));
				prompt.setBounds(50, Constant.JFrame_Height - 1 * 50, 125, 50);
				prompt.setText("设 置 中 心");
				ctlJF.getLayeredPane().add(prompt, Integer.MAX_VALUE + 1);
			}
		} else if ("zoom".equals(e.getComponent().getName())) {
			zoom.setIcon(Constant.getIcon("zoomw"));
		} else if ("close".equals(e.getComponent().getName())) {
			close.setIcon(Constant.getIcon("closew"));
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("issueTask1".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(issueTask1.getName())]) {// 若未使用组件,恢复其颜色
				issueTask1.setIcon(Constant.getIcon("issueTask1b"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();
			} else {
				issueTask1.setContentAreaFilled(true);// 显示背景
				issueTask1.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("issueTask2".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(issueTask2.getName())]) {
				issueTask2.setIcon(Constant.getIcon("issueTask2b"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();
			} else {
				issueTask2.setContentAreaFilled(true);// 显示背景
				issueTask2.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("resourceScheduling".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(resourceScheduling.getName())]) {
				resourceScheduling.setIcon(Constant.getIcon("resourceSchedulingb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				resourceScheduling.setContentAreaFilled(true);// 显示背景
				resourceScheduling.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("taskControl".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(taskControl.getName())]) {
				taskControl.setIcon(Constant.getIcon("taskControlb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				taskControl.setContentAreaFilled(true);// 显示背景
				taskControl.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("dataMonitoring".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(dataMonitoring.getName())]) {
				dataMonitoring.setIcon(Constant.getIcon("dataMonitoringb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				dataMonitoring.setContentAreaFilled(true);// 显示背景
				dataMonitoring.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("dataDisplay".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(dataDisplay.getName())]) {
				dataDisplay.setIcon(Constant.getIcon("dataDisplayb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				dataDisplay.setContentAreaFilled(true);// 显示背景
				dataDisplay.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("historyRecord".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(historyRecord.getName())]) {
				historyRecord.setIcon(Constant.getIcon("historyRecordb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				historyRecord.setContentAreaFilled(true);// 显示背景
				historyRecord.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("feedback".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(feedback.getName())]) {
				feedback.setIcon(Constant.getIcon("feedbackb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				feedback.setContentAreaFilled(true);// 显示背景
				feedback.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("helpDescription".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(helpDescription.getName())]) {
				helpDescription.setIcon(Constant.getIcon("helpDescriptionb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				helpDescription.setContentAreaFilled(true);// 显示背景
				helpDescription.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("setting".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(setting.getName())]) {
				setting.setIcon(Constant.getIcon("settingb"));
				ctlJF.getLayeredPane().remove(prompt);
				ctlJF.repaint();

			} else {
				setting.setContentAreaFilled(true);// 显示背景
				setting.setBackground(new Color(20, 20, 20));// 设置背景色
			}
		} else if ("zoom".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(zoom.getName())]) {
				zoom.setIcon(Constant.getIcon("zoomb"));
			}
		} else if ("close".equals(e.getComponent().getName())) {
			if (!flag[Constant.UIKeyValue.get(close.getName())]) {
				close.setIcon(Constant.getIcon("closeb"));
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
		
		if(Constant.exportSettings()){//设置导入成功
			new UI();
		}
	}
}
