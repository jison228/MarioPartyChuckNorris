package com.chucknorris.client.lobby;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.chucknorris.User;

public class UserPanel extends JPanel{
	private List<User> usuarios;
	
	public UserPanel(List<User> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void paint(Graphics g) {
		for(int i = 0; i < usuarios.size(); i++) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", Font.BOLD, 25));
			g.drawString(usuarios.get(i).getPlayerID(), 6%(i + 1) * 100 + 50, i * 100 + 100);
		}
		
	}
	
	public void updatePanel(List<User> usuarios) {
		this.usuarios = usuarios;
	}
	
}
