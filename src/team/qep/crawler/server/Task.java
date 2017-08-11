package team.qep.crawler.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.socket.Communication;
import team.qep.crawler.util.ConvertJSON;
import team.qep.crawler.util.Operationstring;
import team.qep.crawler.util.Promptinformation;

public class Task {
	//v3.0
	//得到正在运行的任务集
	public static String[][] getRunningTask(int taskNumber){
		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber,""));
		String[] str = ConvertJSON.toStringArray(flag);
		ArrayList<String[]> list = new ArrayList<String[]>();
		System.out.println(flag);

		for(int i=1 ; i<str.length ; i+=2){
			list.add(new String[]{Constant.taskSet[Integer.valueOf(str[i])],String.valueOf(str[i+1])});
		}
		String[][] A= new String[list.size()][3];
		for(int i=0 ; i<list.size() ; i++){
			for(int j=0 ; j<2 ; j++){
				if(j==1){
					switch(list.get(i)[j]){
						case "1":A[i][j] = "Run";break;
						case "2":A[i][j] = "Pause";break;
					}
				}else{
					A[i][j] = list.get(i)[j];
				}
			}
		}
		return A;
//		return new String[][]{{"1","blog.csdn.net"}};
//		return new String[][]{};
	}
	//发布任务集
	public static boolean beginTask(int taskNumber,String[] taskSet){
		StringBuilder S = new StringBuilder();
		for(int i=0 ; i<taskSet.length ; i++){
			S.append(Operationstring.getIndex((String [])Constant.SupportExactUrl.toArray(), taskSet[i])+",");
		}
		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber, 	Operationstring.deleteLastChar(S.toString(),',')));
		String[] str = ConvertJSON.toStringArray(flag);
//		if(str[1].equals("0")){
//			return false;
//		}
		return true;
	}

	//终止任务---
	public static boolean endTask(int taskNumber,String string){
		System.out.println(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex((String[]) Constant.SupportExactUrl.toArray(), string)))+"转换后的终止任务");
		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex((String[])Constant.SupportExactUrl.toArray(), string))));
		String[] str = ConvertJSON.toStringArray(flag);
		if(str[0].equals(String.valueOf(taskNumber))){
			if(str[1].equals("0")){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	//修改任务状态
	public static boolean statusTask(int taskNumber,String string){
		System.out.println(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex((String[])Constant.SupportExactUrl.toArray(), string)))+"转换后的终止任务");
		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex((String[])Constant.SupportExactUrl.toArray(), string))));
		String[] str = ConvertJSON.toStringArray(flag);
		if(str[0].equals(String.valueOf(taskNumber))){
			if(str[1].equals("0")){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
	
	
	//v4.0
	//得到运行中的总任务集
	public static String[] getRunUrl(){
		String[] runFuzzyUrl = getRunFuzzyUrl();
		String[] runExactUrl = getRunExactUrl();

		runFuzzyUrl = Arrays.copyOf(runFuzzyUrl,runFuzzyUrl.length + runExactUrl.length);// 扩容模糊集
        System.arraycopy(runExactUrl, 0, runFuzzyUrl, runFuzzyUrl.length, runExactUrl.length);//合并精确集到模糊集		
        
        return runFuzzyUrl;		
	}
	//得到运行中的模糊任务集
	public static String[] getRunFuzzyUrl(){
		String[] runFuzzyUrl = new String[0];
		
		return runFuzzyUrl;		
	}
	//得到运行中的精确任务集
	public static String[] getRunExactUrl(){
		String[] runExactUrl = new String[0];
		
		return runExactUrl;		
	}
	
	//发布模糊任务                       参数--->(模糊任务url集string,优先度int)
	public static boolean fuzzyUrlPublish(String fuzzyURL,int priority){
		//1---分割为数组同时去掉重复的url
		Set<String> set = new HashSet<String>(Arrays.asList(fuzzyURL.replace(" ", "").split("\n")));
		//2---去掉不支持(错误)的url
		Set<String> support = new HashSet<String>(Constant.SupportExactUrl);
		set.retainAll(support);
		//3---去掉运行中(已经发布过)的url,
		Set<String> run = new HashSet<String>(Arrays.asList(Task.getRunFuzzyUrl()));//得到正在运行的任务集
		set.removeAll(run);
		//得到待发布的模糊任务集
		String[] fuzzyUrlSet=(String[])set.toArray(new String[set.size()]);
		
		//转为下标
		int[] task = new int[fuzzyUrlSet.length];
		for(int i=0 ; i<task.length ; i++){
			task[i]=Constant.SupportExactUrl.indexOf(fuzzyUrlSet[i]);
		}
//		Arrays.toString(task);
		
		//待转json发送
		
		return true;
	}
	
	//发布精确任务      参数--->(精确任务url(string)  关键字(string)    优先度(int))
	public static boolean exactUrlPublish(String exactURL, String key, int priority){
		if(!Arrays.asList(getRunExactUrl()).contains(exactURL)){//不存在相同的任务
			int task = Constant.SupportExactUrl.indexOf(exactURL);//得到任务下标
			
			//待转json发送
			
			return true;
		}
		return false;
	}
	
	//发布及时任务      参数--->(及时任务url集(string)  模板配置(string))
	public static boolean timelyUrlPublish(String timelyURL, String configure) {
		//1---分割为数组同时去掉重复的url
		Set<String> set = new HashSet<String>(Arrays.asList(timelyURL.replace(" ", "").split("\n")));
		String[] timelyUrlSet=(String[])set.toArray(new String[set.size()]);
		
		//默认最好10个超过大小收发有误(猜测)
		//待转json发送
		
		return true;
	}
	//得到及时任务的结果数据   
	public static String[][] getTimelyUrlData() {
		return new String[][]{{"dsa"}};
	}
	public static void main(String[] args){
		timelyUrlPublish("blog.sina.com.cn","s");
	}
	//简化输出
	public void p(String str){
		System.out.println(str);
	}
	public void p(int str){
		System.out.println(str);
	}
}
