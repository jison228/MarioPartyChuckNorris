package com.chucknorris.client.tablero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.client.ClientNode;
import com.chucknorris.client.ClientPlayer;

public class JPanelGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ClientNode> nodesList;
	private List<ClientPlayer> clientPlayerList;
	private int iniX = 30;
	private int iniY = 30;

	public JPanelGame(List<ClientNode> nodesList, List<ClientPlayer> clientPlayerList) {
		this.nodesList = nodesList;
		this.clientPlayerList = clientPlayerList;
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

		for (int i=0;i<nodesList.size();i++) {

			try {
				image = ImageIO.read(new File("images/nodes/" + nodesList.get(i).getType() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.fillOval(iniX + nodesList.get(i).getPosition().getX() * 125 - 5,
					iniY + nodesList.get(i).getPosition().getY() * 125 - 5, 85, 85);
			g.drawImage(image, iniX + nodesList.get(i).getPosition().getX() * 125,
					iniY + nodesList.get(i).getPosition().getY() * 125, this);
		}

		for (int i=0;i<clientPlayerList.size();i++) {
			try {
				image = ImageIO.read(new File("images/characters/" + clientPlayerList.get(i).getCharacter() + "Chico.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(image, iniX + clientPlayerList.get(i).getPosition().getX() * 125 + (i % 2) * 40,
					iniY + clientPlayerList.get(i).getPosition().getY() * 125 + (i / 2) * 40, this);

		}
	}

}
