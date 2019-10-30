package com.chucknorris.server.command;

import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.services.game.GameService;
import com.chucknorris.server.services.game.GameServiceImpl;

public class MovePlayerCommand extends Command {
	private GameService gameService = new GameServiceImpl();

	@Override
	protected ServerResponse execute(CommandData commandData) throws Throwable {
		String gameId = commandData.getValueAsString("game_id");
		String playerId = commandData.getValueAsString("player_id");

		ServerResponse response = gameService.movePlayer(gameId, playerId);

		return response;
	}

}
