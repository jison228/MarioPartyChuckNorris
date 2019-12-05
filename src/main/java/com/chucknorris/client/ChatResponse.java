package com.chucknorris.client;

public class ChatResponse {
	public String playerID;
	public String mensaje;
	
	public ChatResponse(String playerID, String mensaje) {
		this.playerID = playerID;
		this.mensaje = mensaje;
	}
	
}
