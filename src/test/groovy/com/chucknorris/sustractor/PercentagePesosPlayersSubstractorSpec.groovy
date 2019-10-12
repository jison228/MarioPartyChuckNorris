package com.chucknorris.sustractor

import com.chucknorris.player.Player
import com.chucknorris.sustractor.pesos.PercentagePesosPlayersSubstractor
import com.chucknorris.sustractor.pesos.PesosSubstractor
import spock.lang.Specification

class PercentagePesosPlayersSubstractorSpec extends Specification {

    void "test substract 10% players pesos"() {
        given:
        Player playerExecutor = new Player("Alberto", 0.0)

        List<Player> playerList = [
                new Player("Milei", 1000.0),
                new Player("Macri", 5000.0),
                new Player("Cristina", 25.0)
        ]

        PesosSubstractor pesosSubstractor = new PercentagePesosPlayersSubstractor(percentage)

        when:
        pesosSubstractor.apply(playerExecutor, playerList, null)

        then:
        playerExecutor.printWithPesos() == expected

        where:
        testCase                                             | percentage || expected
        "Resta 10% a todos los players y Alberto gana 602.5" | 10         || "Alberto 602.5"
        "Resta 100% a todos los players y Alberto gana 6025" | 100        || "Alberto 6025.0"
        "Resta 0% a todos los players y Alberto gana 6025"   | 0          || "Alberto 0.0"
    }
}
