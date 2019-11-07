package com.chucknorris.server.command.game

import com.chucknorris.commons.Position
import com.chucknorris.server.command.Command
import com.chucknorris.server.command.dto.CommandData
import com.chucknorris.server.command.dto.CommandDto
import com.chucknorris.server.command.response.ServerResponse
import com.chucknorris.server.services.game.GameService
import com.chucknorris.server.services.game.GameServiceImpl
import spock.lang.Specification

class ResolveIntersectionCommandSpec extends Specification {

    void "test invalid parameters, expected BadRequestResponse"() {
        given:
        Command resolveIntersectionCommand = new ResolveIntersectionCommand()

        when:
        ServerResponse response = resolveIntersectionCommand.execute(commandData)

        then:
        response.status == 400

        where:
        commandData                                  | _
        getCommandData(null, null, null)             | _
        getCommandData("game_id", null, null)        | _
        getCommandData("game_id", "player_id", null) | _
    }

    void "test resolveIntersection from game service called when params are valid"() {
        given:
        GameService gameService = Mock(GameServiceImpl)

        Command resolveIntersectionCommand = new ResolveIntersectionCommand(gameService)

        when:
        resolveIntersectionCommand.execute(commandData)

        then:
        1 * gameService.resolveIntersection("game_id", "player_id", new Position(1, 3))

        where:
        commandData                                          | _
        getCommandData("game_id", "player_id", [x: 1, y: 3]) | _
    }

    CommandData getCommandData(String gameId, String playerId, Map positionChosen) {
        return new CommandData(
                new CommandDto(
                        data: [
                                game_id        : gameId,
                                player_id      : playerId,
                                position_chosen: positionChosen
                        ]
                )
        )
    }
}
