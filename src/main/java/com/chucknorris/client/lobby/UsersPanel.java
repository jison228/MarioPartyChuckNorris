package com.chucknorris.client.lobby;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.chucknorris.User;

public class UsersPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> usuarios;

	public UsersPanel(List<User> usuarios) {
		this.usuarios = usuarios;
	}

	public void paint(Graphics g) {
		for (int i = 0; i < usuarios.size(); i++) {

			int separacion = i * 130;
			g.setColor(Color.pink);
			g.fillRect(10, 10 + separacion, 450, 120);

			g.setColor(Color.RED);
			g.fillRect(20, 20 + separacion, 100, 100);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 25));
			g.drawString(usuarios.get(i).getPlayerID(), 132, 43 + separacion);

			g.setFont(new Font("Courier", Font.BOLD, 18));
			g.drawString("Partidas ganadas: " + usuarios.get(i).getPartidasGanadas(), 136, 80 + separacion);
			g.drawString("Maximo Puntaje: " + usuarios.get(i).getMaximoPuntaje(), 136, 110 + separacion);
		}

	}

	public final void updatePanel(List<User> usuarios) {
		this.usuarios = usuarios;
	}
}
