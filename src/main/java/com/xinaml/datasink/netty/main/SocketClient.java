package com.xinaml.datasink.netty.main;
import java.io.*;
import java.net.*;
/**
 * @Author: [lgq]
 * @Date: [19-4-20 下午2:15]
 * @Description:
 * @Version: [1.0.0]
 */
public class SocketClient {

    public static void main(String args[]) {

        try {

            Socket socket = new Socket("127.0.0.1", 7000);
            PrintWriter os = new PrintWriter(socket.getOutputStream());//输出
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));//接收
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
            String readline;
            readline = sin.readLine(); //等待输入
            while (!readline.equals("bye")) {
                os.println(readline);
                os.flush();
                System.out.println("客户端发送的数据包:" + readline);
                System.out.println("客户端接收的数据包:" + is.readLine());
                readline = sin.readLine(); //等待输入

            } //继续循环
            os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("异常：" + e); //出错，则打印出错信息

        }

    }
}
