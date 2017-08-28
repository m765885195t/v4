package team.qep.crawler.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;

import team.qep.crawler.server.Data;
import team.qep.crawler.server.Task;
import team.qep.crawler.util.Constant;

public class HistoryRecord extends JPanel implements MouseListener {
	private Timer temer = new Timer();
	private JLabel historyRecord = new JLabel("数  据  统  计");

	private String[] columnNames; // 表格列名
	private String[][] data; // 表格数据
	private DefaultTableModel taskDataSetModel;
	private JTable taskDataSet = new JTable();
	private JScrollPane taskDataJSP = new JScrollPane(taskDataSet); // 历史人任务记录
	
	private JFreeChart pieChart = CrawlerChart.getPieChart();
	private JPanel pieChartJP=new ChartPanel(pieChart);// 历史任务记录
	private JButton refresh = new JButton();// 刷新
	private JButton deleteData = new JButton();// 删除数据

	public HistoryRecord() {
		this.Init();
		this.loadingData();
		this.setBounds();
		this.setColour();
		this.listener();

		this.add(historyRecord);
		this.add(taskDataJSP);
		this.add(pieChartJP);
		this.add(refresh);
		this.add(deleteData);
	}

	private void loadingData() {// 装载数据
		if(!Constant.RefreshInterval.equals("0")){
			 new Timer().scheduleAtFixedRate(new TimerTask() {
				public void run() {
					((PiePlot) pieChart.getPlot()).setDataset(CrawlerChart.getPieDataSet());
				}
			 },0,Integer.valueOf(Constant.RefreshInterval)*1000);
		}
		columnNames = Constant.HistoricalTaskCcolumnNames;
		data=Data.getDownloadDataSet();
		taskDataSetModel = new DefaultTableModel(data, columnNames){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		taskDataSet.setModel(taskDataSetModel);
	}

	private void Init() {
		Init.initJLable(historyRecord, "historyRecord");

		Init.initJTable(taskDataSet, "taskDataSet");
		Init.initJScrollPane(taskDataJSP, "taskDataJSP");
		taskDataSet.setFont(new Font("serif", 0, 17));// 设置表格字体
		
		Init.initJButton(refresh, "refresh");
		Init.initJButton(deleteData, "deleteData");

	}

	private void setBounds() {
		historyRecord.setBounds(320, 0, 300, 40);
		
		taskDataJSP.setBounds(30, 50, 420, 520);
		pieChartJP.setBounds(500, 50, 444, 450);
		refresh.setBounds(500, 530, 150, 42);
		deleteData.setBounds(790, 530, 150, 42);
	}

	private void setColour() {
		this.setBackground(Theme.PanelColor);

		historyRecord.setFont(Theme.TitleFont);
		historyRecord.setForeground(Theme.TitleColor);
		refresh.setBackground(Theme.ButtonColor);
		refresh.setIcon(Constant.getIcon("refresh"));
		deleteData.setBackground(Theme.ButtonColor);
		deleteData.setIcon(Constant.getIcon("deleteData"));
	}

	private void listener() {
		refresh.addMouseListener(this);
		deleteData.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {// 单击
		if ("refresh".equals(e.getComponent().getName())) {
			data=Data.getDownloadDataSet();
			taskDataSetModel = new DefaultTableModel(data, columnNames) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			taskDataSet.setModel(taskDataSetModel);
			((PiePlot) pieChart.getPlot()).setDataset(CrawlerChart.getPieDataSet());
		}else if ("deleteData".equals(e.getComponent().getName())) {
			int selectedRow = taskDataSet.getSelectedRow();
			if (selectedRow != -1) {
				String url=taskDataSet.getValueAt(selectedRow, 0).toString();
				String keyWord=taskDataSet.getValueAt(selectedRow, 1).toString();
				
				new Promptinformation(null, "确定要删除此任务的所有数据?", Constant.KeyValue.get("Confirm"));
				if(Promptinformation.flag){
					Promptinformation.flag=false;//状态改回去

					if(Task.deleteTaskData(url, keyWord)){
						new Promptinformation(null, "删除成功", Constant.KeyValue.get("Info"));

						data=Data.getDownloadDataSet();
						taskDataSetModel = new DefaultTableModel(data, columnNames) {
							public boolean isCellEditable(int row, int column) {
								return false;
							}
						};
						taskDataSet.setModel(taskDataSetModel);
						((PiePlot) pieChart.getPlot()).setDataset(CrawlerChart.getPieDataSet());
					}else{
						new Promptinformation(null, "删除失败,请检查网络连接", Constant.KeyValue.get("Info"));
					}
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {// 按下
	}

	public void mouseReleased(MouseEvent e) {// 释放

	}

	public void mouseEntered(MouseEvent e) {// 进入
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(Color.WHITE);
		}else if ("deleteData".equals(e.getComponent().getName())) {
			deleteData.setBackground(Color.WHITE);
		}
	}

	public void mouseExited(MouseEvent e) {// 离开
		if ("refresh".equals(e.getComponent().getName())) {
			refresh.setBackground(Theme.ButtonColor);
		}else if ("deleteData".equals(e.getComponent().getName())) {
			deleteData.setBackground(Theme.ButtonColor);
		}
	}

}
