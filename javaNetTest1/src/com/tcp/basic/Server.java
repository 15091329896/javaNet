package com.tcp.basic;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		
		new Server().testServer();
		
	}

	private void testServer() throws Exception {
		System.out.println("-----Server2-----");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket serverSocket =new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		Socket requestSocket =serverSocket.accept();
		System.out.println("一个客户端建立了连接");
		// 3、操作: 输入输出流操作
		DataInputStream receiveStream =new DataInputStream(requestSocket.getInputStream());
		String requestString =receiveStream.readUTF();
		System.out.println(requestString);
		// 4、释放资源 
		receiveStream.close();
		requestSocket.close();
		serverSocket.close();
	}
}
