package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Font;
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
	private int turn;
	private Color[] coloresP = { Color.CYAN, Color.BLUE, Color.GREEN, Color.ORANGE };
	private int iniX = 50;
	private int iniY = 30;

	public JPanelGame(Map mapa, List<Player> listaP, int turn) {
		this.mapa = mapa;
		this.listaP = listaP;
		this.turn = turn;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma",Font.BOLD,24));
		g.drawString("TURNO" + " " + (turn +1), 800, 50);
		
		for (Map.Entry<Position, Node> entry : mapa.entrySet()) {
			Iterator<Node> recorrer = entry.getValue().nextNodes().iterator();
			while (recorrer.hasNext()) {
				g.setColor(Color.DARK_GRAY);
				Node node = recorrer.next();
				g.drawLine(iniX + iniX / 2 + entry.getValue().getPositionCoords().getX() * 150,
						iniY + iniY / 2 + entry.getValue().getPositionCoords().getY() * 150,
						iniX + iniX / 2 + node.getPositionCoords().getX() * 150,
						iniY + iniY / 2 + node.getPositionCoords().getY() * 150);
			}

		}
		for (Map.Entry<Position, Node> entry2 : mapa.entrySet()) {

			if (entry2.getValue().getType() == "RED") {
				g.setColor(Color.RED);
			} else if (entry2.getValue().getType() == "YELLOW") {
				g.setColor(Color.YELLOW);
			} else if (entry2.getValue().getType() == "END") {
				g.setColor(Color.BLACK);
			} else
				g.setColor(Color.ORANGE);
			g.fillOval(iniX + entry2.getValue().getPositionCoords().getX() * 150,
					iniY + entry2.getValue().getPositionCoords().getY() * 150, 75, 75);
		}

		for (int i = 0; i < listaP.size(); i++) {
			g.setColor(coloresP[i]);
			g.fillRect(iniX + listaP.get(i).getNodeLocation().getPositionCoords().getX()*150 + i*25,
					iniX + listaP.get(i).getNodeLocation().getPositionCoords().getY()*150 + 20, 20, 20);
		}
	}
	
	public void actualizar(int turn) {
		this.turn = turn;
	}

}
