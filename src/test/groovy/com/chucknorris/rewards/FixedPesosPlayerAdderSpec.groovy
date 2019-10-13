package com.chucknorris.rewards

import com.chucknorris.player.Player
import spock.lang.Specification

class FixedPesosPlayerAdderSpec extends Specification {

    void "test fixed pesos addition to single and a list of players, expected only alberto to be affected"() {
        given:
        Player alberto = new Player("Alberto", 30.0)
        Player milei = new Player("Milei", 1000.0)
        Player macri = new Player("Macri", 5000.0)
        Player cristina = new Player("Cristina", 25.0)

        List<Player> playerList = [
                milei, macri, cristina, alberto
        ]

        when:
        RewardApplicable fixedPesosAdder = new FixedPesosPlayerAdder(30)
        fixedPesosAdder.apply(alberto, playerList, null)

        then:
        alberto.printWithPesos() == "Alberto 60.0"
        milei.printWithPesos() == "Milei 1000.0"
        macri.printWithPesos() == "Macri 5000.0"
        cristina.printWithPesos() == "Cristina 25.0"
    }

}
