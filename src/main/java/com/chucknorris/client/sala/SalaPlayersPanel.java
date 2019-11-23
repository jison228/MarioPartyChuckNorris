package com.chucknorris.client.sala;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.User;

public class SalaPlayersPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> players;

	public SalaPlayersPanel(List<User> players) {
		this.players = players;
	}

	public void paint(Graphics g) {
		// TODO Agregar Imagen de fondo
		Graphics2D g2d = (Graphics2D) g;
		Stroke stroke1 = new BasicStroke(6f);
		g2d.setStroke(stroke1);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic", Font.BOLD, 35));
		g.drawString("JUGADORES", 113, 50);

		BufferedImage imagen;
		try {
			imagen = ImageIO.read(new File("images/characters/EspertLobby.jpg"));
			g.drawImage(imagen, 343, 80, this);
			imagen = ImageIO.read(new File("images/characters/CristinaLobby.jpg"));
			g.drawImage(imagen, 343, 190, this);
			imagen = ImageIO.read(new File("images/characters/MacriLobby.jpg"));
			g.drawImage(imagen, 343, 300, this);
			imagen = ImageIO.read(new File("images/characters/Del CañoLobby.jpg"));
			g.drawImage(imagen, 343, 410, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < 4; i++) {
			g2d.setColor(Color.orange);
			g2d.drawRect(0, 77 + i * 110, 326, 96);
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Tahoma", Font.BOLD, 20));
			g2d.drawString("Esperando jugador...", 63, 130 + i * 110);
		}

		for (int i = 0; i < players.size(); i++) {

			int separacion = i * 110;
			g.setColor(Color.pink);
			g.fillRect(3, 80 + separacion, 320, 90);

			g.setColor(Color.RED);
			g.fillRect(13, 90 + separacion, 70, 70);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(players.get(i).getPlayerID(), 98, 110 + separacion);

			g.setFont(new Font("Courier", Font.BOLD, 13));
			g.drawString("Partidas ganadas: " + players.get(i).getPartidasGanadas(), 99, 130 + separacion);
			g.drawString("Maximo Puntaje: " + players.get(i).getMaximoPuntaje(), 99, 150 + separacion);
		}

	}

	public final void updatePanel(List<User> players) {
		this.players = players;
	}
}
