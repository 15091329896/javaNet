package com.test.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPServer {
	
	private static Scanner scanner = new Scanner(System.in);
	private static  InetSocketAddress socketAddress = new InetSocketAddress("localhost", 9999);
	
	private DatagramSocket socket;
	
	
	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public static void main(String[] args) throws Exception {
		

		/**
		 * * 基本流程: 发送端
 * 1、使用DatagramSocket  指定端口 创建发送端
 * 2、准备数据 一定转成字节数组
 * 3、 封装成DatagramPacket 包裹，需要指定目的地
 * 4、发送包裹send​(DatagramPacket p) * 
 * 5、释放资源
		 * 
		 * 
		 */
		
		
		
		UDPServer udpServer = new UDPServer();
		udpServer.send();
		
	}

	private void send() throws Exception {
		start();
		while (true) {
			String next = getSendMsg();
			sendData(next, socket);
		}
	}

	private String getSendMsg() {
		System.out.println("请输入要发送的内容");
		String next = scanner.next();
		return next;
	}

	private void start() throws SocketException {
		DatagramSocket socket =new DatagramSocket(8888);
		setSocket(socket);
	}

	private void sendData(String next, DatagramSocket socket) throws IOException {
		// 1、使用DatagramSocket  指定端口 创建发送端
		 //2、准备数据 一定转成字节数组
		byte[] datas = next.getBytes();
		 //3、 封装成DatagramPacket 包裹，需要指定目的地
		DatagramPacket packet = new DatagramPacket(datas, datas.length, socketAddress);
		//4、发送包裹send​(DatagramPacket p) * 
		socket.send(packet);
	}
}
