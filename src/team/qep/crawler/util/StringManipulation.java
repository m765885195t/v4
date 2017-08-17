package team.qep.crawler.util;

import java.util.ArrayList;

//字符串操作
public class StringManipulation {
	//一维list转二维数组
	public static String[][] toTwoDimensionalArrays(ArrayList<String> strlist){
		String[][] str = new String[strlist.size()][1];
		for(int i = 0 ; i < strlist.size() ; i++) {
			str[i][0] = strlist.get(i);
		}
		return str;
	}
	//二位数组转一维数组(只取第一列)
	public static String[] toOneDimensionalArrays(String[][] string){
		String[] str = new String[string.length];
		for(int i = 0 ; i < string.length ; i++) {
			str[i] = string[i][0];
		}
		return str;
	}
}

