package com.chucknorris.gui;

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
		for(int i=0;i<listaP.size();i++) {
			
		}
		
	}
}
