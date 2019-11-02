package com.chucknorris.client.tablero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.client.clientNode;
import com.chucknorris.client.clientPlayer;
import com.chucknorris.commons.Position;

public class JPanelGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Position, clientNode> mapaMap;
	private Map<String, clientPlayer> clientPlayerMap;
	private int iniX = 50;
	private int iniY = 30;

	public JPanelGame(Map<Position, clientNode> mapaMap, Map<String, clientPlayer> clientPlayerMap) {
		this.mapaMap = mapaMap;
		this.clientPlayerMap = clientPlayerMap;
	}

	@Override
	public void paintComponent(Graphics g) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/backgrounds/" + "newMap1" + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, this);

		g.setColor(Color.WHITE);

		for (Map.Entry<Position, clientNode> entry : mapaMap.entrySet()) {

			try {
				image = ImageIO.read(new File("images/nodes/" + entry.getValue().getType() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.fillOval(iniX + entry.getValue().getPosition().getX() * 125 - 5,
					iniY + entry.getValue().getPosition().getY() * 125 - 5, 85, 85);
			g.drawImage(image, iniX + entry.getValue().getPosition().getX() * 125,
					iniY + entry.getValue().getPosition().getY() * 125, this);
		}
		int i = 0;
		for (Map.Entry<String, clientPlayer> entry2 : clientPlayerMap.entrySet()) {
			try {
				image = ImageIO.read(new File("images/characters/" + entry2.getValue().getCharacter() + "Chico.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(image, iniX + entry2.getValue().getPosition().getX() * 125 + (i % 2) * 40,
					iniY + entry2.getValue().getPosition().getY() * 125 + (i / 2) * 40, this);
			i++;
		}
	}

}
