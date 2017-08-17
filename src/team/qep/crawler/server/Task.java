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
import team.qep.crawler.util.StringManipulation;

public class Task {
	//得到运行的任务集
	public static String[][] getRunUrlSet(){
		String[][] runUrl = new String[][]{{"www.taobao.com","羽毛球"},{"www.taobao.com","伞"},{"www.taobao.com",""}};
		
		return runUrl;		
	}	
	//存在本地?
	//得到终止的任务集
	public static String[][] getStopUrlSet(){
		String[][] stopUrl = new String[][]{{"www.taobao.com","1"},{"www.taobao.com","2"},{"www.taobao.com",""}};
		
		return stopUrl;		
	}
	
	//发布模糊任务                       参数--->(模糊任务url集string,优先度int)
	public static boolean fuzzyUrlPublish(String fuzzyURL,int priority){
		//1---分割为数组同时去掉重复的url
		Set<String> set = new HashSet<String>(Arrays.asList(fuzzyURL.replace(" ", "").split("\n")));
		//2---去掉不支持(错误)的url
		Set<String> support = new HashSet<String>(Constant.SupportFuzzyUrl);
		set.retainAll(support);
		//3---去掉运行中(已经发布过)的url
		ArrayList<String> list = new ArrayList<String>();
		boolean flag=true;//重复标志
		for(String url:set){
			flag=true;
			for(String[] str: Task.getRunUrlSet()){
				if(str[0].equals(url) && str[1].equals("")){
					 flag=false;
				}
			}
			if(flag){
				list.add(url);
			}
		}
		//得到待发布的模糊任务集
		String[] fuzzyUrlSet=(String[])list.toArray(new String[list.size()]);
		//转为url编号
		int[] task = new int[fuzzyUrlSet.length];
		for(int i=0 ; i<task.length ; i++){
			task[i]=Constant.SupportExactUrl.indexOf(fuzzyUrlSet[i]);
		}
		//得到任务字符数组[1,2]
		p(Arrays.toString(task));
		//待转json发送
		
		return true;
	}
	
	//发布精确任务      参数--->(精确任务url(string)  关键字(string)    优先度(int))
	public static boolean exactUrlPublish(String exactURL, String key, int priority){
		//去重
		boolean flag=true;//重复标志
		for(String[] str: Task.getRunUrlSet()){
			if(str[0].equals(exactURL) && str[1].equals(key)){
				 flag=false;
			}
		}
		if(flag){
			int task = Constant.SupportExactUrl.indexOf(exactURL);//得到任务下标
			p(task);
			//待转json发送
			
			return true;
		}else{//已经重复
			return false;
		}
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
	
	//查找关键字        参数--->(类型终止or运行(String)   精确任务url(string))
	public static String[] getKeyWords(int type,String string) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("");
		String[][] taskSet = null;
		if(type==Constant.KeyValue.get("run")){
			taskSet=Task.getRunUrlSet();
		}else if(type==Constant.KeyValue.get("stop")){
			taskSet=Task.getStopUrlSet();
		}
		for(String str:taskSet[0]){
			if(str.equals(string)){
				list.add(str);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	public static void main(String[] args){
		fuzzyUrlPublish("www.taobao.com",1);
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
