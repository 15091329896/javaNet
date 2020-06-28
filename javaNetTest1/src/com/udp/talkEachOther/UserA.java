package com.udp.talkEachOther;

public class UserA {
	public static void main(String[] args) {
		System.out.println("用户A:");
		new Thread(new TalkSend(30000, "192.168.1.3", 40001)).start();
		new Thread(new TalkReceive(40000, "userB")).start();
		
		
	}
}
