package com.chucknorris.client.compradolares;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class AceptarButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public AceptarButton(String imagePath) {
		try {
			image = ImageIO.read(new File("images/icons/" + imagePath));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
