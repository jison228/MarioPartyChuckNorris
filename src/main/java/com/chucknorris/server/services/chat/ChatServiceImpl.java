package com.chucknorris.server.services.chat;

import com.chucknorris.player.Player;
import com.chucknorris.server.services.game.GameService;
import com.chucknorris.server.services.game.GameServiceImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatServiceImpl implements ChatService {
	private static final Map<String, Set<Socket>> clientsConnected = new HashMap<>();
	private GameService gameService = new GameServiceImpl();

	@Override
	public void notifyClientConnected(Socket socket, Map data) {
		String gameId = getGameId(data);

		Set<Socket> clients = clientsConnected.computeIfAbsent(gameId, clientsSet -> new HashSet<>());

		clients.add(socket);
	}

	@Override
	public void notifyClientDisconnected(Socket socket, Map data) {
		String gameId = getGameId(data);

		Set<Socket> clients = clientsConnected.get(gameId);

		clients.remove(socket);
	}

	@Override
	public void notifyNewMessage(String message, Map data) {
		String gameId = getGameId(data);

		Set<Socket> clients = clientsConnected.getOrDefault(gameId, null);

		if (clients != null) {
			clients.forEach(socket -> notifyMessage(socket, data, message));
		}
	}

	private void notifyMessage(Socket socket, Map data, String message) {
		String playerId = (String) data.get("player_id");
		String gameId = getGameId(data);

		Player player = gameService.getPlayer(gameId, playerId);

		String messageToPrint = String.format("%s - %s: %s", getTime(), player.printPlayerName(), message);

		sendMessage(socket, messageToPrint);
	}

	private void sendMessage(Socket socket, String messageToPrint) {
		try {
			OutputStream outputStream = socket.getOutputStream();

			outputStream.write(messageToPrint.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private LocalDateTime getTime() {
		return OffsetDateTime.now().toLocalDateTime();
	}

	private String getGameId(Map data) {
		return (String) data.get("game_id");
	}
}
