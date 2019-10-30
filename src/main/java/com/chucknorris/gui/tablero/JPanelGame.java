package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;

public class JPanelGame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Position, Node> mapa;
	private List<Player> listaP;
	private int iniX = 30;
	private int iniY = 30;

	public JPanelGame(Map<Position, Node> mapa, List<Player> listaP) {
		this.mapa = mapa;
		this.listaP = listaP;
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

		for (Map.Entry<Position, Node> entry : mapa.entrySet()) {

			try {
				image = ImageIO.read(new File("images/nodes/" + entry.getValue().getType() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.fillOval(iniX + entry.getValue().getPositionCoords().getX() * 125 - 5,
					iniY + entry.getValue().getPositionCoords().getY() * 125 - 5, 85, 85);
			g.drawImage(image, iniX + entry.getValue().getPositionCoords().getX() * 125,
					iniY + entry.getValue().getPositionCoords().getY() * 125, this);
		}

		for (int i = 0; i < listaP.size(); i++) {
			try {
				image = ImageIO.read(new File("images/characters/" + listaP.get(i).getCharacter() + "Chico.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(image, iniX + listaP.get(i).getNodeLocation().getPositionCoords().getX() * 125 + (i % 2) * 40,
					iniY + listaP.get(i).getNodeLocation().getPositionCoords().getY() * 125 + (i / 2) * 40, this);
		}
	}

}
