package com.tcp.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {

		new Server().testServer();

	}

	private void testServer() throws Exception {
		System.out.println("-----MulityServer-----");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket serverSocket = new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		Socket requestSocket = serverSocket.accept();
		System.out.println("一个客户端建立了连接");
		// 3、操作: 输入输出流操作
		byte[] flush = new byte[1024];
		InputStream receiveStream = new BufferedInputStream(requestSocket.getInputStream());
		OutputStream os = new BufferedOutputStream(
				new FileOutputStream("src/[阳光电影www.ygdy8.com].后会无期.HD.720p.国语中字.rmvb"));
		int len = -1;
		while ((len = receiveStream.read(flush)) != -1) {
			System.out.println("服务端正在写入数据......");
			os.write(flush, 0, len);
		}
		System.out.println("服务端正在写入数据完毕   成功");
		os.flush();
		os.close();
		// 4、释放资源
		receiveStream.close();
		requestSocket.close();
		serverSocket.close();
	}

}
