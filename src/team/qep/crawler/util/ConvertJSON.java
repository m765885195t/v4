package team.qep.crawler.util;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

//数据转换为JSON
public class ConvertJSON {
	//str转为json字符串
	public static String toJSON(int taskNumber,String content){
		JsonObject json = new JsonObject();
		json.addProperty("task",String.valueOf(taskNumber));
		json.addProperty("content", content);
		return "["+json.toString()+"]";

//		return "["+Operationstring.deleteLastChar(Operationstring.deleteLastChar(json.toString(),'"'),'"')+"]";
	}
	//json数组字符转为string[]
	public static String[] toStringArray(String json){
		Gson gson = new Gson();  
		ArrayList<String> list = new ArrayList<String>();  
        list = gson.fromJson(json, new TypeToken<ArrayList<String>>(){}.getType());  
        return (String[])list.toArray(new String[list.size()]);
	}
	public static void main(String[] args){
		String[] list = toStringArray("[\"1\", \"1\"]");
		for(String str:list){
			System.out.println(str);
		}
		System.out.println(toJSON(3,"1,2,3,3"));
	}
}
