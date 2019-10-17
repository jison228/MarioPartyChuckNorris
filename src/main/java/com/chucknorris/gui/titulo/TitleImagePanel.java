package com.chucknorris.gui.titulo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TitleImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public TitleImagePanel(String imagePath) {
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
		g.setColor(Color.lightGray);
		// TODO calcular weight y height en base a la pantalla
		int title1W = 1000;
		int title2W = 150;
		g.fillRect((getWidth() / 2) - title1W / 3, getHeight() - (int) (getHeight() * 0.95), 640, 45);
		g.fillRect((getWidth() / 2) - title2W / 3, getHeight() - (int) (getHeight() * 0.85), 105, 45);

		g.setColor(Color.black);
		g.setFont(new Font("Courier", Font.BOLD, 42));
		g.drawString("ELECCIONES PRESIDENCIALES", (getWidth() / 2) - title1W / 3,
				getHeight() - (int) (getHeight() * 0.90));

		g.drawString("2019", (getWidth() / 2) - title2W / 3, getHeight() - (int) (getHeight() * 0.80));
	}
}
