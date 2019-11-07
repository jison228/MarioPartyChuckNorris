package com.chucknorris.game

import com.chucknorris.player.Player
import com.chucknorris.player.PlayerBuilder
import spock.lang.Specification

class TurnSelectorSpec extends Specification {
    private Player player1 = buildPlayer("1")
    private Player player2 = buildPlayer("2")
    private Player player3 = buildPlayer("3")
    private Player player4 = buildPlayer("4")

    void "test getTurn in the order expected"() {
        setup:
        TurnSelector turnSelector = new TurnSelector([player1, player2, player3, player4])

        expect:
        turnSelector.getTurn() == player1
        turnSelector.getTurn() == player2
        turnSelector.getTurn() == player3
        turnSelector.getTurn() == player4

        turnSelector.getTurn() == player1
        turnSelector.getTurn() == player2
        turnSelector.getTurn() == player3
        turnSelector.getTurn() == player4
    }

    void "test validate player turn "() {
        setup:
        TurnSelector turnSelector = new TurnSelector([player1, player2, player3, player4])

        expect:
        turnSelector.getTurn() == player1
        turnSelector.getTurn() == player2
        turnSelector.getTurn() == player3
        turnSelector.getTurn() == player4

        turnSelector.isPlayerTurn(player1)

        !turnSelector.isPlayerTurn(player2)
        !turnSelector.isPlayerTurn(player3)
        !turnSelector.isPlayerTurn(player4)
    }

    Player buildPlayer(String id) {
        return new PlayerBuilder().setId(id).build()
    }
}
