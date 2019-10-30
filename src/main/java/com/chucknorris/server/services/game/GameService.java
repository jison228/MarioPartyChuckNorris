package com.chucknorris.server.services.game;

import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;
import com.chucknorris.server.command.response.ServerResponse;

public interface GameService {
	ServerResponse createGame(GameInformation gameInformation);

	Player getPlayer(String gameId, String playerId);

	ServerResponse movePlayer(String gameId, String playerId) throws Exception;
}
