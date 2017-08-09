package team.qep.crawler.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import team.qep.crawler.util.Promptinformation;

public class Download {
	private static String IP = "120.77.201.150";
//	private static String IP = "172.18.214.188";
//	private static String IP = "127.0.0.1";
//	private static String IP = "192.168.30.170";
	private static int port = 8888;
	 public static boolean  download(String str,String path) {  
	        try {  
	        	//1.建立客户端socket连接，指定服务器位置及端口
	        	Socket socket =new Socket(IP,port);
	        	socket.setSoTimeout(3000);
	        	//2.发送信息
	        	OutputStream os=socket.getOutputStream();
	        	PrintWriter pw=new PrintWriter(os);
	        	pw.write(str);
	        	pw.flush();
	        	socket.shutdownOutput();
	           
	        	//接受信息
	        	InputStream is=socket.getInputStream();
	        	BufferedReader br=new BufferedReader(new InputStreamReader(is));
//	        	recv=br.readLine();
	        	socket.shutdownInput();
	            
	            
	            
	            // 本地保存路径，文件名会自动从服务器端继承而来。  
	            String savePath = "E:\\";  
	            int bufferSize = 8192;  
	            byte[] buf = new byte[bufferSize];  
	            int passedlen = 0;  
	            long len = 0;  
	  
//	            savePath += inputStream.readUTF();  
//	            DataOutputStream fileOut = new DataOutputStream(  
//	                    new BufferedOutputStream(new FileOutputStream(savePath)));  
//	            len = inputStream.readLong();  
	  
	            System.out.println("文件的长度为:" + len + "\n");  
	            System.out.println("开始接收文件!" + "\n");  
	  
//	            while (true) {  
//	                int read = 0;  
//	                if (inputStream != null) {  
//	                    read = inputStream.read(buf);  
//	                }  
//	                passedlen += read;  
//	                if (read == -1) {  
//	                    break;  
//	                }  
//	                System.out.println("文件接收了" + (passedlen * 100 / len) + "%\n");  
//	                fileOut.write(buf, 0, read);  
//	            }  
//	            System.out.println("接收完成，文件存为" + savePath + "\n");  
	  
//	            fileOut.close();  
	            
	          //关闭资源
	        	br.close();
	        	is.close();
	        	pw.close();
	        	os.close();
	        	socket.close();
	        } catch (UnknownHostException e) {
	        	e.printStackTrace();
	        } catch (IOException e) {
//	        	Promptinformation.errorPrompt(null,"Server connection off!");
//	        	System.exit(0);
	        	e.printStackTrace();
	        }
			return false;
	 }
}
