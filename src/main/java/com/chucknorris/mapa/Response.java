package com.chucknorris.mapa;

import java.util.ArrayList;

import com.chucknorris.commons.Position;

public class Response {
	public Position pos;
	public String NodeType;
	public ArrayList<Position> availablePositions;
	public int movs;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NodeType == null) ? 0 : NodeType.hashCode());
		result = prime * result + movs;
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response other = (Response) obj;
		if (NodeType == null) {
			if (other.NodeType != null)
				return false;
		} else if (!NodeType.equals(other.NodeType))
			return false;
		if (movs != other.movs)
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		return true;
	}

	public Response(Position pos , String nodeType , ArrayList<Position> availablePositions , int movs) {
		super();
		this.pos = pos;
		NodeType = nodeType;
		this.availablePositions = availablePositions;
		this.movs = movs;
	}
}
