package com.test.udp.object;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.udp.bean.Book;

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
//		sendMsg(socket);
//		getMsg(socket);
//		sendMsgDataType(socket);
		sendMsgObject(socket);
	}

	private void sendMsgObject(DatagramSocket socket) throws IOException {
		Scanner scanner = new Scanner(System.in);
		byte[] buf = null;
		boolean tmp = true;
		while (tmp ) {
			tmp = false;
			System.out.print("用户A的发送：");
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			ObjectOutputStream output =new ObjectOutputStream(new BufferedOutputStream(byteOutput));
			//操作数据类型 +数据
			output.writeUTF("这是哈哈哈哈哈串");
			output.writeInt(2222);
			output.writeBoolean(false);
			output.writeChar('a');
			output.writeDouble(20.3);
			Book book = new Book(1001L, "《明哲言行录》", 20.5, "第欧根尼");
			output.writeObject(book);
			output.flush();
			buf =byteOutput.toByteArray();
			DatagramPacket packet = new DatagramPacket(buf , buf.length, new InetSocketAddress("localhost", 8888));
			socket.send(packet);
		}
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
	
	private void sendMsgDataType(DatagramSocket socket) throws IOException {
		Scanner scanner = new Scanner(System.in);
		byte[] buf = null;
		boolean tmp = true;
		while (tmp ) {
			tmp = false;
			System.out.print("用户A的发送：");
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			DataOutputStream dataOutput =new DataOutputStream(new BufferedOutputStream(byteOutput));
			//操作数据类型 +数据
			dataOutput.writeUTF("这是哈哈哈哈哈串");
			dataOutput.writeInt(2222);
			dataOutput.writeBoolean(false);
			dataOutput.writeChar('a');
			dataOutput.writeDouble(20.3);
			dataOutput.flush();
			buf =byteOutput.toByteArray();
			DatagramPacket packet = new DatagramPacket(buf , buf.length, new InetSocketAddress("localhost", 8888));
			socket.send(packet);
		}
	}
	
	/*
	 * public static void main(String[] args) throws Exception {
		System.out.println("发送方启动中.....");
		 // 1、使用DatagramSocket  指定端口 创建发送端
		DatagramSocket Client2 =new DatagramSocket(8888);
		 //2、准备数据 一定转成字节数组
		//写出
		ByteArrayOutputStream baos =new ByteArrayOutputStream();
		DataOutputStream dos =new DataOutputStream(new BufferedOutputStream(baos));
		//操作数据类型 +数据
		dos.writeUTF("编码辛酸泪");
		dos.writeInt(18);
		dos.writeBoolean(false);
		dos.writeChar('a');
		dos.flush();
		byte[] datas =baos.toByteArray();	
		 //3、 封装成DatagramPacket 包裹，需要指定目的地
		DatagramPacket packet =new DatagramPacket(datas,0,datas.length,
				new InetSocketAddress("localhost",6666));
		//4、发送包裹send​(DatagramPacket p) * 
		Client2.send(packet);
		// 5、释放资源
		Client2.close();
	}

	 * 
	 * 
	 * */
}
