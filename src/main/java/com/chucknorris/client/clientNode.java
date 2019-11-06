package com.chucknorris.client;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;

public class ClientNode {
	private String type;
	private Position position;
	
	public ClientNode(String type, Position position) {
		this.type = type;
		this.position = position;
	}
	
	public ClientNode(Node node) {
		this.type = node.getType();
		this.position = node.getPositionCoords();
	}
	
	public String getType() {
		return type;
	}
	
	public Position getPosition() {
		return position;
	}
}
