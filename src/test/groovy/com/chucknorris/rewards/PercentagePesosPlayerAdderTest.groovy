package com.chucknorris.rewards

import com.chucknorris.player.Player
import com.chucknorris.player.PlayerBuilder
import spock.lang.Specification

class PercentagePesosPlayerAdderTest extends Specification {

    void "test percentage pesos addition to single and a list of players, expected only alberto to be affected"() {
        given:
        Player alberto = new PlayerBuilder()
                .setCharacter("Alberto")
                .setPesos(1000.0)
                .build()

        Player milei = new PlayerBuilder()
                .setCharacter("Milei")
                .setPesos(1000.0)
                .build()

        Player macri = new PlayerBuilder()
                .setCharacter("Macri")
                .setPesos(5000.0)
                .build()

        Player cristina = new PlayerBuilder()
                .setCharacter("Cristina")
                .setPesos(25.0)
                .build()


        List<Player> playerList = [
                milei, macri, cristina, alberto
        ]

        when:
        RewardApplicable fixedPesosAdder = new PercentagePesosPlayerAdder(10)
        fixedPesosAdder.apply(alberto, playerList, null)

        then:
        alberto.printWithPesos() == "Alberto 1100.0"
        milei.printWithPesos() == "Milei 1000.0"
        macri.printWithPesos() == "Macri 5000.0"
        cristina.printWithPesos() == "Cristina 25.0"
    }
}
