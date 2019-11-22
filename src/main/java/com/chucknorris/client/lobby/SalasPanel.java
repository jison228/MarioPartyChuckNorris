package com.chucknorris.client.lobby;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.chucknorris.client.ClientInfoSalas;

public class SalasPanel extends JPanel{
	
	List<ClientInfoSalas> salas;
	
	public SalasPanel(List<ClientInfoSalas> salas) {
		this.salas = salas;
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < salas.size(); i++) {
			int separacion = i * 70;
			g.setColor(Color.orange);
			g.fillRect(0, 10 + separacion, 400, 68);
			
			g.setColor(Color.black);
			g.setFont(new Font("Tahoma", Font.BOLD, 22));
			g.drawString(salas.get(i).name, 10, 40 + separacion);
			
			g.setFont(new Font("Tahoma", Font.PLAIN, 18));
			g.drawString("Players: " + salas.get(i).cantPlayers + "/4", 15, 65 + separacion);
			
			g.drawString("Espectadores: " + salas.get(i).cantSpectadores, 180, 40 + separacion);
			
			g.drawString(salas.get(i).playing ? "Jugando" : "En la Sala", 180, 65 + separacion);
		}
	}
	
	public final void updatePanel(List<ClientInfoSalas> salas) {
		this.salas = salas;
	}
}
