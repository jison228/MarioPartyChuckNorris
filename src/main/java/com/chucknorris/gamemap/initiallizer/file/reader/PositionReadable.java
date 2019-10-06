package com.chucknorris.gamemap.initiallizer.file.reader;

import com.chucknorris.commons.Position;

public interface PositionReadable {
	Position readPosition(String data);
}
