package team.qep.crawler.view;

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

public class UI2 implements MouseListener {
	private JFrame ctlJFrame = new JFrame();
	private static Point mousePosition = new Point();// 全局的位置变量，用于表示鼠标在窗口上的位置
	private Timer refresh=null;//定时刷寻器
	private Timer downloaddata=null;

	private Myjpanel ctlJPanel = new Myjpanel("");//主面板
//	private Myjpanel logo = new Myjpanel(Default.getDynamicImagePath(21));//logo
	private JLabel datetime = new JLabel();
	private Myjpanel sidebarJPanel = new Myjpanel("");//侧边栏面板
	private JButton closeWindow = new JButton();//关闭 
	private JButton zoom = new JButton(); //缩放窗口
	
	private boolean[] flag=new boolean[20];
	//组件使用标志下标为以下顺序从０开始---true正在使用
	/*0----task   1----log      2----download  3----help    4----setting
	 *5----delete 6----viewdata                7            8----zoom  9----close 
	 *15---下载
	 **/
	private JButton task = new JButton();//侧边栏--------任务
	private JButton log = new JButton();// -------log
	private JButton download  = new JButton();//------download
	private JButton help  = new JButton();//------help
	private JButton setting  = new JButton();//------setting
	private JButton delete  = new JButton();//------delete
	private JButton viewdata  = new JButton();//------viewdata

	private Myjpanel taskJPanel = new Myjpanel("");//任务面板
	private JTextArea distributionTask = new JTextArea();//分发任务集
	private JScrollPane distributionJSPanel = new JScrollPane(distributionTask);
	private JTable supportedJTable = new JTable(new DefaultTableModel(Operationstring.toTwoimensional(Default.taskSet),new String[]{"URL"}){
		public boolean isCellEditable(int row,int column){ 
			return false;
		}  
	});
	private JScrollPane supportedJSPanel = new JScrollPane(supportedJTable);
	private JButton release= new JButton();//发布任务
	private JButton startTask= new JButton();//开始任务
	private String[][] runData ;////在运行任务
	private DefaultTableModel runModel;
	private JTable runJTable = new JTable();
	private JScrollPane runJSPanel = new JScrollPane(runJTable);
	private JButton endTask= new JButton();//结束任务
	
	private Myjpanel logJPanel = new Myjpanel("");//log面板
	private JPanel lineChart=Crawlergraph.createLineChart();
	private JButton refreshData= new JButton();//刷新数据
	private JButton picture= new JButton();//刷新数据
	
	private Myjpanel downloadPanel = new Myjpanel("");//download面板
	private DefaultTableModel downloadHistoryModel;
	private JTable downloadHistoryJTable = new JTable();
	private JScrollPane downloadHistorySPanel = new JScrollPane(downloadHistoryJTable);
	private JComboBox<String> selectDownload = new JComboBox<String>();//下载类别
	private JComboBox<String> detailed = new JComboBox<String>();//下载类别
	private JButton browse = new JButton();//浏览
	private JProgressBar progress=new JProgressBar(); 
	private JTextField savePath = new JTextField();//存储路径框
	private JButton downloadData= new JButton();//存储数据
	private JButton cancelDownloadData= new JButton();//取消存储数据

	private Myjpanel deletePanel = new Myjpanel("");
	private String[][] oneData ;//一级分类
	private DefaultTableModel oneModel;
	private JTable oneJTable = new JTable();
	private JScrollPane oneJSPanel = new JScrollPane(oneJTable);
	private String[][] twoData ;//二级分类
	private DefaultTableModel twoModel;
	private JTable twoJTable = new JTable();
	private JScrollPane twoJSPanel = new JScrollPane(twoJTable);
	private JButton emptydata= new JButton();//清空数据
	private JButton emptypicture= new JButton();//清空进度图

	private Myjpanel viewdataPanel = new Myjpanel("");//查看数据
	
	private Myjpanel helpPanel = new Myjpanel("");
	
	private Myjpanel settingPanel = new Myjpanel("");
	private JButton saveSetting = new JButton("");
	private JComboBox<String> refreshInterval = new JComboBox<String>();//下载类别



	public UI2(){
		this.Init2();
		this.setSize();
		this.setColour();
		this.listener();
		this.info();
		flag[0]=true;
		task.setBackground(new Color(20,20,20));
		task.setIcon(new ImageIcon(Default.getDynamicIconPath("task1")));//任务icon

		
		sidebarJPanel.add(taskJPanel);
		sidebarJPanel.add(task);
		sidebarJPanel.add(log);
		sidebarJPanel.add(download);
		sidebarJPanel.add(help);
		sidebarJPanel.add(setting);
		sidebarJPanel.add(delete);
		sidebarJPanel.add(viewdata);

		taskJPanel.add(release);
		taskJPanel.add(startTask);
		taskJPanel.add(distributionJSPanel);
		taskJPanel.add(supportedJSPanel);
		taskJPanel.add(runJSPanel);
		taskJPanel.add(endTask);
		
		logJPanel.add(refreshData);
		logJPanel.add(lineChart);
		logJPanel.add(picture);
//		logJPanel.add(emptydata);
		
		downloadPanel.add(browse);
		downloadPanel.add(downloadHistorySPanel);
		downloadPanel.add(savePath);
		downloadPanel.add(selectDownload);
		downloadPanel.add(detailed);
		downloadPanel.add(progress);
		downloadPanel.add(downloadData);
		downloadPanel.add(cancelDownloadData);
		
		deletePanel.add(emptydata);
		deletePanel.add(emptypicture);
		deletePanel.add(oneJSPanel);
		deletePanel.add(twoJSPanel);
	

//		helpPanel.add(downloadData);
	
		settingPanel.add(saveSetting);
		settingPanel.add(refreshInterval);
	
//		viewdataPanel.add();


		ctlJPanel.add(sidebarJPanel);
		ctlJPanel.add(taskJPanel);
		ctlJPanel.add(zoom);		
		ctlJPanel.add(closeWindow);	
		ctlJPanel.add(datetime);
//		ctlJPanel.add(logo);
		ctlJFrame.setContentPane(ctlJPanel);
		ctlJFrame.setVisible(true);
	}

	private void info(){
		JLabel info1 = new JLabel("Supported");
		JLabel info2 = new JLabel("Release");
		JLabel info3 = new JLabel("Running");
		JLabel info4 = new JLabel("Download History");
		JLabel info5 = new JLabel("Drefresh Rate:");
		JLabel info6 = new JLabel();//帮助的图片
		JLabel info7 = new JLabel("Delete the crawled URL data");
		
		Init2.Init2JLable(info1, "info1");
		info1.setFont(new Font("微软雅黑",0,26));
		info1.setBounds(75, 10, 200, 50);
		info1.setForeground(new Color(0,255,255));
		
		Init2.Init2JLable(info2, "info2");
		info2.setFont(new Font("微软雅黑",0,26));
		info2.setBounds(350, 10, 200, 50);
		info2.setForeground(new Color(	230,210,250));
		
		Init2.Init2JLable(info3, "info3");
		info3.setFont(new Font("微软雅黑",0,25));
		info3.setBounds(680, 10, 200, 50);
		info3.setForeground(new Color(0,255,0));
		
		Init2.Init2JLable(info4, "info4");
		info4.setFont(new Font("微软雅黑",0,25));
		info4.setBounds(400, 0, 300, 30);
		info4.setForeground(new Color(0,255,0));
		
		Init2.Init2JLable(info5, "info5");
		info5.setFont(new Font("微软雅黑",0,25));
		info5.setBounds(50, 50, 300, 30);
		info5.setForeground(new Color(0,255,0));
		
		Init2.Init2JLable(info6, "info6");
		info6.setIcon(new  ImageIcon(Default.getDynamicIconPath("helpinfo")));
		info6.setBounds(400, 0, 300, 200);
		
		Init2.Init2JLable(info7, "info7");
		info7.setFont(new Font("微软雅黑",0,26));
		info7.setBounds(140, 10, 400, 50);
		info7.setForeground(new Color(0,255,0));

		taskJPanel.add(info1);
		taskJPanel.add(info2);
		taskJPanel.add(info3);
		downloadPanel.add(info4);
		settingPanel.add(info5);
		helpPanel.add(info6);
		deletePanel.add(info7);
		
	}
	
	private void Init2(){
		Init2.Init2JFrame(ctlJFrame, "ctlJFrame", Default.JFrameX, Default.JFrameY);
		Init2.Init2JPanel(ctlJPanel, "ctlJPanel");
//		Init2.Init2JPanel(logo, "logo", 100,100);
		Init2.Init2JButton(closeWindow, "closeWindow");
		Init2.Init2JButton(zoom, "zoom");
		Init2.Init2JLable(datetime, "datetime");
	    datetime.setCursor(Cursor.getPredefinedCursor(0));
	    new Timer().schedule(new TimerTask() {
            public void run() {       
            	datetime.setText(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));   
            }      
        },0,1000); 
		Init2.Init2JPanel(sidebarJPanel, "sidebarJPanel");
		Init2.Init2JButton(task, "task");
		Init2.Init2JButton(log, "log");
		Init2.Init2JButton(download, "download");
		Init2.Init2JButton(help, "help");
		Init2.Init2JButton(setting, "setting");
		Init2.Init2JButton(delete, "delete");
		Init2.Init2JButton(viewdata, "viewdata");
		
		Init2.Init2JPanel(taskJPanel, "taskJPanel");
		Init2.Init2JTable(supportedJTable, "supportedJTable");
		Init2.Init2JTextArea(distributionTask, "distributionTask");
		Init2.Init2JScrollPane(distributionJSPanel, "distributionJSPanel");
		Init2.Init2JButton(release, "release");
		Init2.Init2JButton(startTask, "startTask");
		Init2.Init2JTable(runJTable, "runJTable");
		runData = Task.getRunningTask(5);
		runModel = new DefaultTableModel(runData,Default.getTaskViewJColumnNames()){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		};
		runJTable.setModel(runModel);
		Init2.Init2JScrollPane(runJSPanel, "runJSPanel");
		Init2.Init2JButton(endTask, "endTask");

		Init2.Init2JPanel(logJPanel, "logJPanel");
		Init2.Init2JButton(refreshData, "refreshData");
		Init2.Init2JButton(picture, "picture");
		
		Init2.Init2JPanel(downloadPanel, "downloadPanel");
		Init2.Init2JButton(downloadData, "downloadData");
		Init2.Init2JButton(cancelDownloadData, "cancelDownloadData");
		Init2.Init2JTable(downloadHistoryJTable, "downloadHistoryJTable");
		downloadHistoryModel = new DefaultTableModel(Log.getDownloadHistory(),new String[]{"DateTime","Url","Path"}){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		};
		downloadHistoryJTable.setModel(downloadHistoryModel);
		Init2.Init2JScrollPane(downloadHistorySPanel, "downloadHistorySPanel");
		Init2.Init2JProgressBar(progress, "progress");
		Init2.Init2JButton(browse, "browse");
		Init2.Init2JComboBox(selectDownload, "selectDownload");
		selectDownload.addItem("ALL");
		selectDownload.addItem("E-Commerce");
		selectDownload.addItem("News");
		selectDownload.addItem("Blog");
		Init2.Init2JComboBox(detailed, "detailed");
		detailed.addItem("ALL");
		String[] url=Default.taskSet;
		for(String str:url){
			detailed.addItem(str);
		}
	
		Init2.Init2JTextField(savePath, "savePath");

		Init2.Init2JPanel(deletePanel, "deletePanel");
		Init2.Init2JButton(emptydata, "emptydata");
		Init2.Init2JButton(emptypicture, "emptypicture");
		Init2.Init2JTable(oneJTable, "oneJTable");
		Init2.Init2JTable(twoJTable, "twoJTable");
		Init2.Init2JScrollPane(oneJSPanel, "oneJSPanel");
		Init2.Init2JScrollPane(twoJSPanel, "twoJSPanel");
		oneData = Data.getOneData();
		oneModel = new DefaultTableModel(oneData,new String[]{"Category"}){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		};
		oneJTable.setModel(oneModel);
		oneJTable.setRowSelectionInterval(0,0);//默认选择第０行
		twoData = Data.getTwoData(0,Default.taskSet.length);
		twoModel = new DefaultTableModel(twoData,new String[]{"URL"}){
			public boolean isCellEditable(int row,int column){ 
				return false;
			}  
		};
		twoJTable.setModel(twoModel);
		
		Init2.Init2JPanel(helpPanel, "helpPanel");
		
		Init2.Init2JPanel(settingPanel, "settingPanel");
		Init2.Init2JButton(saveSetting, "saveSetting");
		Init2.Init2JComboBox(refreshInterval, "refreshInterval");
		Default.setRefreshrate(0);//默认为不刷新
		refreshInterval.addItem("Do not refresh");
		refreshInterval.addItem("05 minute");
		refreshInterval.addItem("01 minute");
		refreshInterval.addItem("30 seconds");
		refreshInterval.addItem("10 seconds");
		refreshInterval.addItem("05 seconds");
		refreshInterval.addItem("01 seconds");

		Init2.Init2JPanel(viewdataPanel, "viewdataPanel");
	}
	private void listener(){
		ctlJFrame.addMouseListener(this);
		ctlJFrame.addMouseMotionListener(new MouseMotionAdapter() {
			// 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			public void mouseDragged(MouseEvent e) {
				Point tem = ctlJFrame.getLocation();
				ctlJFrame.setLocation(tem.x + e.getX() - mousePosition.x, tem.y + e.getY()- mousePosition.y);
			}
		});
		zoom.addMouseListener(this);
		closeWindow.addMouseListener(this);
		task.addMouseListener(this);
		log.addMouseListener(this);
		download.addMouseListener(this);
		help.addMouseListener(this);
		setting.addMouseListener(this);
		delete.addMouseListener(this);
		viewdata.addMouseListener(this);
		
		release.addMouseListener(this);
		startTask.addMouseListener(this);
		supportedJTable.addMouseListener(this);
		runJTable.addMouseListener(this);
		endTask.addMouseListener(this);

		refreshData.addMouseListener(this);
		picture.addMouseListener(this);
		
		browse.addMouseListener(this);
		downloadData.addMouseListener(this);
		cancelDownloadData.addMouseListener(this);
		//监听选中
		selectDownload.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
					detailed.removeAllItems();
					String[] url=Default.taskSet;
					detailed.addItem("ALL");
					switch(selectDownload.getSelectedItem().toString()){
						case "ALL":
							for(String str:url){
								detailed.addItem(str);
							}
							break;
						case "E-Commerce":
							for(int i=0 ; i<Default.EC_News ; i++){
								detailed.addItem(url[i]);
							}
							break;
						case "News":
							for(int i=Default.EC_News ; i<Default.News_Blog; i++){
								detailed.addItem(url[i]);
							}
							break;
						case "Blog":
							for(int i=Default.News_Blog ; i<url.length ; i++){
								detailed.addItem(url[i]);
							}
							break;
					}
					String text=(String) selectDownload.getSelectedItem(); 
					System.out.println(text);
				}
			}
		});
		
		emptydata.addMouseListener(this);
		emptypicture.addMouseListener(this);
		oneJTable.addMouseListener(this);
		
		saveSetting.addMouseListener(this);
		
	}
	private void setColour(){
		for(int i=0 ; i<20 ; i++){
			flag[i]=false;
		}
		//面板色
		ctlJPanel.setBackground(new Color(20,20,20));
		sidebarJPanel.setBackground(new Color(90,90,90));
		taskJPanel.setBackground(new Color(20,20,20));
		logJPanel.setBackground(new Color(20,20,20));
		downloadPanel.setBackground(new Color(20,20,20));
		helpPanel.setBackground(new Color(20,20,20));
		settingPanel.setBackground(new Color(20,20,20));
		deletePanel.setBackground(new Color(20,20,20));
		viewdataPanel.setBackground(new Color(20,20,20));

		//按键色
		closeWindow.setBackground(new Color(20,20,20));
		zoom.setBackground(new Color(20,20,20));
		task.setBackground(new Color(90,90,90));
		log.setBackground(new Color(90,90,90));
		download.setBackground(new Color(90,90,90));
		help.setBackground(new Color(90,90,90));
		setting.setBackground(new Color(90,90,90));
		delete.setBackground(new Color(90,90,90));
		viewdata.setBackground(new Color(90,90,90));
		release.setBackground(new Color(150,150,150));
		startTask.setBackground(new Color(150,150,150));
		endTask.setBackground(new Color(150,150,150));
		refreshData.setBackground(new Color(150,150,150));
		emptydata.setBackground(new Color(150,150,150));
		emptypicture.setBackground(new Color(150,150,150));
		picture.setBackground(new Color(150,150,150));
		saveSetting.setBackground(new Color(150,150,150));
		browse.setBackground(new Color(150,150,150));
		downloadData.setBackground(new Color(150,150,150));
		cancelDownloadData.setBackground(new Color(150,150,150));

		
		//图片
		closeWindow.setIcon(new ImageIcon(Default.getDynamicIconPath("close")));//任务icon
		zoom.setIcon(new ImageIcon(Default.getDynamicIconPath("zoom")));//任务icon
		task.setIcon(new ImageIcon(Default.getDynamicIconPath("task")));//任务icon
		log.setIcon(new ImageIcon(Default.getDynamicIconPath("log")));//任务icon
		download.setIcon(new ImageIcon(Default.getDynamicIconPath("download")));//任务icon
		help.setIcon(new ImageIcon(Default.getDynamicIconPath("help")));//任务icon
		setting.setIcon(new ImageIcon(Default.getDynamicIconPath("setting")));//任务icon
		delete.setIcon(new ImageIcon(Default.getDynamicIconPath("delete")));//任务icon
		viewdata.setIcon(new ImageIcon(Default.getDynamicIconPath("viewdata")));//任务icon
		release.setIcon(new ImageIcon(Default.getDynamicIconPath("release")));//任务icon
		startTask.setIcon(new ImageIcon(Default.getDynamicIconPath("pause")));//任务icon
		endTask.setIcon(new ImageIcon(Default.getDynamicIconPath("end")));//任务icon
		refreshData.setIcon(new ImageIcon(Default.getDynamicIconPath("refresh")));//任务icon
		emptydata.setIcon(new ImageIcon(Default.getDynamicIconPath("emptydata")));//任务icon
		emptypicture.setIcon(new ImageIcon(Default.getDynamicIconPath("emptypicture")));//任务icon
		picture.setIcon(new ImageIcon(Default.getDynamicIconPath("picture")));//任务icon
		saveSetting.setIcon(new ImageIcon(Default.getDynamicIconPath("savesetting")));//任务icon
		browse.setIcon(new ImageIcon(Default.getDynamicIconPath("browse")));//任务icon
		downloadData.setIcon(new ImageIcon(Default.getDynamicIconPath("local")));//任务icon
		cancelDownloadData.setIcon(new ImageIcon(Default.getDynamicIconPath("cancel")));//任务icon
	}
	public void remove(JButton jb,JPanel jp){
		this.setColour();
		jb.setContentAreaFilled(true);
		jb.setBackground(new Color(20,20,20));
		jb.setIcon(new ImageIcon(Default.getDynamicIconPath(jb.getName()+"1")));//任务icon
	
		ctlJPanel.remove(logJPanel);
    	ctlJPanel.remove(downloadPanel);
    	ctlJPanel.remove(helpPanel);
    	ctlJPanel.remove(settingPanel);
    	ctlJPanel.remove(deletePanel);
    	ctlJPanel.remove(viewdataPanel);
    	ctlJPanel.remove(taskJPanel);
    	
	}
	private void setSize(){
		ctlJPanel.setBounds(0, 0, Default.JFrameX, Default.JFrameY);
		zoom.setBounds(940,0, 30, 30);
		closeWindow.setBounds(970,0, 30, 30);
		datetime.setBounds(730,0, 200, 30);
		
		      sidebarJPanel.setBounds(0, 0,50, Default.JFrameY);
		               task.setBounds(0,  0, 50, 50);
		                log.setBounds(0,50,50, 50);
	               download.setBounds(0,100, 50, 50);
				     delete.setBounds(0,150, 50, 50);
	          viewdata.setBounds(0,200, 50, 50);
		     		   help.setBounds(0,Default.JFrameY-100, 50, 50);
		         	setting.setBounds(0,Default.JFrameY-50, 50, 50);

		         taskJPanel.setBounds( 50,  30, Default.JFrameX-50,Default.JFrameY);
		   supportedJSPanel.setBounds( 30,  70, 200, 470);
		distributionJSPanel.setBounds(270,  70, 230, 400);
		         runJSPanel.setBounds(540,  70, 370, 400);
		         release.setBounds(305, 500, 180,  40);
		          startTask.setBounds(580, 500, 100,  40);
		            endTask.setBounds(760, 500, 100,  40);

  		          logJPanel.setBounds(50, 30,Default.JFrameX-50,Default.JFrameY);
		          lineChart.setBounds(5,20,940,460);
		        refreshData.setBounds(170, 505, 180,  40);
		            picture.setBounds(600, 505, 180,  40);
		
		
		      downloadPanel.setBounds(50, 30,Default.JFrameX-50,Default.JFrameY);
	  downloadHistorySPanel.setBounds(50,40, 850, 300);     
		     selectDownload.setBounds(80, 380, 170, 30);
			       detailed.setBounds(300,380, 170, 30);
		           savePath.setBounds(530, 380, 300, 30 );
		             browse.setBounds(830,380, 50, 30);
				   progress.setBounds(50, 450,850, 30);
		       downloadData.setBounds(170, 505, 180,  40);
		 cancelDownloadData.setBounds(600, 505, 180,  40);

			    deletePanel.setBounds(50, 30,Default.JFrameX-50,Default.JFrameY);
			    oneJSPanel.setBounds( 70,  70, 200, 470);
			    twoJSPanel.setBounds(360,  70, 230, 470);
			    emptypicture.setBounds(680,170, 180,  40); 
			    emptydata.setBounds(680, 400, 180,  40);
		   

		          helpPanel.setBounds(50, 30,Default.JFrameX-50,Default.JFrameY);
			  
		       settingPanel.setBounds(50, 30,Default.JFrameX-50,Default.JFrameY);
		        saveSetting.setBounds(640, 500, 180,  40);
		    refreshInterval.setBounds(230, 50, 180, 30);
			      
		      viewdataPanel.setBounds(50, 30,Default.JFrameX-50,Default.JFrameY);

	}
	
	
	public void mouseClicked(MouseEvent e) {// 单击鼠标时执行的操作 
		if("task".equals(e.getComponent().getName())){
			remove(task,taskJPanel);
			flag[0]=true;
	    	ctlJPanel.add(taskJPanel);
	    	ctlJPanel.updateUI();
	    }else if("log".equals(e.getComponent().getName())){
			remove(log,logJPanel);
			flag[1]=true;
	    	ctlJPanel.add(logJPanel);
	    	ctlJPanel.updateUI();
	    }else if("download".equals(e.getComponent().getName())){
			remove(download,downloadPanel);
			flag[2]=true;
	    	ctlJPanel.add(downloadPanel);
	    	ctlJPanel.updateUI();
	    }else if("delete".equals(e.getComponent().getName())){
			remove(delete,deletePanel);
			flag[5]=true;
	    	ctlJPanel.add(deletePanel);
	    	ctlJPanel.updateUI();
	    }else if("help".equals(e.getComponent().getName())){
			remove(help,helpPanel);
			flag[3]=true;
			
	    	ctlJPanel.add(helpPanel);
	    	ctlJPanel.updateUI();
	    }else if("setting".equals(e.getComponent().getName())){
	    	remove(setting,settingPanel);
			flag[4]=true;
	    	ctlJPanel.add(settingPanel);
	    	ctlJPanel.updateUI();
	    }else if("viewdata".equals(e.getComponent().getName())){
	    	remove(viewdata,viewdataPanel);
	    	flag[6]=true;
	    	ctlJPanel.add(viewdataPanel);
	    	ctlJPanel.updateUI();
	    }else if("supportedJTable".equals(e.getComponent().getName())){
	    	int selectedRow = supportedJTable.getSelectedRow(); // 获得选中行索引
			if(selectedRow!=-1){
				distributionTask.append(supportedJTable.getValueAt(selectedRow, 0).toString()+"\n");
			}
	    }else if("release".equals(e.getComponent().getName())){//发布任务
	    	if(!distributionTask.getText().equals("")){
	    		String[] startTaskSet = Operationstring.differenceString(Operationstring.splitString(distributionTask.getText()), Operationstring.extractString(runData));
	    		if(startTaskSet.length != 0){
			    	if(Task.beginTask(1,startTaskSet)){
			    		String[][] list = new String[runData.length+startTaskSet.length][2];
			    		int k=0;
			    		for(int i=0 ; i<runData.length ; i++,k++){
			    			for(int j=0 ; j<2 ; j++){
			    				list[k][j] = runData[i][j];
			    			}
			    		}
			    		for(int i=0 ; i<startTaskSet.length ; i++,k++){
			    			list[k][0] = startTaskSet[i];
			    			list[k][1] = "run";
			    		}
			    		runData=list;
			    		runModel = new DefaultTableModel(runData,Default.getTaskViewJColumnNames()){
			    			public boolean isCellEditable(int row,int column){ 
			    				return false;
			    			}  
			    		};
			    		runJTable.setModel(runModel);
						new Promptinformation(ctlJFrame, "Successful submission has been done automatically with duplicate tasks and unsupported tasks.",1);//１为普通窗口2为确认对话窗口
//			    		Promptinformation.informationprompt(ctlJFrame,"Successful submission has been done automatically with duplicate tasks and unsupported tasks.");
			    		distributionTask.setText("");
			    	}else{				
			    		new Promptinformation(ctlJFrame, "The task submission failed. Check network connections!",1);//１为普通窗口2为确认对话窗口
//			    		Promptinformation.errorPrompt(ctlJFrame,"The task submission failed. Check network connections!");
			    	}
		    	}else{
		    		new Promptinformation(ctlJFrame, "Please enter the correct url with the currently supported url",1);//１为普通窗口2为确认对话窗口
//		    		Promptinformation.informationprompt(ctlJFrame,"Please enter the correct url with the currently supported url");
		    	}
	    	}else{
	    		new Promptinformation(ctlJFrame, "Task set is empty, please check!!!",1);//１为普通窗口2为确认对话窗口
//	    		Promptinformation.errorPrompt(ctlJFrame,"Task set is empty, please check!!!");
	    	}
		}else if("runJTable".equals(e.getComponent().getName())){
			int selectedRow = runJTable.getSelectedRow(); // 获得选中行索引
			if(selectedRow!=-1){
				if(runJTable.getValueAt(selectedRow, 1).toString().equals("Run")){
					startTask.setIcon(new ImageIcon(Default.getDynamicIconPath("pause")));//任务icon
				}else if(runJTable.getValueAt(selectedRow, 1).toString().equals("Pause")){
					startTask.setIcon(new ImageIcon(Default.getDynamicIconPath("start")));//任务icon
				}
			}

		}else if("startTask".equals(e.getComponent().getName())){//
			//给服务器说更改任务状态
			int selectedRow = runJTable.getSelectedRow(); // 获得选中行索引
			if(selectedRow!=-1){
				if(Task.statusTask(6,runJTable.getValueAt(selectedRow, 0).toString())){
					if(runJTable.getValueAt(selectedRow, 1).toString().equals("Run")){
							runJTable.setValueAt("Pause",selectedRow,1);
							startTask.setIcon(new ImageIcon(Default.getDynamicIconPath("start")));//任务icon
					}else if(runJTable.getValueAt(selectedRow, 1).toString().equals("Pause")){
						runJTable.setValueAt("Run", selectedRow,1);
						startTask.setIcon(new ImageIcon(Default.getDynamicIconPath("pause")));//任务icon
					}
		    		new Promptinformation(ctlJFrame, "The task status was modified successfully",1);//１为普通窗口2为确认对话窗口
//					Promptinformation.informationprompt(ctlJFrame,"The task status was modified successfully");
				}
			}else{
				new Promptinformation(ctlJFrame, "Please select a task",1);//１为普通窗口2为确认对话窗口
			}
		}else if("endTask".equals(e.getComponent().getName())){
			int selectedRow = runJTable.getSelectedRow(); // 获得选中行索引
			if(selectedRow!=-1){
				new Promptinformation(ctlJFrame, "Confirm termination task?",2);//１为普通窗口2为确认对话窗口
	    		if(Promptinformation.flag==0){
    				if(Task.endTask(8,runJTable.getValueAt(selectedRow, 0).toString())){
    					String[][] list = new String[runData.length-1][2];
    					int k=0;
    					for(int i=0; i<selectedRow ; i++,k++){
    						list[k][0] = runData[i][0];
    						list[k][1] = runData[i][1];
			    		}
    					for(int i=selectedRow+1; i<runData.length ; i++,k++){
    						list[k][0] = runData[i][0];
    						list[k][1] = runData[i][1];
			    		}
			    		runData=list;
			    		runModel = new DefaultTableModel(runData,Default.getTaskViewJColumnNames()){
			    			public boolean isCellEditable(int row,int column){ 
			    				return false;
			    			}  
			    		};
			    		runJTable.setModel(runModel);
			    		new Promptinformation(ctlJFrame, "successfully deleted",1);//１为普通窗口2为确认对话窗口
//    					Promptinformation.informationprompt(ctlJFrame,"successfully deleted");
    				}
	    		}
			}else{
	    		new Promptinformation(ctlJFrame, "Please select a task",1);//１为普通窗口2为确认对话窗口
//		    		Promptinformation.informationprompt(ctlJFrame,"Please select a task");
			}
		}else if("refreshData".equals(e.getComponent().getName())){
			if(refresh==null && Default.getRefreshrate()!=0){//定时刷新
				refresh = new Timer();
				refresh.schedule(new TimerTask() {
					public void run() {
						if(Default.getRefreshrate_flag()){//若刷新时间未变就继续
							logJPanel.remove(lineChart);
			    			lineChart = Crawlergraph.createLineChart();
			    			logJPanel.add(lineChart);
			    			lineChart.setBounds(5, 20,940,460);
			    			logJPanel.updateUI();
						}else{//若已变则取消自动刷新
							Default.setRefreshrate_flag(true);
							refresh.cancel();
							refresh=null;
						}
//		    			if(1==1){//网络中断
//		    	    		Promptinformation.errorPrompt(ctlJFrame,"The network connection is broken. Please try again");
//		            		refresh.cancel();
//		            	}
						}
					},0,Default.getRefreshrate()); //数据刷新频率
			}else{//立即刷新
				logJPanel.remove(lineChart);
    			lineChart = Crawlergraph.createLineChart();
    			logJPanel.add(lineChart);
    			lineChart.setBounds(5,20,940,460);
    			logJPanel.updateUI();
			}
		}else if("picture".equals(e.getComponent().getName())){
			if(Crawlergraph.savePicture(Crawlergraph.getJFreeChart())){
	    		new Promptinformation(ctlJFrame, "Current progress graph saved successfully (./schedule/)",1);//１为普通窗口2为确认对话窗口
//	    		Promptinformation.informationprompt(ctlJFrame,"Current progress graph saved successfully (./schedule/)");
			}else{
	    		new Promptinformation(ctlJFrame, "Latest progress graph save failed",1);//１为普通窗口2为确认对话窗口
//	    		Promptinformation.informationprompt(ctlJFrame,"Latest progress graph save failed");
			}
		}else if("browse".equals(e.getComponent().getName())){
		    JFileChooser path = new JFileChooser();
		    path.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    int result  =path.showSaveDialog(ctlJFrame);
		    if(result == JFileChooser.APPROVE_OPTION){
		   	 	savePath.setText(path.getSelectedFile().getAbsolutePath());
		    }
		}else if("downloadData".equals(e.getComponent().getName())){
			if(savePath.getText().equals("")){
	    		new Promptinformation(ctlJFrame, "Please select the save path",1);//１为普通窗口2为确认对话窗口
//	    		Promptinformation.informationprompt(ctlJFrame,"Please select the save path");
	    	}else{
	    		if(!flag[15]){
					flag[15]=true;
					int select=0;
					for(int i=0 ; i<detailed.getItemCount() ; i++){
						if(detailed.getSelectedItem().toString().equals(detailed.getItemAt(i).toString())){
							break;
						}
					}
					System.out.println(select);
					downloaddata = new Timer();
					downloaddata.schedule(new TimerTask() {
						public void run() {   
							//得到下载的类别
							if(Data.downloadData(22,select,savePath.getText())){
					    		new Promptinformation(ctlJFrame, "Download completed",1);//１为普通窗口2为确认对话窗口
//								Promptinformation.informationprompt(ctlJFrame,"Download completed");
							}else{
					    		new Promptinformation(ctlJFrame, "Download failed, please try again",1);//１为普通窗口2为确认对话窗口
//								Promptinformation.informationprompt(ctlJFrame,"Download failed, please try again");
							}
							flag[15]=false;
							downloaddata.cancel();
						}
					},0); 
				}else{
		    		new Promptinformation(ctlJFrame, "Has to download the task, please wait for the end of the new task after the start",1);//１为普通窗口2为确认对话窗口
//					Promptinformation.informationprompt(ctlJFrame,"Has to download the task, please wait for the end of the new task after the start");
				}
	    	}
		}else if("cancelDownloadData".equals(e.getComponent().getName())){
			
		
		}else if("oneJTable".equals(e.getComponent().getName())){
			int selectedRow = oneJTable.getSelectedRow(); // 获得选中行索引
			if(selectedRow!=-1){
				int start=0,end=0;
				switch(selectedRow){				
					case 0:start=0;end=Default.taskSet.length;break;
					case 1:start=0;end=Default.EC_News;break;
					case 2:start=Default.EC_News;end=Default.News_Blog;break;
					case 3:start=Default.News_Blog;end=Default.taskSet.length;break;
				}
				twoData=Data.getTwoData(start,end);
				twoModel = new DefaultTableModel(twoData,new String[]{"URL"}){
	    			public boolean isCellEditable(int row,int column){ 
	    				return false;
	    			}  
	    		};
    			twoJTable.setModel(twoModel);
			}
		}else if("emptydata".equals(e.getComponent().getName())){
			int one = oneJTable.getSelectedRow(); // 获得选中行索引
			int two = twoJTable.getSelectedRow(); // 获得选中行索引
			if(two!=-1){
				int start=0,end=0;
				new Promptinformation(ctlJFrame, "Are you sure you want to empty the downloaded data, which is irrevocable!!!",2);//１为普通窗口2为确认对话窗口
				if(Promptinformation.flag==0){
					if(two==0){//是ALL
						switch(one){
							case 0:start=0;end=Default.taskSet.length;break;
							case 1:start=0;end=Default.EC_News;break;
							case 2:start=Default.EC_News;end=Default.News_Blog;break;
							case 3:start=Default.News_Blog;end=Default.taskSet.length;break;
						}
					}else{
						start=Operationstring.getIndex(Default.taskSet,twoJTable.getValueAt(two,0).toString());
						end=start+1;
					}
					if(Data.emptyData(21,start,end)){
			    		new Promptinformation(ctlJFrame, "Has been emptied",1);//１为普通窗口2为确认对话窗口
//						Promptinformation.informationprompt(ctlJFrame,"Has been emptied");
					}else{
						//清空失败操作  网络异常
					}
				}

			}else{
		    		new Promptinformation(ctlJFrame, "Please select a url",1);//１为普通窗口2为确认对话窗口
			}
		}else if("emptypicture".equals(e.getComponent().getName())){
			//清空进度图
			
		}else if("saveSetting".equals(e.getComponent().getName())){
			//保存设置
			//得到数据刷新频率
			int time=10;
			switch(refreshInterval.getSelectedIndex()){
				case 0:time=0;break;
				case 1:time=300;break;
				case 2:time=60;break;
				case 3:time=30;break;
				case 4:time=10;break;
				case 5:time=5;break;
				case 6:time=1;break;
			}
			Default.setRefreshrate(time*1000);
			Default.setRefreshrate_flag(false);
//			Promptinformation.informationprompt(ctlJFrame,"Saved settings");
			new Promptinformation(ctlJFrame, "Saved settings",1);//１为普通窗口2为确认对话窗口
		}else if("zoom".equals(e.getComponent().getName())){
			zoom.setIcon(new ImageIcon(Default.getDynamicIconPath("zoom")));//任务icon
			ctlJFrame.setExtendedState(JFrame.ICONIFIED);
		}else if("closeWindow".equals(e.getComponent().getName())){
			System.exit(0);//退出程序
		} 
	}

	public void mousePressed(MouseEvent e) {// 鼠标在组件上按下时执行的操作  
		if("task".equals(e.getComponent().getName())){
			task.setContentAreaFilled(false);
		}else if("log".equals(e.getComponent().getName())){
			log.setContentAreaFilled(false);
		}else if("download".equals(e.getComponent().getName())){
			download.setContentAreaFilled(false);
		}else if("help".equals(e.getComponent().getName())){
			help.setContentAreaFilled(false);
		}else if("setting".equals(e.getComponent().getName())){
			setting.setContentAreaFilled(false);
		}else if("delete".equals(e.getComponent().getName())){
			delete.setContentAreaFilled(false);
		}else if("viewdata".equals(e.getComponent().getName())){
			viewdata.setContentAreaFilled(false);
		}else if("ctlJFrame".equals(e.getComponent().getName())){	// 当鼠标按下的时候获得窗口当前的位置
			mousePosition.x = e.getX();
			mousePosition.y = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {// 鼠标释放时执行的操作  	
	}

	public void mouseEntered(MouseEvent e) {// 鼠标进入组件时执行的操作 
		if("task".equals(e.getComponent().getName())){
			if(!flag[0]){
				task.setIcon(new ImageIcon(Default.getDynamicIconPath("task1")));//任务icon
			}
		}else if("log".equals(e.getComponent().getName())){
			if(!flag[1]){
				log.setIcon(new ImageIcon(Default.getDynamicIconPath("log1")));//任务icon
			}
		}else if("download".equals(e.getComponent().getName())){
			if(!flag[2]){
				download.setIcon(new ImageIcon(Default.getDynamicIconPath("download1")));//任务icon
			}
		}else if("help".equals(e.getComponent().getName())){
			if(!flag[3]){
				help.setIcon(new ImageIcon(Default.getDynamicIconPath("help1")));//任务icon
			}
		}else if("setting".equals(e.getComponent().getName())){
			if(!flag[4]){
				setting.setIcon(new ImageIcon(Default.getDynamicIconPath("setting1")));//任务icon
			}
		}else if("delete".equals(e.getComponent().getName())){
			if(!flag[5]){
				delete.setIcon(new ImageIcon(Default.getDynamicIconPath("delete1")));//任务icon
			}
		}else if("viewdata".equals(e.getComponent().getName())){
			if(!flag[6]){
				viewdata.setIcon(new ImageIcon(Default.getDynamicIconPath("viewdata1")));//任务icon
			}
		}else if("zoom".equals(e.getComponent().getName())){
			if(!flag[8]){
				zoom.setIcon(new ImageIcon(Default.getDynamicIconPath("zoom1")));//任务icon
			}
		}else if("closeWindow".equals(e.getComponent().getName())){
			if(!flag[9]){
				closeWindow.setIcon(new ImageIcon(Default.getDynamicIconPath("close1")));//任务icon
			}
		}else if("release".equals(e.getComponent().getName())){
			release.setBackground(new Color(255,255,255));
		 }else if("startTask".equals(e.getComponent().getName())){
				startTask.setBackground(new Color(255,255,255));
			 }else if("endTask".equals(e.getComponent().getName())){
			endTask.setBackground(new Color(255,255,255));
		 }else if("refreshData".equals(e.getComponent().getName())){
			 refreshData.setBackground(new Color(255,255,255));
		 }else if("emptydata".equals(e.getComponent().getName())){
			 emptydata.setBackground(new Color(255,255,255));
		 }else if("emptypicture".equals(e.getComponent().getName())){
			 emptypicture.setBackground(new Color(255,255,255));
		 }else if("picture".equals(e.getComponent().getName())){
			 picture.setBackground(new Color(255,255,255));
		 }else if("browse".equals(e.getComponent().getName())){
			 browse.setBackground(new Color(255,255,255));
		 }else if("downloadData".equals(e.getComponent().getName())){
			 downloadData.setBackground(new Color(255,255,255));
		 }else if("cancelDownloadData".equals(e.getComponent().getName())){
			 cancelDownloadData.setBackground(new Color(255,255,255));
		 }else if("saveSetting".equals(e.getComponent().getName())){
			 saveSetting.setBackground(new Color(255,255,255));
		 }
	}

	public void mouseExited(MouseEvent e) {//鼠标离开组件时执行的操作 
		if("task".equals(e.getComponent().getName())){
			if(!flag[0]){
				task.setIcon(new ImageIcon(Default.getDynamicIconPath("task")));//任务icon
			}
		}else if("log".equals(e.getComponent().getName())){
			if(!flag[1]){
				log.setIcon(new ImageIcon(Default.getDynamicIconPath("log")));//任务icon
			}
		}else if("download".equals(e.getComponent().getName())){
			if(!flag[2]){
				download.setIcon(new ImageIcon(Default.getDynamicIconPath("download")));//任务icon
			}
		}else if("help".equals(e.getComponent().getName())){
			if(!flag[3]){
				help.setIcon(new ImageIcon(Default.getDynamicIconPath("help")));//任务icon
			}
		}else if("setting".equals(e.getComponent().getName())){
			if(!flag[4]){
				setting.setIcon(new ImageIcon(Default.getDynamicIconPath("setting")));//任务icon
			}
		}else if("delete".equals(e.getComponent().getName())){
			if(!flag[5]){
				delete.setIcon(new ImageIcon(Default.getDynamicIconPath("delete")));//任务icon
			}
		}else if("viewdata".equals(e.getComponent().getName())){
			if(!flag[6]){
				viewdata.setIcon(new ImageIcon(Default.getDynamicIconPath("viewdata")));//任务icon
			}
		}else if("release".equals(e.getComponent().getName())){
			release.setBackground(new Color(150,150,150));
		 }else if("startTask".equals(e.getComponent().getName())){
			startTask.setBackground(new Color(150,150,150));
		}else if("endTask".equals(e.getComponent().getName())){
			endTask.setBackground(new Color(150,150,150));
		 }else if("refreshData".equals(e.getComponent().getName())){
			 refreshData.setBackground(new Color(150,150,150));
		 }else if("emptydata".equals(e.getComponent().getName())){
			 emptydata.setBackground(new Color(150,150,150));
		 }else if("emptypicture".equals(e.getComponent().getName())){
			 emptypicture.setBackground(new Color(150,150,150));
		 }else if("picture".equals(e.getComponent().getName())){
			 picture.setBackground(new Color(150,150,150));
		 }else if("browse".equals(e.getComponent().getName())){
			 browse.setBackground(new Color(150,150,150));
		 }else if("downloadData".equals(e.getComponent().getName())){
			 downloadData.setBackground(new Color(150,150,150));
		 }else if("cancelDownloadData".equals(e.getComponent().getName())){
			 cancelDownloadData.setBackground(new Color(150,150,150));
		 }else if("saveSetting".equals(e.getComponent().getName())){
			 saveSetting.setBackground(new Color(150,150,150));
		 }else if("zoom".equals(e.getComponent().getName())){
			if(!flag[8]){
				zoom.setIcon(new ImageIcon(Default.getDynamicIconPath("zoom")));//任务icon
			}
		 }else if("closeWindow".equals(e.getComponent().getName())){
			if(!flag[9]){
				closeWindow.setIcon(new ImageIcon(Default.getDynamicIconPath("close")));//任务icon
			}
		 }
	}
	public static void main(String[] args){ 
		new UI2();
	}
}
