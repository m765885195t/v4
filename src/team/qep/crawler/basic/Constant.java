package team.qep.crawler.basic;

import java.util.ArrayList;
import java.util.HashMap;

public class Constant {
	//窗口默认值
	public static final int JFrame_Width  = 1024;
	public static final int JFrame_Height = 632;
	//UI选项卡的键值对
	public static final HashMap<String, Integer> UIKeyValue = new HashMap<String, Integer>() {
		{
			put("issueTask1", 0);
			put("issueTask2", 2);
			put("resourceScheduling", 4);
			put("taskControl", 6);
			put("dataMonitoring", 8);
			put("dataDisplay", 10);
			put("historyRecord", 12);
			put("setting", 14);
			put("zoom", 16);
			put("close", 18);
		}
	};
	
	//模糊任务集 
	public static final ArrayList<String> SupportFuzzyUrl = new ArrayList<String>(){{
		add("www.taobao.com");
		add("www.tmall.com");
		add("www.jd.com");
		add("www.suning.com");
		add("www.gome.com");
		add("www.amazon.cn");
		add("www.dangdang.com");
		add("www.cnblogs.com");
		add("blog.csdn.net");
		add("blog.sina.com.cn");
		add("blog.51cto.com");
		add("blog.hexun.com");
		add("www.chinanews.com");
		add("www.chinadaily.com.cn");
		add("www.eastday.com");
		add("www.huanqiu.com");
		add("news.sina.com.cn");
	}};
	//模糊任务集上电商与博客新闻的分割线
	public static final int division = 7;
	//精确任务集-------模糊任务集的子集
	public static final ArrayList<String> SupportExactUrl = new ArrayList<String>(){{
		add("www.taobao.com");
		add("www.tmall.com");
		add("www.jd.com");
		add("www.suning.com");
		add("www.gome.com");
		add("www.amazon.cn");
		add("www.dangdang.com");
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
			put("RunUrlSet", 1);//运行的任务集合
			put("AllUrlSet", 2);//所有的任务集合
			put("fuzzyUrlPublish", 3);//发布模糊任务
			put("exactUrlPublish", 5);//发布精确任务
			put("timelyUrlPublish", 8);//发布即时任务
			put("DownloadData", 13);//下载的数据量
			put("ProgressData", 21);//爬取的数据量
			put("TotalData", 34);//三大类别数据量
			put("ResourceInformation", 55);//从机资源信息
		}
	};
	
	//功能键值对
	public static final HashMap<String, Integer> KeyValue = new HashMap<String, Integer>() {
		{
			put("Fail", -1);//失败
			put("Success",0);//成功
			put("E_Commerce", 1);//电商
			put("Blog", 2);//博客
			put("News", 3);//新闻
			put("Run", 5);//爬取任务运行中
			put("Wait",8);//爬取任务等待中
			put("Complete", 13);//爬取任务已完成
			put("Start", 21);//启用从机
			put("Ready", 34);//从机准备中
			put("Stop", 55);//终止从机
			put("Info", 89);//信息窗口
			put("Confirm", 144);//确认对话窗口
			
		}
	};
	
	public static final String[] E_CommerceCcolumnNames = new String[]{"URL","商品名","店铺名","评论"};//电商数据列名
	public static final String[] BlogNewsCcolumnNames = new String[]{"标题","关键字","摘要"};//博客新闻数据列名
	public static final String[] TaskCcolumnNames = new String[]{"URL","任务类别","关键字","发布时间","任务状态"};//任务数据列名
	public static final String[] HistoricalTaskCcolumnNames = new String[]{"URL","关键字","总数"};//历史任务数据列名
	public static final String[] ResourceSchedulingCcolumnNames = new String[]{"从机编号","IP","状态","任务数"};//历史任务数据列名

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