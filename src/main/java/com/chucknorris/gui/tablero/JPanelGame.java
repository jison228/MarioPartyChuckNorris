package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
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
	private int iniX = 50;
	private int iniY = 30;

	public JPanelGame(Map<Position, Node> mapa, List<Player> listaP) {
		this.mapa = mapa;
		this.listaP = listaP;
	}

	@Override
	public void paintComponent(Graphics g) {
		BufferedImage image2 = null;
		try {
			image2 = ImageIO.read(new File("images/backgrounds/" + "juego" + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image2, 0, 0, this);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.BOLD, 24));

		for (Map.Entry<Position, Node> entry : mapa.entrySet()) {
			Iterator<Node> recorrer = entry.getValue().nextNodes().iterator();
			while (recorrer.hasNext()) {
				g.setColor(Color.DARK_GRAY);
				Node node = recorrer.next();
				g.drawLine(iniX + 35 + entry.getValue().getPositionCoords().getX() * 150,
						iniY + 35 + entry.getValue().getPositionCoords().getY() * 150,
						iniX + 35 + node.getPositionCoords().getX() * 150,
						iniY + 35 + node.getPositionCoords().getY() * 150);
			}

		}
		for (Map.Entry<Position, Node> entry2 : mapa.entrySet()) {

			if (entry2.getValue().getType() == "AFIPD") {
				g.setColor(Color.RED);
			} else if (entry2.getValue().getType() == "AFIPP") {
				g.setColor(Color.ORANGE);
			} else if (entry2.getValue().getType() == "BG") {
				g.setColor(Color.MAGENTA);
			} else if (entry2.getValue().getType() == "IDM") {
				g.setColor(new Color(29,118,22));
			} else if (entry2.getValue().getType() == "IDP") {
				g.setColor(new Color(45,222,33));
			} else if (entry2.getValue().getType() == "IPM") {
				g.setColor(new Color(100,103,104));
			} else if (entry2.getValue().getType() == "IPP") {
				g.setColor(new Color(190,191,191));
			} else if (entry2.getValue().getType() == "PARI") {
				g.setColor(Color.YELLOW);
			} else if (entry2.getValue().getType() == "PWR") {
				g.setColor(Color.BLUE);
			} else if (entry2.getValue().getType() == "END") {
				g.setColor(Color.BLACK);
			} else
				g.setColor(Color.BLACK);
			g.fillOval(iniX + entry2.getValue().getPositionCoords().getX() * 150,
					iniY + entry2.getValue().getPositionCoords().getY() * 150, 75, 75);
		}

		for (int i = 0; i < listaP.size(); i++) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("images/characters/" + listaP.get(i).getCharacter() + "Chico.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(image, iniX + listaP.get(i).getNodeLocation().getPositionCoords().getX() * 150 + (i%2) * 40,
					iniY + listaP.get(i).getNodeLocation().getPositionCoords().getY() * 150 + (i/2) * 40, this);
		}
	}

}
