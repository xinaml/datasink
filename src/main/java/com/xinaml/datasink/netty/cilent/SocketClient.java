package com.xinaml.datasink.netty.cilent;
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
                System.out.println("Client send:" + readline);
                System.out.println("return:" + is.readLine());
                readline = sin.readLine(); //等待输入

            } //继续循环

            os.close();

            is.close();

            socket.close();

        } catch (Exception e) {

            System.out.println("Error" + e); //出错，则打印出错信息

        }

    }
}
