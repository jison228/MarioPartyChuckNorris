package com.chucknorris.server;

public class NamePasswordResponse {
	public String name;
	public String password;
	
	public NamePasswordResponse(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
}
