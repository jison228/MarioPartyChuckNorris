package com.chucknorris.client;

import java.net.InetAddress;
import java.net.Socket;

import com.chucknorris.server.config.GlobalConfig;

public class Client {
	//private String ip;
	private static final int SERVER_PORT = GlobalConfig.SERVER_PORT;
	
	
	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getByName("localhost");			
			Socket socketServer = new Socket(ip, SERVER_PORT);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
