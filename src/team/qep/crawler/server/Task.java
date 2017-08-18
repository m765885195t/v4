package team.qep.crawler.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import team.qep.crawler.basic.Constant;
import team.qep.crawler.socket.Communication;
import team.qep.crawler.util.ConvertJSON;

public class Task {
	//发布模糊任务                       参数--->(模糊任务url集string,优先度int)
	public static boolean fuzzyUrlPublish(String fuzzyURL,int priority){
		//1---分割为数组同时去掉重复的url
		Set<String> set = new HashSet<String>(Arrays.asList(fuzzyURL.replace(" ", "").split("\n")));
		//2---去掉不支持(错误)的url
		Set<String> support = new HashSet<String>(Constant.SupportFuzzyUrl);
		set.retainAll(support);
		//3---去掉运行中(已经发布过)的url
		ArrayList<String> list = new ArrayList<String>();
		String[][] runUrlSet=Data.getRunUrlSet();//正在运行的任务集
		boolean flag=true;//重复标志
		for(String url:set){
			flag=true;
			for(String[] str: runUrlSet){
				if(url.equals(str[0]) && str[2].equals("")){
					 flag=false;
					 break;
				}
			}
			if(flag){
				list.add(url);
			}
		}
		//得到待发布的模糊任务集
		String[] fuzzyUrlSet=(String[])list.toArray(new String[list.size()]);

		if(fuzzyUrlSet.length>0){//空任务不发送
			//转为url编号
			int[] task = new int[fuzzyUrlSet.length+1];
			for(int i=0 ; i<task.length-1 ; i++){
				task[i]=Constant.SupportFuzzyUrl.indexOf(fuzzyUrlSet[i]);
			}
			task[task.length-1]=priority;//最后一位为任务优先级
			//待转json发送
			String content=Arrays.toString(task).substring(1,Arrays.toString(task).length()-1);
			String send=ConvertJSON.toJSON(Constant.Agreement.get("fuzzyUrlPublish"),content);
			String[] recv = ConvertJSON.toStringArray(Communication.SendAndRecv(send));
			if(recv.length>0){//<0 说明服务器无响应
				if(recv[0].equals(String.valueOf(Constant.Agreement.get("fuzzyUrlPublish"))) && recv[1].equals(String.valueOf(Constant.KeyValue.get("Success")))){
					return true;
				}
			}
		}
		return false;
	}
	
	//发布精确任务      参数--->(精确任务url(string)  关键字(string)    优先度(int))
	public static boolean exactUrlPublish(String exactURL, String keyWord, int priority){
		String[][] runUrlSet=Data.getRunUrlSet();//正在运行的任务集
		boolean flag=true;//重复标志
		
		for(String[] str: runUrlSet){
			if(exactURL.equals(str[0]) && str[2].equals(keyWord)){//重复
				 flag=false;
				 break;
			}
		}
		if(flag){//未重复
			//转为url编号
			String[] task = new String[3];
			task[0]=String.valueOf(Constant.SupportFuzzyUrl.indexOf(exactURL));//url下标
			task[1]=keyWord;//关键字
			task[2]=String.valueOf(priority);//最后一位为任务优先级
			//待转json发送
			String content=Arrays.toString(task).substring(1,Arrays.toString(task).length()-1);
			String send=ConvertJSON.toJSON(Constant.Agreement.get("exactUrlPublish"),content);
			String[] recv = ConvertJSON.toStringArray(Communication.SendAndRecv(send));
			
			if(recv.length>0){//<0 说明服务器无响应
				if(recv[0].equals(String.valueOf(Constant.Agreement.get("exactUrlPublish"))) && recv[1].equals(String.valueOf(Constant.KeyValue.get("Success")))){
					return true;
				}
			}
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
	
	//查找url对应的关键字        参数--->任务url(string))
	public static String[] getKeyWords(String url) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("");
		String[][] taskSet=Data.getALLUrlSet();
		for(String[] str:taskSet){
			if(url.equals(str[0])){
				list.add(str[1]);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	//简化输出
	public static void p(String str){
		System.out.println(str);
	}
	public static void p(int str){
		System.out.println(str);
	}
	//得到该url对应的数据     keyword为空说明为模糊任务，否则为精确任务
	public static String[][] getUrlData(String url, String keyWord) {
		return new String[][]{{"dsa"}};
	}

}
