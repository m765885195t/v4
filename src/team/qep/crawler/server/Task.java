package team.qep.crawler.server;

import java.util.ArrayList;

import team.qep.crawler.basic.Default;
import team.qep.crawler.socket.Communication;
import team.qep.crawler.util.ConvertJSON;
import team.qep.crawler.util.Operationstring;
import team.qep.crawler.util.Promptinformation;

public class Task {
	//得到正在运行的任务集
	public static String[][] getRunningTask(int taskNumber){
		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber,""));
		String[] str = ConvertJSON.toStringArray(flag);
		ArrayList<String[]> list = new ArrayList<String[]>();
		System.out.println(flag);

		for(int i=1 ; i<str.length ; i+=2){
			list.add(new String[]{Default.taskSet[Integer.valueOf(str[i])],String.valueOf(str[i+1])});
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
			S.append(Operationstring.getIndex(Default.getDefaultUrl(), taskSet[i])+",");
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
		System.out.println(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex(Default.getDefaultUrl(), string)))+"转换后的终止任务");
		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex(Default.getDefaultUrl(), string))));
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
		System.out.println(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex(Default.getDefaultUrl(), string)))+"转换后的终止任务");
		String flag = Communication.SendAndRecv(ConvertJSON.toJSON(taskNumber,String.valueOf(Operationstring.getIndex(Default.getDefaultUrl(), string))));
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
	public static void main(String[] args){
		statusTask(8,"1");
	}
}
