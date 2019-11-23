package com.chucknorris.client.sala;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.chucknorris.User;

public class SalaSpectatorsPanel extends JPanel{

	private List<User> spectators;
	
	public SalaSpectatorsPanel(List<User> spectators) {
		this.spectators = spectators;
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Century Gothic", Font.BOLD, 35));
		g.drawString("ESPECTADORES", 200, 50);
		
		for(int i = 0; i < spectators.size(); i++) {
			int separacion = i < 3? i * 40 : (i - 3) * 40;
			
			g.setColor(Color.ORANGE);
			g.fillRect(3 + (i/3) * 180, 60 + separacion, 150, 30);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 20));
			g.drawString(spectators.get(i).getPlayerID(), 5 + (i/3) * 180, 80 + separacion);

			g.setFont(new Font("Courier", Font.BOLD, 13));
		}
	}
	
	public final void updatePanel(List<User> spectators) {
		this.spectators = spectators;
	}
}
