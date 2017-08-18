package team.qep.crawler.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.jfree.chart.editor.DefaultChartEditorFactory;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.socket.Communication;
import team.qep.crawler.socket.Download;
import team.qep.crawler.util.ConvertJSON;
import team.qep.crawler.util.Operationstring;
import team.qep.crawler.util.StringManipulation;

public class Data {
	//得到运行的任务集
	public static String[][] getRunUrlSet(){
		String send=ConvertJSON.toJSON(Constant.Agreement.get("RunUrlSet"),"");
		String[] recv=ConvertJSON.toStringArray(Communication.SendAndRecv(send));
		
		String[][] RunUrlSet=StringManipulation.toTwoDimensionalArrays(recv,5);
		for(int i=0 ; i<RunUrlSet.length; i++){
			for(int j=0 ; j<RunUrlSet[i].length; j++){
				switch(j){
				case 0:RunUrlSet[i][j]=Constant.SupportFuzzyUrl.get(Integer.valueOf(RunUrlSet[i][j]));break;
				case 1:
					if(Integer.valueOf(RunUrlSet[i][j])==Constant.KeyValue.get("E_Commerce")){
						RunUrlSet[i][j]=new String("电商");
					}else if(Integer.valueOf(RunUrlSet[i][j])==Constant.KeyValue.get("Blog")){
						RunUrlSet[i][j]=new String("博客");
					}else if(Integer.valueOf(RunUrlSet[i][j])==Constant.KeyValue.get("News")){
						RunUrlSet[i][j]=new String("新闻");
					}
					break;
				case 4:
					if(Integer.valueOf(RunUrlSet[i][j])==Constant.KeyValue.get("Run")){
						RunUrlSet[i][j]=new String("运行中");
					}else if(Integer.valueOf(RunUrlSet[i][j])==Constant.KeyValue.get("Wait")){
						RunUrlSet[i][j]=new String("暂停中");
					}else if(Integer.valueOf(RunUrlSet[i][j])==Constant.KeyValue.get("Complete")){
						RunUrlSet[i][j]=new String("已完成");
					}
					break;
				}
			}
		}
		return RunUrlSet;		
	}	
	//得到所有的任务集
	public static String[][] getALLUrlSet(){
		String send=ConvertJSON.toJSON(Constant.Agreement.get("AllUrlSet"),"");
		String[] recv=ConvertJSON.toStringArray(Communication.SendAndRecv(send));
		
		String[][] AllUrlSet=StringManipulation.toTwoDimensionalArrays(recv,2);
		for(int i=0 ; i<AllUrlSet.length; i++){
			AllUrlSet[i][0]=Constant.SupportFuzzyUrl.get(Integer.valueOf(AllUrlSet[i][0]));
		}
		return AllUrlSet;
	}
	
	//简化输出
	public static void p(String str){
		System.out.println(str);
	}
	public static void p(int str){
		System.out.println(str);
	}
	//得到进度数据生成折线图
	public static String[][] getScheduleData(String url,String keyWord){
		String[] task = new String[]{String.valueOf(Constant.SupportFuzzyUrl.indexOf(url)),keyWord};
		String content=Arrays.toString(task).substring(1,Arrays.toString(task).length()-1);
		String send=ConvertJSON.toJSON(Constant.Agreement.get("ProgressData"),content);
		String[] recv=ConvertJSON.toStringArray(Communication.SendAndRecv(send));
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		for(int i=1 ; i<recv.length; i+=2){
			list.add(new String[]{recv[i],url,recv[i+1]});
		}
		
		String[][] dataSet = new String[list.size()][3];
		for(int i=0 ; i<list.size() ; i++){
			for(int j=0 ; j<3 ; j++){
				dataSet[i][j] = list.get(i)[j];
			}
		}
		return dataSet;
	}	
	//得到下载历史数据
	public static String[][] getDownloadDataSet(){
		String send=ConvertJSON.toJSON(Constant.Agreement.get("DownloadData"),"");
		String[] recv=ConvertJSON.toStringArray(Communication.SendAndRecv(send));
		
		String[][] downloadDataSet=StringManipulation.toTwoDimensionalArrays(recv,3);
		for(int i=0 ; i<downloadDataSet.length; i++){
			downloadDataSet[i][0]=Constant.SupportFuzzyUrl.get(Integer.valueOf(downloadDataSet[i][0]));
		}
		return downloadDataSet;
	}
	//得到总下载量数据生成饼状图
	public static String[][] downloadData(){
		String send=ConvertJSON.toJSON(Constant.Agreement.get("TotalData"),"");
		String[] recv=ConvertJSON.toStringArray(Communication.SendAndRecv(send));
	
		ArrayList<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"E-Commerce",recv[1]});
		list.add(new String[]{"Blog",recv[2]});
		list.add(new String[]{"News",recv[3]});

		String[][] dataSet = new String[list.size()][2];
		for(int i=0 ; i<list.size() ; i++){
			for(int j=0 ; j<2 ; j++){
				dataSet[i][j] = list.get(i)[j];
			}
		}
		return dataSet;
	}
	//得到从机资源信息
	public static String[][] getResourceInformation(){
		String send=ConvertJSON.toJSON(Constant.Agreement.get("ResourceInformation"),"");
		String[] recv=ConvertJSON.toStringArray(Communication.SendAndRecv(send));
		
		String[][] resource=StringManipulation.toTwoDimensionalArrays(recv,4);
		for(int i=0 ; i<resource.length; i++){
			if(resource[i][2].equals(String.valueOf(Constant.KeyValue.get("Start")))){
				resource[i][2]="运行中";
			}else if(resource[i][2].equals(String.valueOf(Constant.KeyValue.get("Ready")))){
				resource[i][2]="就绪";
			}else if(resource[i][2].equals(String.valueOf(Constant.KeyValue.get("Stop")))){
				resource[i][2]="已停止";
			}
		}
		return resource;
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
