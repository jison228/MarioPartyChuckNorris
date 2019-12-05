package com.chucknorris.gamemap.initiallizer.file.reader;

import com.chucknorris.commons.Position;

public class LineDataDto {
	public String nodeType;
	public String nextNodes;
	public Position position;

	public LineDataDto(Position position, String nodeType, String nextNodes) {
		this.nodeType = nodeType;
		this.nextNodes = nextNodes;
		this.position = position;
	}
}
