package com.chucknorris.server.command.serializer


import com.chucknorris.game.GameResponse
import com.chucknorris.server.command.response.ChatResponse
import spock.lang.Specification
import spock.lang.Unroll

class SerializerImplTest extends Specification {

    @Unroll
    void "test serialize object: #testCase"() {
        given:
        Serializer serializer = new SerializerImpl<>()

        when:
        String serializedObject = serializer.serialize(object)

        then:
        serializedObject == expected

        where:
        testCase       | object                         || expected
        "GameResponse" | getGameResponse("game id 3")   || getExpectedGameResponse("game id 3")
        "ChatResponse" | getChatResponse("Saracatunga") || getExpectedChatResponse("Saracatunga")
    }

    private static String getExpectedGameResponse(String gameId) {
        return "{\"movementsLeft\":0,\"diceResult\":0,\"gameId\":\"$gameId\"}"
    }

    private static GameResponse getGameResponse(String gameId) {
        return new GameResponse(gameId: gameId)
    }

    private static String getExpectedChatResponse(String messageToPrint) {
        return "{\"messageToPrint\":\"$messageToPrint\"}"
    }

    private static ChatResponse getChatResponse(String messageToPrint) {
        return new ChatResponse(messageToPrint: messageToPrint)
    }
}
