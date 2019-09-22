package com.chucknorris.mapa;

import java.util.ArrayList;

import com.chucknorris.commons.Position;

public class Response {
	public Position pos;
	public String NodeType;
	public ArrayList<Position> availablePositions;
	public int movs;
	
	public Response(Position pos, String nodeType, ArrayList<Position> availablePositions, int movs) {
		super();
		this.pos = pos;
		NodeType = nodeType;
		this.availablePositions = availablePositions;
		this.movs = movs;
	}
}
