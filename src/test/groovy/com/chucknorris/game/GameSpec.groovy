package com.chucknorris.game

import com.chucknorris.commons.Dice
import com.chucknorris.gamemap.GameMap
import com.chucknorris.gamemap.MapUtils
import com.chucknorris.player.Player
import spock.lang.Specification

class GameSpec extends Specification {

    void "test move players macri three positions in map_1, expected to win 10 coins"() {
        given:
        Dice dice = Spy(new Dice(1, 6))

        Player macri = new Player("Macri", 0)

        List<Player> playerList = Arrays.asList(macri)

        GameMap gameMap = MapUtils.buildGameMapThroughCSVAndInitiallizePositions("map_1.txt", playerList)

        1 * dice.roll() >> 3

        Game game = new Game(playerList, gameMap, dice)

        when:
        GameResponse dummyResponse = game.play(macri)

        then:
        macri.getCoins() == 10
    }
}
