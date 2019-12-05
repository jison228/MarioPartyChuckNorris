package com.chucknorris.server;

public class SalaResponse {
	public String name;
	public String password;
	public boolean priv;
	
	public SalaResponse(String name, String password, boolean priv) {
		this.name = name;
		this.password = password;
		this.priv = priv;
	}
	
}
