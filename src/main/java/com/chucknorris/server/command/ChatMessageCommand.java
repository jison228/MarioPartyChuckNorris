package com.chucknorris.server.command;

import com.chucknorris.player.Player;
import com.chucknorris.server.command.dto.CommandData;
import com.chucknorris.server.command.response.ChatResponse;
import com.chucknorris.server.command.response.ServerResponse;
import com.chucknorris.server.services.chat.ChatService;
import com.chucknorris.server.services.chat.ChatServiceImpl;
import com.chucknorris.server.services.game.GameService;
import com.chucknorris.server.services.game.GameServiceImpl;

public class ChatMessageCommand extends Command<ChatResponse> {
	private ChatService service = new ChatServiceImpl();
	private GameService gameService = new GameServiceImpl();

	@Override
	protected ServerResponse execute(CommandData commandData) {
		String message;

		notifyClientConnected(commandData);

		while ((message = readLine()) != null && !message.equals("close")) {
			service.notifyNewMessage(message, commandData.getData());
		}

		notifyClientDisconnected(commandData);

		return clientCloseConnectionChatResponse();
	}

	private void notifyClientDisconnected(CommandData commandData) {
		String gameId = (String) commandData.getData().get("game_id");
		String playerId = (String) commandData.getData().get("player_id");
		Player player = gameService.getPlayer(gameId, playerId);

		String message = String.format("Player %s has disconnected from chat_message", player.printPlayerName());

		LOGGER.info(message);

		service.notifyClientDisconnected(commandData.getSocket(), commandData.getData());
	}

	private void notifyClientConnected(CommandData commandData) {
		String gameId = (String) commandData.getData().get("game_id");
		String playerId = (String) commandData.getData().get("player_id");
		Player player = gameService.getPlayer(gameId, playerId);

		String message = String.format("Player %s has initiallized chat_message command", player.printPlayerName());

		LOGGER.info(message);

		service.notifyClientConnected(commandData.getSocket(), commandData.getData());
	}

	private ServerResponse clientCloseConnectionChatResponse() {
		ChatResponse response = new ChatResponse();

		response.messageToPrint = String.format("%s - Player %s has disconnected", getNow(), "PlayerDummy");

		return response;
	}
}
