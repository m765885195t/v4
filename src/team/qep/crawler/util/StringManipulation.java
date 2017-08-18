package team.qep.crawler.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//字符串操作
public class StringManipulation {
	//一维list转二维数组(只有一列)
	public static String[][] toTwoDimensionalArrays(ArrayList<String> strlist){
		String[][] str = new String[strlist.size()][1];
		for(int i = 0 ; i < strlist.size() ; i++) {
			str[i][0] = strlist.get(i);
		}
		return str;
	}
	//一维字符数组转指定二维数组      参数--待转的一维字符数组  转换的维度
	public static String[][] toTwoDimensionalArrays(String[] string,int dimension){
		String[][] twoDimensional = new String[string.length/dimension][dimension];
		for(int i=1 ; i < string.length ; i++) {
			twoDimensional[(i-1)/dimension][(i-1)%dimension] = string[i];
		}
		return twoDimensional;
	}
		
	//二位数组转一维数组(只取第一列)
	public static String[] toOneDimensionalArrays(String[][] string){
		String[] str = new String[string.length];
		for(int i = 0 ; i < string.length ; i++) {
			str[i] = string[i][0];
		}
		return str;
	}
	
	//一维数组去重
	public static String[] oneDuplicateRemoval(String[] string){
		Set<String> set = new HashSet<String>(Arrays.asList(string));
		return set.toArray(new String[set.size()]);
	}
	public static void main(String[] a){
		System.out.println(ConvertJSON.toStringArray("[\"1\",\"www.taobao.com\",\"0\", \"\",\"08/17 20:01\",\"1\"]").length);

		String[][] A = toTwoDimensionalArrays(ConvertJSON.toStringArray("[\"1\", \"www.taobao.com\",\"0\", \"\", \"08/17 20:01\",\"1\"]"),5);
		for(String[] i:A){
			for(String j:i){
				System.out.println(j);
			}
		}
		
	}
}

