package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PaChongTest {
	public static void main(String[] args) throws Exception {
		/**
		 * 网络爬虫
		 * 		使用URL对象访问http连接
		 */
		
		
//		visitBaidu();
		visitDianPing();
		
		
		
		
	}

	private static void visitDianPing() throws IOException {
		URL url = new URL("https://www.dianping.com/");
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		httpConnection.setRequestMethod("GET");
//		表示当前访问方式是浏览器方式
		httpConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String readLine = null;
		while ((readLine = bufferedReader.readLine())!= null) {
			System.out.println(readLine);
		}
		
	}

	private static void visitBaidu() throws MalformedURLException, IOException {
		URL url = new URL("https://www.dianping.com/");
		URLConnection connection = url.openConnection();
		
		InputStream inputStream = connection.getInputStream();
		
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String readLine = null;
		while ((readLine = bufferedReader.readLine())!= null) {
			System.out.println(readLine);
		}
	}
}
