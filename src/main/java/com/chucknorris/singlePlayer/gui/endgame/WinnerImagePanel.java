package com.chucknorris.singlePlayer.gui.endgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WinnerImagePanel extends JPanel {
	private BufferedImage image;

	public WinnerImagePanel(String imagePath) {
		try {
			image = ImageIO.read(new File("images/ganador/" + imagePath+".jpg"));
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
