package com.chucknorris.client;

import com.chucknorris.client.tablero.MainGameScreen;

public class ClientWorker extends Thread{

	MainGameScreen frame;
	
	public ClientWorker(MainGameScreen frame) {
		this.frame = frame;
	}
	
	@Override
	public void run() {
		try {
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
