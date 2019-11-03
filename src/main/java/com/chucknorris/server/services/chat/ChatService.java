package com.chucknorris.server.services.chat;

import java.net.Socket;
import java.util.Map;

public interface ChatService {
	void notifyClientConnected(Socket socket, Map data);

	void notifyClientDisconnected(Socket socket, Map data);

	void notifyNewMessage(String message, Map data);
}
