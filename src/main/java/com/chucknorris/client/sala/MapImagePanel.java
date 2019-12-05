package com.chucknorris.client.sala;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapImagePanel extends JPanel{
	
	private BufferedImage image;

	public MapImagePanel(String imagePath) {
		try {
			image = ImageIO.read(new File("images/maps/" + imagePath));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
	public void updateImage(String imagePath) {
		try {
			image = ImageIO.read(new File("images/maps/" + imagePath));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
