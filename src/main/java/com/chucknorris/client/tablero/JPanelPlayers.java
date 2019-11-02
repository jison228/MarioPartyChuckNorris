package com.chucknorris.client.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.client.clientPlayer;

public class JPanelPlayers extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, clientPlayer> clientPlayerMap;

	public JPanelPlayers(Map<String, clientPlayer> clientPlayerMap) {
		this.clientPlayerMap = clientPlayerMap;
	}

	public void paint(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/backgrounds/" + "panelPlayers" + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, this);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 30));
		g.drawString("CANDIDATOS", 45, 30);
		g.setFont(new Font("Courier New", Font.BOLD, 10));
		g.drawString("(Gana el que llega a 500 dolares)", 30, 40);
		int i = 0;
		for (Map.Entry<String, clientPlayer> entry : clientPlayerMap.entrySet()) {
			g.setColor(Color.ORANGE);
			g.fillRect(10, 110 * i - 5 + 90, 45, 45);

			try {
				image = ImageIO.read(new File("images/characters/" + entry.getValue().getCharacter() + "Chico.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(image, 15, 110 * i + 90, this);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 18));
			g.drawString(entry.getValue().getCharacter(), 10, 80 + i * 110);
			g.setColor(Color.lightGray);
			g.fillRect(70, 107 + i * 110, 65, 17);
			g.fillRect(170, 107 + i * 110, 75, 17);
			g.fillRect(70, 87 + i * 110, 130, 15);
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.drawString("Salario: $" + entry.getValue().getSalario(), 70, 100 + i * 110);
			g.setColor(new Color(0, 255, 0));
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.drawString("$" + entry.getValue().getPesos(), 70, 120 + i * 110);
			g.drawString("U$S" + entry.getValue().getDolares(), 170, 120 + i * 110);
			i++;
		}

	}
}
