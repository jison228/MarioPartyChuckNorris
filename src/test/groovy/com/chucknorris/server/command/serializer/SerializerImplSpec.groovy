package com.chucknorris.server.command.serializer


import com.chucknorris.game.GameResponse
import com.chucknorris.server.command.response.ChatResponse
import spock.lang.Specification
import spock.lang.Unroll

class SerializerImplSpec extends Specification {

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
        "GameResponse" | getGameResponse("game id 3")   || getExpectedGameResponse()
        "ChatResponse" | getChatResponse("Saracatunga") || getExpectedChatResponse()
    }

    @Unroll
    void "test deserialize object: #testCase"() {
        given:
        Serializer serializer = new SerializerImpl<>()

        when:
        Object serializedObject = serializer.serialize(jsonObject, clazz)

        then:
        serializedObject.class == clazz

        where:
        testCase       | jsonObject              || clazz
        "GameResponse" | getGameResponseAsJson() || GameResponse.class
        "ChatResponse" | getChatResponseAsJson() || ChatResponse.class
    }

    String getChatResponseAsJson() {
        return "{\"messageToPrint\":\"Saracatunga\",\"status\":200}"
    }

    String getGameResponseAsJson() {
        return "{\"movementsLeft\":0,\"diceResult\":0,\"gameId\":\"game id 3\",\"status\":200}"
    }

    private static String getExpectedGameResponse() {
        return "{\"movementsLeft\":0,\"diceResult\":0,\"gameId\":\"game id 3\",\"status\":200}"
    }

    private static GameResponse getGameResponse(String gameId) {
        return new GameResponse(gameId: gameId)
    }

    private static String getExpectedChatResponse() {
        return "{\"messageToPrint\":\"Saracatunga\",\"status\":200}"
    }

    private static ChatResponse getChatResponse(String messageToPrint) {
        return new ChatResponse(messageToPrint: messageToPrint)
    }
}
