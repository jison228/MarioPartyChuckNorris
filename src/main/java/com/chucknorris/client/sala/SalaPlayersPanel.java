package com.chucknorris.client.sala;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

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
		g.setFont(new Font("Century Gothic", Font.BOLD, 30));
		g.drawString("Jugadores", 180, 50);

		g2d.setColor(Color.orange);
		for (int i = 0; i < 4; i++) {
			g2d.drawRect(37, 77 + i * 110, 326, 96);
		}

		for (int i = 0; i < players.size(); i++) {

			int separacion = i * 110;
			g.setColor(Color.pink);
			g.fillRect(40, 80 + separacion, 320, 90);

			g.setColor(Color.RED);
			g.fillRect(50, 90 + separacion, 70, 70);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(players.get(i).getPlayerID(), 135, 110 + separacion);

			g.setFont(new Font("Courier", Font.BOLD, 13));
			g.drawString("Partidas ganadas: " + players.get(i).getPartidasGanadas(), 136, 130 + separacion);
			g.drawString("Maximo Puntaje: " + players.get(i).getMaximoPuntaje(), 136, 150 + separacion);
		}

	}

	public final void updatePanel(List<User> usuarios) {
		this.players = usuarios;
	}
}
