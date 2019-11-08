package com.chucknorris.client;

import java.net.InetAddress;
import java.net.Socket;

public class Client {
	//private String ip;

	
	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getByName("localhost");			
			Socket socketServer = new Socket(ip, 30000);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
