package com.udp.talkEachOther;

public class UserB {
	public static void main(String[] args) {
		System.out.println("用户B:");
		new Thread(new TalkSend(30001, "192.168.1.3", 40000)).start();
		new Thread(new TalkReceive(40001, "userA")).start();
		
	}
}	
