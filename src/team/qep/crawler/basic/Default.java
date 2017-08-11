package team.qep.crawler.basic;

import java.util.HashMap;

public class Default {
	public static int JFrameX = 1024;
	public static int JFrameY = 632;
	//存在数据库中
	private static String[] supportFuzzyUrl = new String[] { "www.taobao.com", "blog.csdn.net", "www.tmall.com",
			"news.sina.com.cn", "blog.sina.com.cn" };
	private static String[] supportExactUrl = new String[] { "www.taobao.com", "blog.csdn.net", "www.tmall.com",
			"news.sina.com.cn", "blog.sina.com.cn" };
	public static HashMap<String, Integer> map = new HashMap<String, Integer>() {
		{
			put("issueTask1", 0);
			put("issueTask2", 1);
			put("taskControl", 2);
			put("dataMonitoring", 3);
			put("dataDisplay", 4);
			put("historyRecord", 5);
			put("zoom", 8);
			put("close", 9);
		}
	};

	// v2.0
	private static int refreshrate = 1000;
	private static boolean refreshrate_flag = true;// true一样未变化 false刷新时间已改变
	public static int EC_News = 1;
	public static int News_Blog = 2;
	public static String[] taskSet = new String[] { "www.taobao.com", "blog.csdn.net", "1", "2", "3", "4" };;

	public static boolean getRefreshrate_flag() {
		return refreshrate_flag;
	}

	public static void setRefreshrate_flag(boolean flag) {
		refreshrate_flag = flag;
	}

	public static void setRefreshrate(int refresh) {
		refreshrate = refresh;
	}

	public static int getRefreshrate() {
		return refreshrate;
	}

	public static String getPromptImagePath() {// 得到提示框路径
		// return "./image/prompt.jpg";
		return "./image/1.jpg";
	}

	public static String getDynamicImagePath(int k) {
		return "./image/" + k + ".gif";
	}

	public static String getDynamicIconPath(String path) {
		return "./image/icon/" + path + ".png";
	}

	public static String getHelp() {
		return "./image/.png";
	}

	public static String[] getTaskViewJColumnNames() {
		return new String[] { "Url", "Status" };
	}

	// v3.0
	public static String[] getSupportFuzzyUrl() {// 得到支持的模糊爬取url集
		return supportFuzzyUrl;
	}
	public static String[] getSupportExactUrl() {// 得到支持的精确爬取url集
		return supportExactUrl;
	}
	public static String getIcon(String path) {// 得到icon
		return "./image/icon/sidebar/" + path + ".png";
	}

}