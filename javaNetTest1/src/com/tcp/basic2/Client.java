package com.tcp.basic2;

import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		new Client().clientTest2();
	}
	private void clientTest2() throws Exception {
		/*
		 * 创建客户端socket
		 * 		
		 * 		创建接收请求的输入流
		 * 		将输入流转为数据输入流
		 * 		
		 * */
	}
	
	private void clientTest() throws Exception {
		System.out.println("-----Client2-----");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket clientSocket =new Socket("localhost",8888);
		//2、操作: 输出流操作
		DataOutputStream sendStream =new DataOutputStream(clientSocket.getOutputStream());
		String requestString ="请求的消息";
		sendStream.writeUTF(requestString);
		sendStream.flush();
		//3、释放资源 
		sendStream.close();
		clientSocket.close();
	}
}
