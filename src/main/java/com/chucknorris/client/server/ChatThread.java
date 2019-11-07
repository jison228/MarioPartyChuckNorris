package com.chucknorris.client.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chucknorris.client.GameInformation;
import com.chucknorris.client.tablero.MainGameScreen;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;
import com.chucknorris.server.command.dto.CommandDto;
import com.chucknorris.server.command.response.ChatResponse;
import com.google.gson.Gson;

public class ChatThread extends Thread {

	private Gson gson = new Gson();
	MainGameScreen frame;

	@Override
	public void run() {
		try {
			listenPortForChatMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listenPortForChatMessage() throws IOException {
		System.out.println("INtentando conectarme a localhost 30000");
		InetAddress address = InetAddress.getByName("localhost");

		Socket chatSocket = new Socket(address, 30000);

		InputStream inputStream = chatSocket.getInputStream();
		//OutputStream outputStream = chatSocket.getOutputStream();
		//OutputStreamWriter writer = new OutputStreamWriter(outputStream);
		//BufferedWriter bf = new BufferedWriter(writer);

		CommandDto commandDto = getCommandDto();

		String serializedCommandDto = gson.toJson(commandDto);

		PrintStream ps = new PrintStream(chatSocket.getOutputStream(), true);

		ps.println(serializedCommandDto);

		String serializedResponse;

		System.out.println("Me conecte a " + chatSocket);

		System.out.println("Listo para recibir mensajes" + chatSocket);
		BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream));
		while ((serializedResponse = responseReader.readLine()) != null) {
			ChatResponse chatResponse = gson.fromJson(serializedResponse, ChatResponse.class);

			System.out.println(chatResponse.messageToPrint);
		}

		responseReader.close();
	}

	private CommandDto getCommandDto() {
		Map<String, Object> commandData = new HashMap<>();
		commandData.put("player_id", "pepito");
		commandData.put("game_id", "test_game");

		CommandDto commandDto = new CommandDto();
		commandDto.command = "chat_message";
		commandDto.data = commandData;
		return commandDto;
	}
}
