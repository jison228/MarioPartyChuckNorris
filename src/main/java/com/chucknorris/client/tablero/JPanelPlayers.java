package com.chucknorris.client.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.client.ClientPlayer;

public class JPanelPlayers extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ClientPlayer> clientPlayerList;

	public JPanelPlayers(List<ClientPlayer> clientPlayerList) {
		this.clientPlayerList = clientPlayerList;
	}

	public void paint(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/backgrounds/" + "panelPlayers2" + ".jpg"));
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

		for (int i =0; i<clientPlayerList.size();i++) {
			g.setColor(Color.ORANGE);
			g.fillRect(10, 110 * i - 5 + 90, 45, 45);

			try {
				image = ImageIO.read(new File("images/characters/" + clientPlayerList.get(i).getCharacter() + "Chico.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(image, 15, 110 * i + 90, this);

			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 18));
			g.drawString(clientPlayerList.get(i).getCharacter(), 10, 80 + i * 110);
			g.setColor(Color.lightGray);
			g.fillRect(70, 107 + i * 110, 65, 17);
			g.fillRect(170, 107 + i * 110, 75, 17);
			g.fillRect(70, 87 + i * 110, 130, 15);
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.drawString("Salario: $" + clientPlayerList.get(i).getSalario(), 70, 100 + i * 110);
			g.setColor(new Color(0, 255, 0));
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.drawString("$" + clientPlayerList.get(i).getPesos(), 70, 120 + i * 110);
			g.drawString("U$S" + clientPlayerList.get(i).getDolares(), 170, 120 + i * 110);

		}

	}
	
	public void updatePanelPlayers(List<ClientPlayer> clientPlayersList) {
		this.clientPlayerList = clientPlayersList;
	}
}
