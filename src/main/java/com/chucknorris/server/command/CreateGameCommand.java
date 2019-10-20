package com.chucknorris.server.command;

import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.services.game.GameService;

import java.util.Map;

public class CreateGameCommand implements Command {
	private GameService gameService;

	public CreateGameCommand(GameService gameService) {
		this.gameService = gameService;
	}

	@Override
	public ServerResponse process(Map commandDataDto) {
		return gameService.createGame(commandDataDto);
	}
}
