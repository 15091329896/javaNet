package com.test;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("https://www.runoob.com/?s=sdsad");
		//获取四个值
		System.out.println("协议:"+url.getProtocol());
		System.out.println("域名|ip:"+url.getHost());
		System.out.println("端口:"+url.getPort());
		System.out.println("请求资源getFile:"+url.getFile());
		System.out.println("请求资源getPath:"+url.getPath());
		
		//参数
		System.out.println("参数:"+url.getQuery());
		//锚点
		System.out.println("锚点:"+url.getRef());
		
		
		
	}
}	
