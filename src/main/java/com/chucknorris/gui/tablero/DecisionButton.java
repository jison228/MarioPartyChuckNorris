package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class DecisionButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BufferedImage image = null;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	public void actualizarImagen(String fileName) {
		try {
			image = ImageIO.read(new File("images/nodes/" + fileName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
