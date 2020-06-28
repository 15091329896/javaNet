package com.tcp.login;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception {
		new Client().clientTest();
		
	}

	private void clientTest() throws Exception {
		System.out.println("-----Client2-----");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
//		获取用户输入的用户名和密码
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名：");
		String userName = reader.readLine();
		System.out.println("请输入密码：");
		String passWord = reader.readLine();
		StringBuffer requestStringbuffer = new StringBuffer();
		requestStringbuffer.append("userName=");
		requestStringbuffer.append(userName);
		requestStringbuffer.append("&");
		requestStringbuffer.append("passWord=");
		requestStringbuffer.append(passWord);
		
		Socket clientSocket =new Socket("localhost",8888);
		//2、操作: 输入输出流操作
//		向服务端发送数据
		DataOutputStream sendStream = senMesg(requestStringbuffer, clientSocket);
//		从服务端接收数据
		receiveMsg(clientSocket);
		//3、释放资源 
		sendStream.close();
		clientSocket.close();
	}

	private void receiveMsg(Socket clientSocket) throws IOException {
		DataInputStream receiveStream =new DataInputStream(clientSocket.getInputStream());
		String receiveString =receiveStream.readUTF();
		System.out.println("从服务端接收的数据是：" + receiveString);
	}

	private DataOutputStream senMesg(StringBuffer requestStringbuffer, Socket clientSocket) throws IOException {
		DataOutputStream sendStream =new DataOutputStream(clientSocket.getOutputStream());
		String requestString = requestStringbuffer.toString();
		sendStream.writeUTF(requestString);
		sendStream.flush();
		return sendStream;
	}
}
