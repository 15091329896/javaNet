package com.tcp.file2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
	public static void main(String[] args) throws Exception {

		new Server2().testServer();

	}

	private void testServer() throws Exception {
		System.out.println("-----Server2-----");
		ServerSocket serverSocket = new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		Socket requestSocket = serverSocket.accept();
		System.out.println("一个客户端建立了连接");
		// 3、操作: 输入输出流操作
		InputStream is =new BufferedInputStream(requestSocket.getInputStream());
		OutputStream os =new BufferedOutputStream(new FileOutputStream("src/tcp.png"));
		byte[] flush =new byte[1024];
		int len = -1;
		while((len=is.read(flush))!=-1) {
			os.write(flush,0,len);
		}
		os.flush();
		//3、释放资源 
		os.close();
		is.close();
		
		// 4、释放资源 
		requestSocket.close();
		
		serverSocket.close();
		
				
//		String requestString = receiveStream.readUTF();
//		System.out.println(requestString);
//		String[] split = requestString.split("&");
//
//		for (String string : split) {
//			String[] split2 = string.split("=");
//			if (split2[0].equals("userName")) {
//				userName = split2[1];
//			}
//			if (split2[0].equals("passWord")) {
//				passWord = split2[1];
//			}
//		}
//		String checkResult = checkLogin(userName, passWord);
//
//		// 向客户端发送数据
//		sendMsg(requestSocket, checkResult);
//		int len = -1;
//		while ((len = receiveStream.read(flush)) != -1) {
//			System.out.println("服务端正在写入数据......");
//			os.write(flush, 0, len);
//		}
//		os.flush();
//		os.close();
//		// 4、释放资源
//		receiveStream.close();
//		requestSocket.close();
//		serverSocket.close();
	}

	private void sendMsg(Socket requestSocket, String checkResult) throws IOException {
		DataOutputStream sendStream = new DataOutputStream(requestSocket.getOutputStream());
		String sendMsg = checkResult;
		sendStream.writeUTF(sendMsg);
		sendStream.flush();
	}

	private String checkLogin(String userName, String passWord) {
		if (userName.matches("root|dyk")) {
			switch (userName) {
			case "root": {
				if (passWord.equals("root")) {
					System.out.println("success");
					return "success";
				} else {
					System.out.println("fail");
					return "fail";
				}
			}
			case "dyk": {
				if (passWord.equals("dyk")) {
					System.out.println("success");
					return "success";
				} else {
					System.out.println("fail");
					return "fail";
				}
			}
			default:
				return "fail";
			}
		}
		return "fail";
	}
}
