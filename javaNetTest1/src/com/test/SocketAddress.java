package com.test;

import java.net.InetSocketAddress;

public class SocketAddress {
	public static void main(String[] args) {
		InetSocketAddress socketAddress = new InetSocketAddress(3306);
		System.out.println(socketAddress.toString());
		System.out.println(socketAddress.getAddress());
		System.out.println(socketAddress.getPort());
		
		
	}
}
