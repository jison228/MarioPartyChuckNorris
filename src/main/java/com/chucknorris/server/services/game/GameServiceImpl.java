package com.chucknorris.server.services.game;

import com.chucknorris.game.Game;
import com.chucknorris.game.GameResponse;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;
import com.chucknorris.server.command.response.BadRequestResponse;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.repositories.game.GameRepository;
import com.chucknorris.server.repositories.game.InMemoryGameRepository;

public class GameServiceImpl implements GameService {
	private GameRepository repository;

	public GameServiceImpl() {
		repository = InMemoryGameRepository.getRepository();
	}

	public GameServiceImpl(GameRepository repository) {
		this.repository = repository;
	}

	@Override
	public ServerResponse createGame(GameInformation gameInformation) {
		Game game = new Game(gameInformation);

		String gameId = repository.createGame(game);

		GameResponse response = new GameResponse();

		response.gameId = gameId;

		return response;
	}

	@Override
	public Player getPlayer(String gameId, String playerId) {
		Game game = repository.getGame(gameId);

		if (game == null) {
			return null;
		}

		return game.getPlayer(playerId);
	}

	@Override
	public ServerResponse movePlayer(String gameId, String playerId) {
		Game game = repository.getGame(gameId);

		if (game == null) {
			return new BadRequestResponse();
		}

		Player player = getPlayer(gameId, playerId);

		GameResponse gameResponse = game.play(player);

		return gameResponse;
	}
}
