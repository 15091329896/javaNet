package com.tcp.login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {

		new Server().testServer();

	}

	private void testServer() throws Exception {
		String userName = "";
		String passWord = "";
		System.out.println("-----Server2-----");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket serverSocket = new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		Socket requestSocket = serverSocket.accept();
		System.out.println("一个客户端建立了连接");
		// 3、操作: 输入输出流操作
		DataInputStream receiveStream = new DataInputStream(requestSocket.getInputStream());
		String requestString = receiveStream.readUTF();
		System.out.println(requestString);
		String[] split = requestString.split("&");

		for (String string : split) {
			String[] split2 = string.split("=");
			if (split2[0].equals("userName")) {
				userName = split2[1];
			}
			if (split2[0].equals("passWord")) {
				passWord = split2[1];
			}
		}
		String checkResult = checkLogin(userName, passWord);

		// 向客户端发送数据
		sendMsg(requestSocket, checkResult);

		// 4、释放资源
		receiveStream.close();
		requestSocket.close();
		serverSocket.close();
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
