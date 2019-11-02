package com.chucknorris.game

import com.chucknorris.player.Player
import com.chucknorris.player.PlayerBuilder
import spock.lang.Specification

class TurnSelectorSpec extends Specification {

    "test getTurn in the order expected"() {
        given:
        Player player1 = buildPlayer("1")
        Player player2 = buildPlayer("2")
        Player player3 = buildPlayer("3")
        Player player4 = buildPlayer("4")

        TurnSelector turnSelector = new TurnSelector([player1, player2, player3, player4])

        then:
        turnSelector.getTurn() == player1
        turnSelector.getTurn() == player2
        turnSelector.getTurn() == player3
        turnSelector.getTurn() == player4

        turnSelector.getTurn() == player1
        turnSelector.getTurn() == player2
        turnSelector.getTurn() == player3
        turnSelector.getTurn() == player4
    }

    Player buildPlayer(String id) {
        return new PlayerBuilder().setId(id).build()
    }
}
