package team.qep.crawler.basic;

public class Default {
	public static int JFrameX=1000;
	public static int JFrameY=618;
	public static int JPanelX=1000;
	public static int JPanelY=568;
	private static int refreshrate=1000;	
	private static  boolean refreshrate_flag=true;//true一样未变化　　　false刷新时间已改变　
	public static int EC_News=1;
	public static int News_Blog=2;
	public static String[] taskSet = new String[]{"www.taobao.comassssssssssssssssssssssssssssssssssssssc","blog.csdn.net","1","2","3","4"};
	public static boolean getRefreshrate_flag() {
		return refreshrate_flag;
	}
	public static void setRefreshrate_flag(boolean flag) {
		refreshrate_flag = flag;
	}
	public static void setRefreshrate(int refresh){
		refreshrate=refresh;
	}
	public static int getRefreshrate(){
		return refreshrate;
	}
	public static String getPromptImagePath(){//得到提示框路径
//		return "./image/prompt.jpg";
		return "./image/1.jpg";
	}
	public static String getDynamicImagePath(int k){
		return "./image/"+k+".gif";
	}
	public static String getDynamicIconPath(String path){
		return "./image/icon/"+path+".png";
	}	
	public static String getHelp(){
		return "./image/.png";
	}
	public static String[] getTaskViewJColumnNames(){
		return new String[]{"Url","Status"};
	}
	//默认支持的任务集
	public static String[] getDefaultUrl(){
		return taskSet;
	}
	
}