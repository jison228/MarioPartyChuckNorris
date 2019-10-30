package com.chucknorris.rewards.substractor.dolar

import com.chucknorris.player.Player
import com.chucknorris.player.PlayerBuilder
import com.chucknorris.rewards.substractor.PercentagePlayersCurrencySubstractor
import spock.lang.Specification
import spock.lang.Unroll

class DolarPercentagePlayersSubstractorSpec extends Specification {

    @Unroll
    void "test #testCase"() {
        given:
        Player milei = new PlayerBuilder()
                .setCharacter("Milei")
                .setPesos(1000)
                .setDolar(1000)
                .build()

        Player macri = new PlayerBuilder()
                .setCharacter("Macri")
                .setPesos(5000)
                .setDolar(5000)
                .build()

        Player cristina = new PlayerBuilder()
                .setCharacter("Cristina")
                .setPesos(25)
                .setDolar(25)
                .build()


        List<Player> playerList = [
                milei, macri, cristina, alberto
        ]

        PercentagePlayersCurrencySubstractor substractor = new DolarPercentagePlayersSubstractor(percentage)

        when:
        substractor.apply(alberto, playerList, null)

        then:
        milei.printWithDolar() == expectedMilei
        macri.printWithDolar() == expectedMacri
        cristina.printWithDolar() == expectedCristina
        alberto.printWithDolar() == expectedAlberto

        where:
        testCase                                                               | percentage | alberto                        || expectedAlberto  || expectedMilei  || expectedMacri  || expectedCristina
        "Albert tiene 0 y Resta 10% a todos los players. Alberto gana 602.5"   | 10         | getPlayerWithAlberto()         || "Alberto 602.5"  || "Milei 900.0"  || "Macri 4500.0" || "Cristina 22.5"
        "Albert tiene 0 y Resta 100% a todos los players. Alberto gana 6025"   | 100        | getPlayerWithAlberto()         || "Alberto 6025.0" || "Milei 0.0"    || "Macri 0.0"    || "Cristina 0.0"
        "Albert tiene 0 y Resta 0% a todos los players. Alberto gana 6025"     | 0          | getPlayerWithAlberto()         || "Alberto 0.0"    || "Milei 1000.0" || "Macri 5000.0" || "Cristina 25.0"

        "Albert tiene 100 y Resta 10% a todos los players. Alberto gana 602.5" | 10         | getPlayerWithAlberto(100, 100) || "Alberto 702.5"  || "Milei 900.0"  || "Macri 4500.0" || "Cristina 22.5"
        "Albert tiene 100 y Resta 100% a todos los players. Alberto gana 6025" | 100        | getPlayerWithAlberto(100, 100) || "Alberto 6125.0" || "Milei 0.0"    || "Macri 0.0"    || "Cristina 0.0"
        "Albert tiene 100 y Resta 0% a todos los players. Alberto gana 6025"   | 0          | getPlayerWithAlberto(100, 100) || "Alberto 100.0"  || "Milei 1000.0" || "Macri 5000.0" || "Cristina 25.0"
    }

    private static Player getPlayerWithAlberto() {
        return new PlayerBuilder()
                .setCharacter("Alberto")
                .build()
    }

    private static Player getPlayerWithAlberto(int pesos, int dolares) {
        return new PlayerBuilder()
                .setCharacter("Alberto")
                .setPesos(pesos)
                .setDolar(dolares)
                .build()
    }
}
