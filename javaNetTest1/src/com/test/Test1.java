package com.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test1 {
	public static void main(String[] args) throws UnknownHostException {
		//使用getLocalHost方法创建InetAddress对象  本机
				InetAddress addr = InetAddress.getLocalHost();
//				System.out.println(addr.getHostAddress());  //返回：192.168.1.110
//				System.out.println(addr.getHostName());  //输出计算机名
				
				
				
//		System.out.println(InetAddress.getLocalHost().getHostAddress());
//				//根据域名得到InetAddress对象
//				addr = InetAddress.getByName("www.cctv.com"); 
//				System.out.println(addr.getHostAddress());  //返回 shsxt服务器的ip:
//				System.out.println(addr.getHostName());  //输出：www.shsxt.com
//				System.out.println(InetAddress.getByName("www.baidu.com").getHostAddress());
				
//				//根据ip得到InetAddress对象
//				addr = InetAddress.getByName("127.0.0.0"); 
				
//				System.out.println(addr.getHostAddress());  //返回 shsxt的ip:123.56.138.176
//				System.out.println(addr.getHostName());  //输出ip而不是域名。如果这个IP地 址不存在或DNS服务器不允许进行IP地址和域名的映射，
		
//				System.out.println(InetAddress.getByName("mp.csdn.net").getHostAddress());
				
	}
}
