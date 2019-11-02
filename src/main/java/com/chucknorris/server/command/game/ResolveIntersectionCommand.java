package com.chucknorris.server.command.game;

import com.chucknorris.server.command.Command;
import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.services.game.GameService;
import com.chucknorris.server.services.game.GameServiceImpl;

public class ResolveIntersectionCommand extends Command {
	private GameService gameService;

	public ResolveIntersectionCommand() {
		gameService = new GameServiceImpl();
	}

	public ResolveIntersectionCommand(GameService gameService) {
		this.gameService = gameService;
	}

	@Override
	protected ServerResponse execute(CommandData commandData) throws Throwable {
		String gameId = commandData.getValueAsString("game_id");
		String playerId = commandData.getValueAsString("player_id");

		return gameService.resolveIntersection(gameId, playerId);
	}
}
