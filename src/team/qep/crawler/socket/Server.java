package team.qep.crawler.socket;
 
import java.io.BufferedReader;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class Server {  
    public static final int PORT = 8888;//监听的端口号     
      
    public static void main(String[] args) {    
        System.out.println("服务器启动...\n");    
        Server server = new Server();    
        server.init();    
    }    
    
    public void init() {    
        try {    
            ServerSocket serverSocket = new ServerSocket(PORT);    
            while (true) {    
                // 一旦有堵塞, 则表示服务器与客户端获得了连接    
                Socket client = serverSocket.accept();    
                // 处理这次连接    
                new HandlerThread(client);    
            }    
        } catch (Exception e) {    
            System.out.println("服务器异常: " + e.getMessage());    
        }    
    }    
    
    private class HandlerThread implements Runnable {    
        private Socket socket;    
        public HandlerThread(Socket client) {    
            socket = client;    
            new Thread(this).start();    
        }    
    
        public void run() {    
            try {    
                //获得输入流
                InputStream is=socket.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                //读取用户输入信息
                String info=null;
                while(!((info=br.readLine())==null)){
                    System.out.println("我是服务器，用户信息为："+info);
                }
                

                //给客户一个响应
                //获得输出流
                OutputStream os=socket.getOutputStream();
                PrintWriter pw=new PrintWriter(os);
                String reply="welcome";
                pw.write(reply);
                pw.flush();
            
                //关闭资源
                pw.close();
                os.close();
                br.close();
                is.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }    
        }    
    }    
}    