package team.qep.crawler.basic;

import java.util.ArrayList;
import java.util.HashMap;

public class Constant {
	//窗口默认值
	public static final int JFrame_Width  = 1024;
	public static final int JFrame_Height = 632;
	//UI选项卡的键值对
	public static final HashMap<String, Integer> KeyValue = new HashMap<String, Integer>() {
		{
			put("issueTask1", 0);
			put("issueTask2", 1);
			put("taskControl", 2);
			put("dataMonitoring", 3);
			put("dataDisplay", 4);
			put("historyRecord", 5);
			put("zoom", 8);
			put("close", 9);
			put("stop", 12);
			put("run", 13);
		}
	};
	
	//模糊任务集 
	public static final ArrayList<String> SupportFuzzyUrl = new ArrayList<String>(){{
		add("www.taobao.com");
		add("www.tmall.com");
		add("news.sina.com.cn");
		add("blog.csdn.net");
		add("blog.sina.com.cn");
	}};
	//模糊任务集上电商与博客新闻的分割线
	public static final int division = 2;
	//精确任务集-------模糊任务集的子集
	public static final ArrayList<String> SupportExactUrl = new ArrayList<String>(){{
		add("www.taobao.com");
	}};

	//及时任务的配置模板
	public static final ArrayList<String> Template = new ArrayList<String>(){{
		add("taobao");
		add("tmall");
		add("blog.csdn");
		add("news.sina");
		add("blog.sina");
	}};
	//协议表
	public static final HashMap<String, Integer> Agreement = new HashMap<String, Integer>() {
		{
			put("fuzzyUrl", 5);//模糊任务
			put("exactUrl", 6);//精确任务
			put("timelyUrl", 7);//及时任务
			put("DownloadData ", 8);//下载的数据量
			put("ProgressData ", 9);//爬取的数据量
			
		}
	};
	
	
	public static final String[] E_CommerceCcolumnNames = new String[]{"URL","商品名","店铺名","评论"};//电商数据列名
	public static final String[] BlogNewsCcolumnNames = new String[]{"标题","关键字","摘要"};//博客新闻数据列名
	public static final String[] TaskCcolumnNames = new String[]{"URL","任务类别","关键字","发布时间","任务状态"};//任务数据列名
	public static final String[] HistoricalTaskCcolumnNames = new String[]{"URL","关键字","总数"};//历史任务数据列名

	public static String getIcon(String path) {// 得到icon
		return "./image/icon/" + path + ".png";
	}
	
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
}