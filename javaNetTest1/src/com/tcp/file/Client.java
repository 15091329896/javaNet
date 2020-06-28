package com.tcp.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		new Client().clientTest();
		
	}

	private void clientTest() throws Exception {
		System.out.println("-----MulityClient-----");
		Socket clientSocket =new Socket("localhost",8888);
		
//		接收数据
		byte[] flush =new byte[1024];
		InputStream is =new BufferedInputStream(new FileInputStream("D:\\app\\xun_lei\\迅雷下载\\[阳光电影www.ygdy8.com].后会无期.HD.720p.国语中字.rmvb"));
		OutputStream sendStream =new BufferedOutputStream(clientSocket.getOutputStream());
		
//		读写数据
		int len = -1;
		while ((len = is.read(flush)) != -1) {
			System.out.println("客户端    正在上传数据......");
			sendStream.write(flush, 0 , len);
		}
		System.out.println("客户端    正在上传数据 成功");
		is.close();
		sendStream.flush();
		//3、释放资源 
		sendStream.close();
		clientSocket.close();
	}

}
