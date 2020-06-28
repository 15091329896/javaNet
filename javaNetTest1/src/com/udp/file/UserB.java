package com.udp.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.udp.bean.Book;

public class UserB {
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
		
		new UserB().test();
		
		
		
	}

	private void test() throws Exception {
		DatagramSocket socket = new DatagramSocket(8888);
//		sendMsg(socket);
//		getMsg(socket);
//		getMsgObject(socket);
		getMsgFile(socket);
		socket.close();
		
	}
	
	private void getMsgFile(DatagramSocket socket) throws Exception {
//		10K的数据包大小,封装数据包中的数据
		byte[] byteArray = new byte[1024 * 60];
		DatagramPacket packet = new DatagramPacket(byteArray, byteArray.length);
		boolean tmp = true;
		while (tmp ) {
			System.out.print("用户B的接收：");
			socket.receive(packet);
			packet.getData();
			byte[] dataArray = packet.getData();
			int len = packet.getLength();
			byteArrayToFile(byteArray, "src/222.png");
		}
	}
	
	public void byteArrayToFile(byte[] src,String filePath) {
		//1、创建源
		File dest = new File(filePath);
		//2、选择流
		InputStream  is =null;
		OutputStream os =null;
		try {
			is =new ByteArrayInputStream(src);
			os = new FileOutputStream(dest);
			//3、操作 (分段读取)
			byte[] flush = new byte[5]; //缓冲容器
			int len = -1; //接收长度
			while((len=is.read(flush))!=-1) {
				os.write(flush,0,len);			//写出到文件	
			}		
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//4、释放资源
			try {
				if (null != os) {
					os.close();
				} 
			} catch (Exception e) {
			}
		}
	}
	
	private void getMsgObject(DatagramSocket socket) throws Exception {
//		10K的数据包大小,封装数据包中的数据
		byte[] buf = new byte[1024 * 10];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		boolean tmp = true;
		while (tmp ) {
//			tmp = false;
			System.out.print("用户B的接收：");
			socket.receive(packet);
			packet.getData();
			byte[] dataArray = packet.getData();
			int len = packet.getLength();
			ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(dataArray)));
			System.out.println(inputStream.readUTF());
			System.out.println(inputStream.readInt());
			System.out.println(inputStream.readBoolean());
			System.out.println(inputStream.readChar());
			System.out.println(inputStream.readDouble());
			
			Object readObject = inputStream.readObject();
			
			if (readObject instanceof Book) {
				Book book = (Book) readObject;
				System.out.println(book.toString());
			}
			
		}
	}
	
	
	private void getMsg(DatagramSocket socket) throws IOException {
//		10K的数据包大小,封装数据包中的数据
		byte[] buf = new byte[1024 * 10];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		boolean tmp = true;
		while (tmp ) {
//			tmp = false;
			System.out.print("用户B的接收：");
			socket.receive(packet);
			packet.getData();
			byte[] data = packet.getData();
			int len = packet.getLength();
			DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(data)));
			System.out.println(inputStream.readUTF());
			System.out.println(inputStream.readInt());
			System.out.println(inputStream.readBoolean());
			System.out.println(inputStream.readChar());
			System.out.println(inputStream.readDouble());
//			System.out.println(new String(buf, 0, packet.getLength()));
		}
	}

	/*
	 * public static void main(String[] args) throws Exception {
		System.out.println("接收方启动中.....");
		// 1、使用DatagramSocket  指定端口 创建接收端
		DatagramSocket server =new DatagramSocket(6666);
		// 2、准备容器 封装成DatagramPacket 包裹
		byte[] container =new byte[1024*60];
		DatagramPacket packet = new DatagramPacket(container,0,container.length);
		// 3、阻塞式接收包裹receive​(DatagramPacket p)
		server.receive(packet); //阻塞式
		// 4、分析数据    将字节数组还原为对应的类型
		//    byte[]  getData​()
		//                getLength​()
		 byte[]  datas =packet.getData();
		 int len = packet.getLength();		 
		DataInputStream dis =new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		//顺序与写出一致
		String msg = dis.readUTF(); 
		int age = dis.readInt();
		boolean flag = dis.readBoolean();
		char ch = dis.readChar();
		System.out.println(msg+"-->"+flag);
		// 5、释放资源
		 server.close();
	}
	 */
	
	
	private void sendMsg(DatagramSocket socket) throws IOException {
		Scanner scanner = new Scanner(System.in);
		byte[] buf = new byte[1024 * 10];
		DatagramPacket packet = new DatagramPacket(buf , buf.length, new InetSocketAddress("localhost", 9999));
		while (true) {
			System.out.print("用户B的发送：");
			String next = scanner.next();
			socket.send(packet);
		}
	}
}
