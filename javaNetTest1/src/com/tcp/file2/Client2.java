package com.tcp.file2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client2 {
	public static void main(String[] args) throws Exception {
		new Client2().clientTest();
		
	}

	private void clientTest() throws Exception {
		System.out.println("-----Client2-----");
		Socket client =new Socket("localhost",8888);
		//2、操作: 拷贝 上传
		InputStream is =new BufferedInputStream(new FileInputStream("src/test.png"));
		OutputStream os =new BufferedOutputStream(client.getOutputStream());
		byte[] flush =new byte[1024];
		int len = -1;
		while((len=is.read(flush))!=-1) {
			os.write(flush,0,len);
		}
		os.flush();
		//3、释放资源 
		os.close();
		is.close();
		client.close();
	}

	private void receiveMsg(Socket clientSocket) throws IOException {
		DataInputStream receiveStream =new DataInputStream(clientSocket.getInputStream());
		String receiveString =receiveStream.readUTF();
		System.out.println("从服务端接收的数据是：" + receiveString);
	}

	
	private OutputStream senMesg(Socket clientSocket) throws IOException {
//		读文件到内存中
		byte[] flush =new byte[1024];
		InputStream is =new BufferedInputStream(new FileInputStream("src/test.png"));
		OutputStream sendStream =new BufferedOutputStream(clientSocket.getOutputStream());
		
//		InputStream is =new BufferedInputStream(new FileInputStream("src/ndl.png"));
//		OutputStream os =new BufferedOutputStream(client.getOutputStream());
//		byte[] flush =new byte[1024];
		
		
		int len = -1;
		while ((len = is.read(flush)) != -1) {
			System.out.println("客户端正在上传数据......");
			sendStream.write(flush, 0 , len);
		}
		is.close();
		sendStream.flush();
		return sendStream;
	}
}
