package com.chucknorris.client;

public class Thread1 extends Thread{
	@Override
	public void run() {
		try {
			while(true) {
				System.out.println(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
