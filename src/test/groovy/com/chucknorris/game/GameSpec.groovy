package com.chucknorris.game

import com.chucknorris.commons.Dice
import com.chucknorris.gamemap.GameMap
import com.chucknorris.gamemap.MapUtils
import com.chucknorris.player.Player
import spock.lang.Specification

class GameSpec extends Specification {

    void "test move players macri 3 positions and then 4 positions in map_1, expected to win 13 coins"() {
        given:
        Dice dice = Spy(new Dice(1, 6))

        Player macri = new Player("Macri", 0)

        List<Player> playerList = Arrays.asList(macri)

        GameMap gameMap = MapUtils.buildGameMapThroughCSVAndInitiallizePositions("map_1.txt", playerList)

        2 * dice.roll() >> 3 >> 4

        Game game = new Game(playerList, gameMap, dice)

        when:
        game.play(macri)

        GameResponse gameResponse = game.play(macri)

        then:
        macri.getCoins() == 10
        gameResponse.movementsLeft == 1
    }
}
