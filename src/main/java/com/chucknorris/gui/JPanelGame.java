package com.chucknorris.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.player.Player;
import com.chucknorris.commons.Position;

public class JPanelGame extends JPanel {

	private Map<Position, Node> mapa;
	private List<Player> listaP;
	private Circulo circuloDef;
	private int iniX = 50;
	private int iniY = 50;

	public JPanelGame(Map mapa, List<Player> listaP) {
		this.mapa = mapa;
		this.listaP = listaP;
	}

	public void paint(Graphics g) {

		for (Map.Entry<Position, Node> entry : mapa.entrySet()) {
			Iterator<Node> recorrer = entry.getValue().nextNodes().iterator();
			while (recorrer.hasNext()) {
				g.setColor(Color.DARK_GRAY);
				Node node = recorrer.next();
				g.drawLine(iniX + iniX / 2 + entry.getValue().getPositionCoords().getX() * 200,
						iniY + iniY / 2 + entry.getValue().getPositionCoords().getY() * 100,
						iniX + iniX / 2 + node.getPositionCoords().getX() * 200,
						iniY + iniY / 2 + node.getPositionCoords().getY() * 100);
			}

		}
		for (Map.Entry<Position, Node> entry2 : mapa.entrySet()) {

			if (entry2.getValue().getType() == "RED") {
				g.setColor(Color.RED);
			} else if (entry2.getValue().getType() == "YELLOW") {
				g.setColor(Color.YELLOW);
			} else if (entry2.getValue().getType() == "WHITE") {
				g.setColor(Color.BLACK);
			} else
				g.setColor(Color.ORANGE);
			g.fillOval(iniX + entry2.getValue().getPositionCoords().getX() * 200,
					iniY + entry2.getValue().getPositionCoords().getY() * 100, 50, 50);

		}

	}

}
