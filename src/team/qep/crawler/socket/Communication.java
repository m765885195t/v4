package team.qep.crawler.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import team.qep.crawler.util.Constant;
import team.qep.crawler.util.Promptinformation;

public class Communication{
	private static final String IP = "120.77.201.150";
	private static final int port = 8888;
	private static String recv = "[]";
	
    public static String SendAndRecv(String send) {
        try{   
        	//1.建立客户端socket连接，指定服务器位置及端口
//        	Socket socket =new Socket(IP,port);
        	Socket socket = new Socket();
        	InetSocketAddress address=new InetSocketAddress(IP, port);
        	socket.connect(address,2000);//连接超时时长
        	//2.发送信息
        	OutputStream os=socket.getOutputStream();
        	PrintWriter pw=new PrintWriter(os);
        	pw.write(send);
        	pw.flush();
        	socket.shutdownOutput();
            System.out.println("我说:"+send);

        	//接受信息
        	socket.setSoTimeout(3000);//读数据操作时,最多阻塞3秒钟 时间一到(不再等待读下去了),抛出IOException？
        	InputStream is=socket.getInputStream();
        	BufferedReader br=new BufferedReader(new InputStreamReader(is));
        	
//        	StringBuffer strbuf=new StringBuffer();
//        	String str;
//        	while((str=br.readLine())!= null){
//        		strbuf.append(str);
//        	}
//        	recv=strbuf.toString();
        	
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
        }catch (IOException e) {
        	//服务器未开直接关闭
//        	System.out.println("读不出数据");
        	recv="[]";//远程服务器长时间无回响
//			new Promptinformation(null,"网络未连接  or 服务器端未开启",Constant.KeyValue.get("Info"));// １为普通窗口2为确认对话窗口
//        	System.exit(0);
			e.printStackTrace();
        } 
        return recv;
    }
}