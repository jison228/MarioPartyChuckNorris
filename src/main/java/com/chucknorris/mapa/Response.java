package com.chucknorris.mapa;

import java.util.ArrayList;

import com.chucknorris.commons.Position;

public class Response {
	public Node pos;
	public ArrayList<Position> availablePositions;
	public int movs;
	
	public Response(Node pos , ArrayList<Position> availablePositions , int movs) {
		super();
		this.pos = pos;
		this.availablePositions = availablePositions;
		this.movs = movs;
	}
}
