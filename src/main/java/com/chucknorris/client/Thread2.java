package com.chucknorris.client;

public class Thread2 extends Thread{
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(100);
				System.out.println(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
