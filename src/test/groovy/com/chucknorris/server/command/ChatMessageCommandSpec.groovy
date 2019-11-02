package com.chucknorris.server.command


import com.chucknorris.player.PlayerBuilder
import com.chucknorris.server.command.dto.CommandData
import com.chucknorris.server.command.dto.CommandDto
import com.chucknorris.server.services.chat.ChatService
import com.chucknorris.server.services.chat.ChatServiceImpl
import com.chucknorris.server.services.game.GameService
import com.chucknorris.server.services.game.GameServiceImpl
import spock.lang.Specification

class ChatMessageCommandSpec extends Specification {
    private GameService gameService = Mock(GameServiceImpl)
    private ChatService chatService = Mock(ChatServiceImpl)

    void setup() {
        gameService.getPlayer(_, _) >> new PlayerBuilder().setCharacter("Cristina").build()
    }

    void "test message chat service called once cause second message is null"() {
        given:
        ChatMessageCommand command = Spy(new ChatMessageCommand(chatService, gameService))

        command.readLine() >>> ["Hola mundo", null]

        CommandData commandData = new CommandData(
                new CommandDto(
                        command: "chat_message",
                        data: [
                                "player_id": "pepito",
                                "game_id"  : "test_game"
                        ]
                )
        )

        when:
        command.execute(commandData)

        then:
        1 * chatService.notifyNewMessage("Hola mundo", commandData.getData())
    }

    void "test chat services dont called when first message is close"() {
        given:
        ChatMessageCommand command = Spy(new ChatMessageCommand(chatService, gameService))

        command.readLine() >> "close"

        CommandData commandData = new CommandData(
                new CommandDto(
                        command: "chat_message",
                        data: [
                                "player_id": "pepito",
                                "game_id"  : "test_game"
                        ]
                )
        )

        when:
        command.execute(commandData)

        then:
        0 * chatService.notifyNewMessage(_, _)
    }

    void "test message chat service called once cause seconde message is close"() {
        given:
        ChatMessageCommand command = Spy(new ChatMessageCommand(chatService, gameService))

        command.readLine() >>> ["Hola mundo", "close"]

        CommandData commandData = new CommandData(
                new CommandDto(
                        command: "chat_message",
                        data: [
                                "player_id": "pepito",
                                "game_id"  : "test_game"
                        ]
                )
        )

        when:
        command.execute(commandData)

        then:
        1 * chatService.notifyNewMessage("Hola mundo", commandData.getData())
    }
}
