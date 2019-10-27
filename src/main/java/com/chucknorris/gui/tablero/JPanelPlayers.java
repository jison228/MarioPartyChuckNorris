package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.chucknorris.player.Player;

public class JPanelPlayers extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Player> listaP;
	
	public JPanelPlayers(List<Player> listaP) {
		this.listaP = listaP;
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
		g.setFont(new Font("Tahoma",Font.BOLD,24));
		g.drawString("PLAYERS", 75, 30);
		for(int i=0;i<listaP.size();i++) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 18));
			g.drawString(listaP.get(i).getCharacter(), 25, 75 + i*100);
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.drawString("Salario: $" + listaP.get(i).getSalario(), 125, 75 + i*100);
			g.setColor(Color.green);
			g.setFont(new Font("Tahoma",Font.BOLD,15));
			g.drawString("$" + listaP.get(i).getPesos(), 25, 120 + i*100);
			g.drawString("U$S" + listaP.get(i).getDolares(), 125, 120 + i*100);
		}
		
	}
}
