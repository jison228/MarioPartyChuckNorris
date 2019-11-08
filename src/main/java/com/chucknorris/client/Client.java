package com.chucknorris.client;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import com.chucknorris.Command;
import com.google.gson.Gson;

public class Client {
	// private String ip;

	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getByName("192.168.43.102");
			Socket socketServer = new Socket(ip, 22222);
			PrintStream ps = new PrintStream(socketServer.getOutputStream(), true);
			Command brigadaA = new Command("TirarDado", "");
			Gson gson = new Gson();
			String send = gson.toJson(brigadaA);
			ps.println(send);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
