package com.chucknorris.gui.tablero;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.chucknorris.player.Player;

public class InfoPanel extends JPanel{

	Player currentPlayer;
	/**
	 * 
	 */
	
	public InfoPanel(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	private static final long serialVersionUID = 1L;
	public void paint(Graphics g) {
		
	}
}
