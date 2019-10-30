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
	private static final String FINISH_CHAT = "close";
	private final ChatService chatService;
	private final GameService gameService;

	public ChatMessageCommand() {
		chatService = new ChatServiceImpl();
		gameService = new GameServiceImpl();
	}

	public ChatMessageCommand(ChatService chatService, GameService gameService) {
		this.chatService = chatService;
		this.gameService = gameService;
	}

	@Override
	protected ServerResponse execute(CommandData commandData) {
		String message;

		notifyClientConnected(commandData);

		while ((message = readLine()) != null && !message.equals(FINISH_CHAT)) {
			chatService.notifyNewMessage(message, commandData.getData());
		}

		notifyClientDisconnected(commandData);

		return clientCloseConnectionChatResponse();
	}

	private void notifyClientDisconnected(CommandData commandData) {
		Player player = getPlayer(commandData);

		String message = String.format("Player %s has disconnected from chat_message", player.printPlayerName());

		LOGGER.info(message);

		chatService.notifyClientDisconnected(commandData.getSocket(), commandData.getData());
	}

	private Player getPlayer(CommandData commandData) {
		String gameId = (String) commandData.getData().get("game_id");
		String playerId = (String) commandData.getData().get("player_id");
		return gameService.getPlayer(gameId, playerId);
	}

	private void notifyClientConnected(CommandData commandData) {
		Player player = getPlayer(commandData);

		String message = String.format("Player %s has initiallized chat_message command", player.printPlayerName());

		LOGGER.info(message);

		chatService.notifyClientConnected(commandData.getSocket(), commandData.getData());
	}

	private ServerResponse clientCloseConnectionChatResponse() {
		ChatResponse response = new ChatResponse();

		response.messageToPrint = String.format("%s - Player %s has disconnected", getNow(), "PlayerDummy");

		return response;
	}
}
