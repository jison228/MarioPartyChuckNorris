package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.player.Player;

public class InfoPanel extends JPanel {

	private Player currentPlayer;
	private int turn;
	private Double precioDolar;
	private BufferedImage image;
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
			image = ImageIO.read(new File("images/characters/" + currentPlayer.getCharacter() + "Grande.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 300, 0, this);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString(currentPlayer.getCharacter(), 430, 25);
		g.drawString("Turno " + (turn + 1), 725, 25);
		g.setFont(new Font("Arial", Font.PLAIN, 18));
		String powerup = currentPlayer.getPowerupDescription();
		String[] frase = powerup.split("\n");
		for (int i = 0; i < frase.length; i++) {
			g.drawString(frase[i], 430 , 30 * i + 50);
		}
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.RED);
		g.drawString("PrecioDolar: $" + precioDolar, 25 , 75);
	}

	public void actualizar(Player currentPlayer, int turn) {
		this.currentPlayer = currentPlayer;
		this.turn = turn;
	}
}
