package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class DecisionButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(255, 0, 127));
		g.fillOval(0, 0, 75, 75);
	}
}
