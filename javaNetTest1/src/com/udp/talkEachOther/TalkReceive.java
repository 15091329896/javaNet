package com.udp.talkEachOther;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkReceive implements Runnable{

	private DatagramSocket receiveSocket ;
	private String userName ;
	
	public TalkReceive(int receiveLocalPort, String userName) {
		this.userName = userName;
		try {
			this.receiveSocket = new DatagramSocket(receiveLocalPort);
		} catch (SocketException e) {
			System.out.println(" receiveSocket 创建失败 ");
		}
	}



	@Override
	public void run() {
		while(true) {
			// 2、准备容器 封装成DatagramPacket 包裹
			byte[] receiveArray =new byte[1024*60];
			DatagramPacket receivePacket = new DatagramPacket(receiveArray,0,receiveArray.length);
			// 3、阻塞式接收包裹receive​(DatagramPacket p)
			try {
				receiveSocket.receive(receivePacket);//阻塞式
				// 4、分析数据
				 int len = receivePacket.getLength();
				 String receiveString=new String(receiveArray,0,len);
				 System.out.println(userName + " : " + receiveString);
				 if(receiveString.equals("bye")) {
					 break;
				 }
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		// 5、释放资源
		 receiveSocket.close();
	}

}
