package com.chucknorris.client;

import java.util.List;

public class ServerResponse2 {
	public boolean minigame;
	public List<clientPlayer> currentClientPlayerList;
	
	public ServerResponse2(boolean minigame, List<clientPlayer> currentClientPlayerList) {
		this.minigame = minigame;
		this.currentClientPlayerList = currentClientPlayerList;
	}
	
}
