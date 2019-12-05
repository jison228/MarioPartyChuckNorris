package com.chucknorris.client.tablero;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class DiceButton extends JButton{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage image;

	@Override
	protected void paintComponent(Graphics g) {
		try {
			image = ImageIO.read(new File("images/dice/" + "dice" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, this);
		
	}
}