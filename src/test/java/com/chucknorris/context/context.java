package com.chucknorris.context;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.chucknorris.game.GameContext;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.player.Player;

public class context {



	Player p1, p2;
	ArrayList<Player> playerList;
	GameContext gamecontext;
	@Before
	public void executedBeforeEach() throws Exception {
		p1 = new Player("Javier Milei", 0);
		p1.givePesos(100);
		p1.giveUSD(5);
		p2 = new Player("MauriCEOMcree", 0);
		p2.givePesos(100);
		p2.giveUSD(5);

		gamecontext =new GameContext(2, 2, 10);
	}
	
	@Test
	public void buyUSDplayer() {
	List<Player> playerList = Arrays.asList(p1, p2);
	gamecontext.buyUSD(p1, 10, playerList);
    assertEquals(10.0, p1.getUSD(),0);
	}
	
	@Test
	public void sellUSDplayer() {
	List<Player> playerList = Arrays.asList(p1, p2);
	gamecontext.sellUSD(p1, 1, playerList);
    assertEquals(102.0, p1.getPesos(),0);
    
    
	}
	

}
