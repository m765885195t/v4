package team.qep.crawler.server;

import java.util.ArrayList;
import java.util.Random;

import org.jfree.chart.editor.DefaultChartEditorFactory;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.socket.Communication;
import team.qep.crawler.socket.Download;
import team.qep.crawler.util.ConvertJSON;
import team.qep.crawler.util.Operationstring;

public class Data {
	//简化输出
	public static void p(String str){
		System.out.println(str);
	}
	public static void p(int str){
		System.out.println(str);
	}
	//得到进度数据生成折线图
	public static String[][] scheduleData(){
//		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(Constant.Agreement.get("ProgressData"),""));
//		String[] str = ConvertJSON.toStringArray(flag);
//		
//		ArrayList<String[]> list = new ArrayList<String[]>();
//		for(int i=1 ; i<str.length; i+=4){
//			list.add(new String[]{str[i+1],"E-Commerce",str[i]});
//			list.add(new String[]{str[i+2],"News",str[i]});
//			list.add(new String[]{str[i+3],"Blog",str[i]});
//		}
		
		Random random = new Random();
		ArrayList<String[]> list = new ArrayList<String[]>();
		for(int i=0 ; i<8; i++){
			list.add(new String[]{String.valueOf(i*random.nextInt(1000)),"E-Commerce","07/18 "+String.valueOf(i*2+10)+":00"});
			list.add(new String[]{String.valueOf(i*random.nextInt(1000)),"News","07/18 "+String.valueOf(i*2+10)+":00"});
			list.add(new String[]{String.valueOf(i*random.nextInt(1000)),"Blog","07/18 "+String.valueOf(i*2+10)+":00"});
		}
//		
		String[][] dataSet = new String[list.size()][3];
		for(int i=0 ; i<list.size() ; i++){
			for(int j=0 ; j<3 ; j++){
				dataSet[i][j] = list.get(i)[j];
			}
		}
		p("das");
		return dataSet;
	}	
	//得到总下载量数据生成饼状图
	public static String[][] downloadData(){
//		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(Constant.Agreement.get("DownloadData"),""));
//		String[] str = ConvertJSON.toStringArray(flag);
//		
//		ArrayList<String[]> list = new ArrayList<String[]>();
//		list.add(new String[]{"E-Commerce",str[1]});
//		list.add(new String[]{"News",str[2]});
//		list.add(new String[]{"Blog",str[3]});
		
		Random random = new Random();
		ArrayList<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"E-Commerce",String.valueOf(random.nextInt(10))});
		list.add(new String[]{"News",String.valueOf(random.nextInt(10))});
		list.add(new String[]{"Blog",String.valueOf(random.nextInt(10))});
		
		String[][] dataSet = new String[list.size()][2];
		for(int i=0 ; i<list.size() ; i++){
			for(int j=0 ; j<2 ; j++){
				dataSet[i][j] = list.get(i)[j];
			}
		}
		return dataSet;
	}
	
	//清空数据 ----参数（带清空数据据的url(String), 关键字keyWord(String))
	public static boolean wipeData(String url,String keyWord){
		int task = Constant.SupportFuzzyUrl.indexOf(url);//得到任务下标
		//待转json发送
//		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber,""));
//		String[] str = ConvertJSON.toStringArray(flag);
//		if(str[1].equals("0")){
//			return false;
//		}
		return true;
	}
	public static void main(String[] args){
	}
}
