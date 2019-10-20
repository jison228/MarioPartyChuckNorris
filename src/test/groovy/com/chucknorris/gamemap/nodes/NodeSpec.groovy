package com.chucknorris.gamemap.nodes

import com.chucknorris.commons.Position
import com.chucknorris.player.Player
import spock.lang.Specification

import static org.junit.Assert.assertEquals

class NodeSpec extends Specification {

    void "test node rewards and type: #testCase"() {
        given:
        Player player = new Player("Mauri", 0)

        when:
        node.applyReward(player, null, null)

        then:
        assertEquals(expectedCoins, player.getCoins())
        assertEquals(expectedType, node.getType())

        where:
        testCase           | node                                     || expectedCoins || expectedType
        "Red node case"    | new RedNode(null, new Position(4, 4))    || 3             || "RED"
        "Yellow node case" | new YellowNode(null, new Position(4, 4)) || 10            || "YELLOW"
        "White node case"  | new WhiteNode(null, new Position(4, 4))  || 0             || "WHITE"
        "End node case"    | new EndNode(null, new Position(4, 4))    || 0             || "END"
    }
}
