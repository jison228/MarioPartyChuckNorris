package com.chucknorris.server.command.game;

import com.chucknorris.commons.Position;
import com.chucknorris.server.command.Command;
import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.BadRequestResponse;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.services.game.GameService;
import com.chucknorris.server.services.game.GameServiceImpl;

import java.util.Map;

public class ResolveIntersectionCommand extends Command {
	private GameService gameService;

	public ResolveIntersectionCommand() {
		gameService = new GameServiceImpl();
	}

	public ResolveIntersectionCommand(GameService gameService) {
		this.gameService = gameService;
	}

	@Override
	protected ServerResponse execute(CommandData commandData) {
		String gameId = commandData.getValueAsString("game_id");
		String playerId = commandData.getValueAsString("player_id");
		Map positionCoords = (Map) commandData.getData().get("position_chosen");

		if (positionCoords == null) {
			return new BadRequestResponse();
		}

		Position position = new Position((int) positionCoords.get("x"), (int) positionCoords.get("y"));

		return gameService.resolveIntersection(gameId, playerId, position);
	}
}
