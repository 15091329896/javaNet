package com.test.udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UserA {
	public static void main(String[] args) throws Exception {
		/*
		 * UDP通信过程
		 *
		 * 		创建socket
		 * 		
		 * 			接收数据包
		 * 				创建接收数据包
		 * 				socket接收到数据包中
		 * 				从数据包取出数据
		 * 			发送数据包
		 * 				创建发送的数据包
		 * 				使用socket发送数据包
		 * 
		 */
		
		new UserA().test();
		
		
		
	}

	private void test() throws Exception {
		DatagramSocket socket = new DatagramSocket(9999);
		sendMsg(socket);
//		getMsg(socket);
		
	}

	private void getMsg(DatagramSocket socket) throws IOException {
//		10K的数据包大小
		byte[] buf = new byte[1024 * 10];
		DatagramPacket packet = new DatagramPacket(buf , buf.length);
		while (true) {
			System.out.print("用户A的接收：");
			socket.receive(packet);
			packet.getData();
			System.out.println(new String(buf, 0, buf.length));
		}
	}

	private void sendMsg(DatagramSocket socket) throws IOException {
		Scanner scanner = new Scanner(System.in);
		byte[] buf = null;
		while (true) {
			System.out.print("用户A的发送：");
			scanner.reset();
			buf = scanner.next().getBytes();
			DatagramPacket packet = new DatagramPacket(buf , buf.length, new InetSocketAddress("localhost", 8888));
			socket.send(packet);
		}
	}
}
