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

public class JPanelGrafico extends JPanel {

	private Map<Position, Node> mapa;
	private List<Player> listaP;
	private Circulo circuloDef;
	private int iniX = 50;
	private int iniY = 50;

	public JPanelGrafico(Map mapa, List<Player> listaP) {
		this.mapa = mapa;
		this.listaP = listaP;
	}

	public void paintComponent(Graphics g) {

		for (Map.Entry<Position, Node> entry : mapa.entrySet()) {

			if (entry.getValue().getType() == "RED") {
				g.setColor(Color.RED);
			} else if (entry.getValue().getType() == "YELLOW") {
				g.setColor(Color.YELLOW);
			} else if (entry.getValue().getType() == "WHITE") {
				g.setColor(Color.BLACK);
			} else
				g.setColor(Color.ORANGE);
			g.fillOval(iniX + entry.getValue().getPositionCoords().getX() * 200,
					iniY + entry.getValue().getPositionCoords().getY() * 100, 50, 50);

			g.setColor(Color.RED);

			Iterator<Node> recorrer = entry.getValue().nextNodes().iterator();
			while (recorrer.hasNext()) {
				Node node = recorrer.next();
				g.drawLine(iniX + iniX / 2 + entry.getValue().getPositionCoords().getX() * 200,
						iniY + iniY / 2 + entry.getValue().getPositionCoords().getY() * 100,
						iniX + iniX / 2 + node.getPositionCoords().getX() * 200,
						iniY + iniY / 2 + node.getPositionCoords().getY() * 100);
			}
		}

	}

}
