package com.chucknorris.server.command;

import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.services.game.GameService;

import java.net.Socket;
import java.util.Map;

public class CreateGameCommand extends Command {
	private GameService gameService;

	public CreateGameCommand(GameService gameService) {
		this.gameService = gameService;
	}

	@Override
	public ServerResponse process(Map commandDataDto, Socket socket) {
//		return gameService.createGame(commandDataDto);
		return null;
	}
}
