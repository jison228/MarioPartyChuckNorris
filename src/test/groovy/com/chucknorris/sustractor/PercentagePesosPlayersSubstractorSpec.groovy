package com.chucknorris.sustractor

import com.chucknorris.player.Player
import com.chucknorris.sustractor.pesos.PercentagePesosPlayersSubstractor
import com.chucknorris.sustractor.pesos.PesosSubstractor
import spock.lang.Specification
import spock.lang.Unroll

class PercentagePesosPlayersSubstractorSpec extends Specification {

    @Unroll
    void "test substract 10% players pesos"() {
        given:
        Player playerExecutor = new Player("Alberto", 0.0)
        Player milei = new Player("Milei", 1000.0)
        Player macri = new Player("Macri", 5000.0)
        Player cristina = new Player("Cristina", 25.0)

        List<Player> playerList = [
                milei, macri, cristina
        ]

        PesosSubstractor pesosSubstractor = new PercentagePesosPlayersSubstractor(percentage)

        when:
        pesosSubstractor.apply(playerExecutor, playerList, null)

        then:
        milei.printWithPesos() == expectedMilei
        macri.printWithPesos() == expectedMacri
        cristina.printWithPesos() == expectedCristina
        playerExecutor.printWithPesos() == expectedAlberto

        where:
        testCase                                             | percentage || expectedAlberto  || expectedMilei  || expectedMacri  || expectedCristina
        "Resta 10% a todos los players y Alberto gana 602.5" | 10         || "Alberto 602.5"  || "Milei 900.0"  || "Macri 4500.0" || "Cristina 22.5"
        "Resta 100% a todos los players y Alberto gana 6025" | 100        || "Alberto 6025.0" || "Milei 0.0"    || "Macri 0.0"    || "Cristina 0.0"
        "Resta 0% a todos los players y Alberto gana 6025"   | 0          || "Alberto 0.0"    || "Milei 1000.0" || "Macri 5000.0" || "Cristina 25.0"
    }
}
