package com.chucknorris.server.repositories.game;

import com.chucknorris.commons.Dice;
import com.chucknorris.game.Game;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Player;
import com.chucknorris.player.PlayerBuilder;

import java.util.*;

public class InMemoryGameRepository implements GameRepository {
	private static GameRepository repository;
	private Map<String, Game> gameDatabase;

	private InMemoryGameRepository() {
		gameDatabase = new HashMap<>();

		Game testGame = createTestGame();
		gameDatabase.put(testGame.printId(), testGame);
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

	@Override
	public Game getGame(String gameId) {
		return gameDatabase.get(gameId);
	}

	private Game createTestGame() {
		try {
			Player mauriPlayer = new PlayerBuilder()
					.setCharacter("Mauricio")
					.setId("mauricio")
					.setPesos(300)
					.setDolar(100)
					.setSalario(600)
					.build();

			Player mileiPlayer = new PlayerBuilder()
					.setCharacter("Milei")
					.setId("milei")
					.setPesos(300)
					.setDolar(100)
					.setSalario(350)
					.build();

			GameMap map = new MapFileCSVReader("map_1.txt").buildGameMap();

			List<Player> players = Arrays.asList(mauriPlayer, mileiPlayer);

			map.initializePlayers(players);

			GameInformation testGameInfo = new GameInformation(players, map, new Dice(1, 6), 10);

			return new Game(testGameInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Game(Collections.emptyList(), new GameMap(Collections.emptyMap(), null));
	}
}
