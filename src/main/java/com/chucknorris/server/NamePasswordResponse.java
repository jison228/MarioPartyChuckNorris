package com.chucknorris.server;

public class NamePasswordResponse {
	public String name;
	public String password;
	public boolean reg;
	
	public NamePasswordResponse(String name, String password,boolean reg) {
		this.name = name;
		this.password = password;
		this.reg = reg;
	}
}
