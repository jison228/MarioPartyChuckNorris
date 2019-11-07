package com.chucknorris.client;

import com.chucknorris.commons.Position;

public class ClientNode {
	private String type;
	private Position position;
	
	public ClientNode(String type, Position position) {
		this.type = type;
		this.position = position;
	}
	
	public String getType() {
		return type;
	}
	
	public Position getPosition() {
		return position;
	}
}
