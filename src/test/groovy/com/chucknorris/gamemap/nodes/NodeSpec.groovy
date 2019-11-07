package com.chucknorris.gamemap.nodes

import com.chucknorris.commons.Position
import com.chucknorris.player.Player
import com.chucknorris.player.PlayerBuilder
import spock.lang.Specification

import static org.junit.Assert.assertEquals

class NodeSpec extends Specification {

    void "test node rewards and type: #testCase"() {
        given:
        Player player = new PlayerBuilder().setCharacter("Macri").build()

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

    void "test nextNode within next: #testCase"() {
        given:
        Node redNode = new RedNode(null, new Position(1, 0))

        expect:
        node.hasThisPositionWithinNext(redNode) == expected
        where:
        testCase          | node                                                                     || expected
        "Node within"     | new RedNode([new RedNode(null, new Position(1, 0))], new Position(4, 4)) || true
        "Node not within" | new RedNode([new RedNode(null, new Position(1, 1))], new Position(4, 4)) || false
    }
}
