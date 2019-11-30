package com.chucknorris.singlePlayer.gui.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.client.ClientPlayer;
import com.chucknorris.player.Player;

public class InfoPanel extends JPanel {

	private Player currentPlayer;
	private int turn;
	private Double precioDolar;
	private BufferedImage image;
	private BufferedImage image2;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfoPanel(Player currentPlayer, int turn, Double precioDolar) {
		this.currentPlayer = currentPlayer;
		this.turn = turn;
		this.precioDolar = precioDolar;
	}

	public void paint(Graphics g) {
		try {
			image2 = ImageIO.read(new File("images/backgrounds/" + "infoPanel" + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image2, 0, 0, this);
		g.setColor(new Color(255, 128, 0));
		g.fillRect(10, 25, 130, 130);
		try {
			image = ImageIO.read(new File("images/characters/" + currentPlayer.getCharacter() + "Grande.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 15, 30, this);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString(currentPlayer.getCharacter(), 150, 45);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		String powerup = currentPlayer.getPowerupDescription();
		String[] frase = powerup.split("\n");
		for (int i = 0; i < frase.length; i++) {
			g.drawString(frase[i], 150, 30 * i + 75);
		}
		g.setColor(new Color(128,128,128,200));
		g.fillRect(500, 10, 200, 160);
		g.setColor(new Color(160,160,160,200));
		g.fillRect(525, 20, 150, 30);
		g.fillRect(525, 100, 150, 30);
		g.fillRect(580, 50, 40, 30);
		g.fillRect(565, 130, 70, 30);
		
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(new Color(0,0,255));
		g.drawString("Turno ", 565, 40);
		if(turn>8)
			g.drawString(String.valueOf(turn +1), 585, 73);
		else
			g.drawString(String.valueOf(turn +1), 592, 73);
		
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.GREEN);
		g.drawString("PrecioDolar", 533, 122);
		g.drawString("$" + precioDolar, 568, 155);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma",Font.BOLD,10));
		g.drawString("[Ctrl]: Ver informacion sobre los nodos", 780, 170);
	}

	public void actualizar(Player currentPlayer, int turn, Double precioDolar) {
		this.currentPlayer = currentPlayer;
		this.turn = turn;
		this.precioDolar = precioDolar;
	}
	
	public double getPrecioDolar() {
		return precioDolar;
	}
}
