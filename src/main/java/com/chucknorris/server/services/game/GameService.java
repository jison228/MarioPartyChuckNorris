package com.chucknorris.server.services.game;

import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;
import com.chucknorris.server.command.response.ServerResponse;

import java.util.Map;

public interface GameService {
	ServerResponse createGame(GameInformation gameInformation);

	ServerResponse movePlayer(Map data);

	Player getPlayer(String gameId, String playerId);
}
