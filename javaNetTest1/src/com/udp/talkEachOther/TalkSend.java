package com.udp.talkEachOther;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable {
	
	private DatagramSocket sendSocket;
	private BufferedReader input;
	private String toIP;
	private int toPort;
	
	
	
	public TalkSend(int sendlocalPort, String toIP, int toPort) {
		super();
		this.toIP = toIP;
		this.toPort = toPort;
		try {
			sendSocket = new DatagramSocket(sendlocalPort);
			input = new BufferedReader(new InputStreamReader(System.in));
		} catch (SocketException e) {
			System.out.println("socket创建失败 ");
		}
	}



	@Override
	public void run() {
		while(true) {
			String inputString = new String();
			try {
				inputString = input.readLine();
				byte[] sendByteArray = inputString.getBytes();
				 //3、 封装成DatagramPacket 包裹，需要指定目的地
				DatagramPacket sendPacket =new DatagramPacket(sendByteArray,0,sendByteArray.length,
						new InetSocketAddress(this.toIP,this.toPort));
				//4、发送包裹send​(DatagramPacket p) *
				sendSocket.send(sendPacket);
				if(inputString.equals("bye")) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		// 5、释放资源
		sendSocket.close();
	}

}
