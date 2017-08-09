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

public class Communication{
//	private static String IP = "120.77.201.150";
	private static String IP = "123.206.89.123";
//	private static String IP = "127.0.0.1";
//	private static String IP = "192.168.30.170";
	private static int port = 8888;
	
    public static String SendAndRecv(String str) {
        String recv = null;
      recv="[\"5\",1,1,2,2]";

        try{   
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
        	recv=br.readLine();
        	socket.shutdownInput();
            System.out.println("服务器说:"+recv);

        	//关闭资源
        	br.close();
        	is.close();
        	pw.close();
        	os.close();
        	socket.close();
        } catch (UnknownHostException e) {
        	e.printStackTrace();
        } catch (IOException e) {
//			new Promptinformation(null, "Server connection off!",1);//１为普通窗口2为确认对话窗口
        	e.printStackTrace();
//        	System.exit(0);
        }
        return recv;
    }
}