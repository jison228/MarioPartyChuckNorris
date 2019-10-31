package com.chucknorris.server.services.game

import com.chucknorris.commons.Dice
import com.chucknorris.game.Game
import com.chucknorris.game.GameResponse
import com.chucknorris.gamemap.GameMap
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader
import com.chucknorris.gui.GameInformation
import com.chucknorris.player.Player
import com.chucknorris.player.PlayerBuilder
import com.chucknorris.server.command.response.ServerResponse
import com.chucknorris.server.repositories.game.GameRepository
import spock.lang.Specification

class GameServiceImplSpec extends Specification {

    private GameMap gameMap1 = new MapFileCSVReader("map_1.txt").buildGameMap()

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
        gameRepository.getGame("gameId") >> mockGameForGetPlayerTest()

        GameService gameService = new GameServiceImpl(gameRepository)

        when:
        Player player = gameService.getPlayer("gameId", "player_id")

        then:
        player == null
    }

    void "test getPlayer for Cristina, expected player to be Cristina"() {
        given:
        GameRepository gameRepository = Mock(GameRepository)
        gameRepository.getGame("gameId") >> mockGameForGetPlayerTest()

        GameService gameService = new GameServiceImpl(gameRepository)

        when:
        Player player = gameService.getPlayer("gameId", "11c1de64-6714-45cf-ba4e-9c253f7dfad1")

        then:
        player != null
    }

    void "test move player for Cristina, expected game response with location and without movements for map_1"() {
        setup:
        GameRepository gameRepository = Mock(GameRepository)
        gameRepository.getGame("game1") >> mockGameForMovePlayerTest()

        GameService gameService = new GameServiceImpl(gameRepository)

        String mileiPlayerId = "11c1de64-6714-45cf-ba4e-9c253f7dfad1"

        when:
        //Movemos al player que usa a Milei
        ServerResponse serverResponse = gameService.movePlayer("game1", mileiPlayerId)

        GameResponse gameResponse = (GameResponse) serverResponse

        Player mileiPlayer = gameService.getPlayer("game1", mileiPlayerId)

        then:
        gameResponse.gameId == "game1"
        gameResponse.diceResult == 1
        gameResponse.movementsLeft == 0
        gameResponse.nodePath.size() == 1
        gameResponse.nodePath.peek().getType() == "WHITE"
        mileiPlayer.printWithPesos() == "Javier Milei - 0"
    }

    private Game mockGameForMovePlayerTest() {
        Player milei = new PlayerBuilder()
                .setCharacter("Javier Milei")
                .setId("8ecf4947-b41b-4574-b6cb-df23aaae70b1")
                .build()

        Player mauri = new PlayerBuilder()
                .setCharacter("MauriCEOMcree")
                .setId("11c1de64-6714-45cf-ba4e-9c253f7dfad1")
                .build()

        List<Player> playerList = Arrays.asList(milei, mauri)

        gameMap1.initializePlayers(playerList)

        Dice spiedDice = Spy(new Dice(1, 1))

        spiedDice.roll() >>> [1, 3]

        return new Game(new GameInformation(playerList, gameMap1, spiedDice, 3))
    }

    private static Game mockGameForGetPlayerTest() {
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
