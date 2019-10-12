package com.chucknorris.substractor

import com.chucknorris.player.Player
import com.chucknorris.substractor.pesos.PercentagePesosPlayersSubstractor
import com.chucknorris.substractor.pesos.PesosSubstractor
import spock.lang.Specification
import spock.lang.Unroll

class PercentagePesosPlayersSubstractorSpec extends Specification {

    @Unroll
    void "test substract 10% players pesos"() {
        given:
        Player milei = new Player("Milei", 1000.0)
        Player macri = new Player("Macri", 5000.0)
        Player cristina = new Player("Cristina", 25.0)

        List<Player> playerList = [
                milei, macri, cristina, alberto
        ]

        PesosSubstractor pesosSubstractor = new PercentagePesosPlayersSubstractor(percentage)

        when:
        pesosSubstractor.apply(alberto, playerList, null)

        then:
        milei.printWithPesos() == expectedMilei
        macri.printWithPesos() == expectedMacri
        cristina.printWithPesos() == expectedCristina
        alberto.printWithPesos() == expectedAlberto

        where:
        testCase                                                               | percentage | alberto                      || expectedAlberto  || expectedMilei  || expectedMacri  || expectedCristina
        "Albert tiene 0 y Resta 10% a todos los players. Alberto gana 602.5"   | 10         | new Player("Alberto", 0.0)   || "Alberto 602.5"  || "Milei 900.0"  || "Macri 4500.0" || "Cristina 22.5"
        "Albert tiene 0 y Resta 100% a todos los players. Alberto gana 6025"   | 100        | new Player("Alberto", 0.0)   || "Alberto 6025.0" || "Milei 0.0"    || "Macri 0.0"    || "Cristina 0.0"
        "Albert tiene 0 y Resta 0% a todos los players. Alberto gana 6025"     | 0          | new Player("Alberto", 0.0)   || "Alberto 0.0"    || "Milei 1000.0" || "Macri 5000.0" || "Cristina 25.0"

        "Albert tiene 100 y Resta 10% a todos los players. Alberto gana 602.5" | 10         | new Player("Alberto", 100.0) || "Alberto 702.5"  || "Milei 900.0"  || "Macri 4500.0" || "Cristina 22.5"
        "Albert tiene 100 y Resta 100% a todos los players. Alberto gana 6025" | 100        | new Player("Alberto", 100.0) || "Alberto 6125.0" || "Milei 0.0"    || "Macri 0.0"    || "Cristina 0.0"
        "Albert tiene 100 y Resta 0% a todos los players. Alberto gana 6025"   | 0          | new Player("Alberto", 100.0) || "Alberto 100.0"  || "Milei 1000.0" || "Macri 5000.0" || "Cristina 25.0"
    }
}
