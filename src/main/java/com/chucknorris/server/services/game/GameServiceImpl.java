package com.chucknorris.server.services.game;

import com.chucknorris.game.Game;
import com.chucknorris.game.GameResponse;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.repositories.game.GameRepository;
import com.chucknorris.server.repositories.game.InMemoryGameRepository;

import java.util.Map;

public class GameServiceImpl implements GameService {
	private GameRepository repository = InMemoryGameRepository.getRepository();

	@Override
	public ServerResponse createGame(GameInformation gameInformation) {
		Game game = new Game(gameInformation);

		String gameId = repository.createGame(game);

		GameResponse response = new GameResponse();

		response.gameId = gameId;

		return response;
	}

	@Override
	public ServerResponse movePlayer(Map data) {
		return null;
	}

	@Override
	public Player getPlayer(String gameId, String playerId) {
		return new Player("Cristina", 3);
	}
}
