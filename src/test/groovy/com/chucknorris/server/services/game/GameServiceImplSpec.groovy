package com.chucknorris.server.services.game

import com.chucknorris.commons.Dice
import com.chucknorris.game.Game
import com.chucknorris.gamemap.GameMap
import com.chucknorris.gui.GameInformation
import com.chucknorris.player.Player
import com.chucknorris.player.PlayerBuilder
import com.chucknorris.server.repositories.game.GameRepository
import spock.lang.Specification

class GameServiceImplSpec extends Specification {

    void "test getPlayer when gameId doesnt exists, expected null player"() {
        given:
        GameRepository gameRepository = Mock(GameRepository)
        gameRepository.getGame("gameId") >> null

        GameService gameService = new GameServiceImpl(gameRepository)

        when:
        Player player = gameService.getPlayer("gameId", "player_id")

        then:
        player == null
    }

    void "test getPlayer when playerId doesnt exists, expected null player"() {
        given:
        GameRepository gameRepository = Mock(GameRepository)
        gameRepository.getGame("gameId") >> mockGame()

        GameService gameService = new GameServiceImpl(gameRepository)

        when:
        Player player = gameService.getPlayer("gameId", "player_id")

        then:
        player == null
    }

    void "test getPlayer for Cristina, expected player to be Cristina"() {
        given:
        GameRepository gameRepository = Mock(GameRepository)
        gameRepository.getGame("gameId") >> mockGame()

        GameService gameService = new GameServiceImpl(gameRepository)

        when:
        Player player = gameService.getPlayer("gameId", "11c1de64-6714-45cf-ba4e-9c253f7dfad1")

        then:
        player != null
    }

    private static Game mockGame() {
        new Game(
                new GameInformation(
                        Arrays.asList(
                                new PlayerBuilder().setId("11c1de64-6714-45cf-ba4e-9c253f7dfad1").build(),
                                new PlayerBuilder().setId("8ecf4947-b41b-4574-b6cb-df23aaae70b1").build()
                        ),
                        new GameMap(null, null),
                        new Dice(1, 1),
                        3
                )
        )
    }
}
