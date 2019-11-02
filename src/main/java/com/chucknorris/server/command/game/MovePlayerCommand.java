package com.chucknorris.server.command.game;

import com.chucknorris.server.command.Command;
import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.services.game.GameService;
import com.chucknorris.server.services.game.GameServiceImpl;

public class MovePlayerCommand extends Command {
	private GameService gameService;

	public MovePlayerCommand() {
		gameService = new GameServiceImpl();
	}

	public MovePlayerCommand(GameService gameService) {
		this.gameService = gameService;
	}

	@Override
	protected ServerResponse execute(CommandData commandData) throws Throwable {
		String gameId = commandData.getValueAsString("game_id");
		String playerId = commandData.getValueAsString("player_id");

		return gameService.movePlayer(gameId, playerId);
	}

}
