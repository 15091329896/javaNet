package com.test.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClient {

	private DatagramSocket socket;

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}

	public static void main(String[] args) throws Exception {

		/**
		 * * 基本流程:接收端 1、使用DatagramSocket 指定端口 创建发送端 2、准备数据 一定转成字节数组 3、
		 * 封装成DatagramPacket 包裹，需要指定目的地 4、接收包裹send​(DatagramPacket p) * 5、释放资源
		 * 
		 * 
		 */
		UDPClient udpClient = new UDPClient();
		udpClient.get();

	}

	private void get() throws Exception {
		start();
		getData(socket);
		socket.close();
	}

	private void start() throws SocketException {
		DatagramSocket socket = new DatagramSocket(9999);
		setSocket(socket);
	}

	private void getData(DatagramSocket socket) throws IOException {
		// 1、使用DatagramSocket 指定端口 创建发送端
		// 2、准备数据 一定转成字节数组
		byte[] datas = new byte[1024 * 60];
		// 3、 封装成DatagramPacket 包裹，需要指定端口
		DatagramPacket packet = new DatagramPacket(datas, datas.length);
		// 4、接收包裹(DatagramPacket p) *
		while (true) {
			printData(socket, datas, packet);
		}
	}

	private void printData(DatagramSocket socket, byte[] datas, DatagramPacket packet) throws IOException {
		socket.receive(packet);
		packet.getData();
		int length = packet.getLength();
		System.out.println(new String(datas, 0, length));
		
	}

}
