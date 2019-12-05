package com.chucknorris.client.lobby;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
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
			
			BufferedImage image = null;
			
			try {
				image = ImageIO.read(new File("images/users/" + usuarios.get(i).getPlayerID() + ".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int separacion = i < 6? i * 100 : (i - 6) * 100;
			g.setColor(Color.pink);
			g.fillRect(5 + (i/6) * 255, 10 + separacion, 250, 90);

			g.setColor(Color.RED);
			g.drawImage(image, 15 + (i/6) * 255 , 20 + separacion, this);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(usuarios.get(i).getPlayerID(), 90 + (i/6) * 255 , 43 + separacion);

			g.setFont(new Font("Courier", Font.BOLD, 13));
			//g.drawString("Partidas ganadas: " + usuarios.get(i).getPartidasGanadas(), 92 + (i/6) * 255 , 60 + separacion);
			//g.drawString("Maximo Puntaje: " + usuarios.get(i).getMaximoPuntaje(), 92 + (i/6) * 255 , 80 + separacion);
		}

	}

	public final void updatePanel(List<User> usuarios) {
		this.usuarios = usuarios;
	}
}
