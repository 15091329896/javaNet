package com.tcp.basic2;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		
		new Server().testServer2();
		
	}

	private void testServer2() throws Exception {
		/*
		 * 创建服务端socket
		 * 		监听请求，发行请求得到处理请求的socket
		 * 		通过处理请求的socket，得到请求的输入流
		 * 		从输入流中取出请求的数据
		 * 		打印请求的数据
		 */
		System.out.println("  服务端 ");
		ServerSocket serverSocket = new ServerSocket(8888);
		Socket acceptSocket = serverSocket.accept();
		System.out.println(" 一个客户端连接已经建立 ");
		InputStream inputStream = acceptSocket.getInputStream();
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		String readUTF = dataInputStream.readUTF();
		System.out.println(readUTF);
		
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
