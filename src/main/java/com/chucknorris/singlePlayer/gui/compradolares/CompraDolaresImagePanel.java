package com.chucknorris.singlePlayer.gui.compradolares;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CompraDolaresImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public CompraDolaresImagePanel(String imagePath) {
		try {
			image = ImageIO.read(new File("images/backgrounds/" + imagePath));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(new Color(15, 246, 54));
		g.drawString("COMPRA DOLARES", 50, 50);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("$", 125, 119);
		g.drawString("US$", 325, 119);
	}
	
}
