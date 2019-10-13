package com.chucknorris.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.chucknorris.player.Player;

public class JPanelPlayers extends JPanel {
	private List<Player> listaP;
	
	public JPanelPlayers(List<Player> listaP) {
		this.listaP = listaP;
	}

	public void paint(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, 280, 450);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma",Font.BOLD,24));
		g.drawString("PLAYERS", 100, 30);
		for(int i=0;i<listaP.size();i++) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 18));
			g.drawString(listaP.get(i).getCharacter(), 50, 75 + i*100);
			g.setColor(Color.green);
			g.setFont(new Font("Tahoma",Font.BOLD,15));
			g.drawString("$" + listaP.get(i).getPesos(), 50, 120 + i*100);
			g.drawString("U$S" + listaP.get(i).getDolares(), 150, 120 + i*100);
		}
		
	}
}
