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
}

