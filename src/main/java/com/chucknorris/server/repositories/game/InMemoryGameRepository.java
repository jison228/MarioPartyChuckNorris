package com.chucknorris.server.repositories.game;

import com.chucknorris.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryGameRepository implements GameRepository {
	private static GameRepository repository;
	private Map<String, Game> gameDatabase;

	private InMemoryGameRepository() {
		gameDatabase = new HashMap<>();
	}


	public static GameRepository getRepository() {
		if (repository == null) {
			repository = new InMemoryGameRepository();
		}

		return repository;
	}

	@Override
	public String createGame(Game game) {
		String gameId = UUID.randomUUID().toString();

		gameDatabase.put(gameId, game);

		return gameId;
	}
}
