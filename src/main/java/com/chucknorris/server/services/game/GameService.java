package com.chucknorris.server.services.game;

import com.chucknorris.server.command.response.ServerResponse;

import java.util.Map;

public interface GameService {
	ServerResponse createGame(Map gameData);

	ServerResponse movePlayer(Map data);
}
