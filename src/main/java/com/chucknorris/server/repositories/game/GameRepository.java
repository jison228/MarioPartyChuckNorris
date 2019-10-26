package com.chucknorris.server.repositories.game;

import com.chucknorris.game.Game;

public interface GameRepository {
	String createGame(Game game);
}
