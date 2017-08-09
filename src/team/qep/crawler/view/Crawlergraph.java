package team.qep.crawler.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import team.qep.crawler.server.Data;

public class Crawlergraph {
	 //返回折线图面板
	 public static JPanel createLineChart() {  
	        return new ChartPanel(getJFreeChart());  
	    }
	  static JFreeChart getJFreeChart() {
		   JFreeChart jfreechart=null;  
		   // JFreeChart对象 参数：标题，目录轴显示标签，数值轴显示标签，数据集，是否显示图例，是否生成工具，是否生成URL连接  
		   jfreechart = ChartFactory.createLineChart("Total Crawl Progress", "", "", getDataSet(), PlotOrientation.VERTICAL, true, true, false);  
//	      jfreechart = ChartFactory.createLineChart3D("", "X轴", "Y轴",getDataSet(), PlotOrientation.VERTICAL, true, true, false);  

//		    jfreechart.setBorderVisible(true);// 边框可见  
		    jfreechart.getLegend().setItemFont(new Font("sans-serif", 1, 15));//图例字体  
	       
		    CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot(); // 获取折线图plot对象 
	        categoryplot.setBackgroundPaint(Color.lightGray);  //背景色
	        categoryplot.setRangeGridlinePaint(Color.white);  
	        categoryplot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false  
	        //空数据提示信息
	        categoryplot.setNoDataMessage("No download history, please start the task");  
	        categoryplot.setNoDataMessageFont(new Font("sans-serif", 1, 25));//字体的大小  
	        categoryplot.setNoDataMessagePaint(Color.RED);//字体颜色  
	        
	        //设置Y轴  
	        NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
	        setNumberAxis(numberaxis);  
	        // 设置x轴  
	        CategoryAxis domainAxis = (CategoryAxis) categoryplot.getDomainAxis();  
	        setDomainAxis(domainAxis);  

	        // 获取折线对象  
	        LineAndShapeRenderer renderer = (LineAndShapeRenderer) categoryplot.getRenderer();  
	        renderer.setBaseShapesVisible(true);   // 数据点可见  
	        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());   // 显示数据点的数据   
	        renderer.setBaseItemLabelsVisible(true);     // 显示折线图点上的数据  
	      
	        BasicStroke realLine = new BasicStroke(1.8f); // 设置实线  
	        // 设置虚线  
	        float dashes[] = { 5.0f };   
	        BasicStroke brokenLine = new BasicStroke(2.2f, // 线条粗细  
	                BasicStroke.CAP_ROUND, // 端点风格  
	                BasicStroke.JOIN_ROUND, // 折点风格  
	                8f, dashes, 0.6f);  

	        renderer.setSeriesStroke(0, brokenLine); //第一条线用虚线绘制  
	        renderer.setSeriesStroke(1, realLine); // 第二条线用实线绘制  
	      
	        
	        return jfreechart;  
	    }  
	 
	   //X轴设置
	    private static void setDomainAxis(CategoryAxis domainAxis){  
	        // 设置X轴标题与坐标字体  
	    	domainAxis.setTickLabelFont(new Font("sans-serif", 1, 13));  
	        domainAxis.setLabelFont(new Font("sans-serif", 1, 13));  
//	         用于显示X轴刻度  
      	    domainAxis.setTickMarksVisible(true);  
	        //设置X轴45度  
//	        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);  
	    }  
	    //Y轴设置
	    private static void setNumberAxis(NumberAxis numberaxis){  
//	         numberaxis.setTickUnit(new NumberTickUnit(1));//指定Y轴数据间隔  
	    	numberaxis.setPositiveArrowVisible(true);//正想箭头
	        // 设置Y轴标题与坐标字体  
	        numberaxis.setLabelFont(new Font("sans-serif",1, 13));  
	        numberaxis.setTickLabelFont(new Font("sans-serif", 1, 13));  
	        numberaxis.setUpperMargin(0.1);//设置最高数据显示与顶端的距离
	    }  
	      
	   //得到数据集 
	    private static DefaultCategoryDataset getDataSet() {  
	        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();  
	        String[][] dataSet = Data.refreshData(18);
            for(int i=0 ; i<dataSet.length ; i++){
                defaultcategorydataset.addValue(Integer.valueOf(dataSet[i][0]), dataSet[i][1], dataSet[i][2]);  
            }  
	        return defaultcategorydataset;  
	    }  
	//保存为图片
	public static boolean savePicture(JFreeChart jfreechart){
		File file = new File("./schedule/"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+".jpg");
		if (!file.exists()) {//文件不存在则创建
			try {  
				file.getParentFile().mkdirs();
				file.createNewFile();
				ChartUtilities.saveChartAsJPEG(file,1.0f,jfreechart,1200,600);  //    1.0f,    //图片质量 ，0.0f~1.0f  
		    }catch (IOException e) {  
		        e.printStackTrace();  
		        return false;
		    }
		}
		return true;
	 }
}
