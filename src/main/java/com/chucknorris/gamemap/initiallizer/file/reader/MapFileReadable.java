package com.chucknorris.gamemap.initiallizer.file.reader;

import com.chucknorris.gamemap.GameMap;

public interface MapFileReadable {
	GameMap buildGameMap() throws Exception;
}
