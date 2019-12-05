package com.chucknorris.gui.gameoptions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NewGameImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public NewGameImagePanel(String imagePath) {
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
		g.setColor(Color.lightGray);
		// TODO calcular weight y height en base a la pantalla
		g.setColor(Color.WHITE);
		
		//Titulo
		g.setFont(new Font("Courier", Font.BOLD, 45));
		g.drawString("Nueva Partida", 450, getHeight() - (int) (getHeight() * 0.90));
		
		//Mapa
		g.setFont(new Font("Courier", Font.BOLD, 30));
		g.drawString("Elegir mapa", 70, getHeight() - (int) (getHeight() * 0.75));
		
		//Opciones de Juego
		g.setFont(new Font("Courier",Font.BOLD,30));
		g.drawString("Opciones de Juego", 450 , getHeight() - (int) (getHeight() * 0.75));
		
		//Dado
		g.setFont(new Font("Courier",Font.BOLD,25));
		g.drawString("Dado", 450 , getHeight() - (int) (getHeight() * 0.68));
		
		//Precio dolar
		g.setFont(new Font("Courier",Font.BOLD,25));
		g.drawString("Precio Dolar", 650 , getHeight() - (int) (getHeight() * 0.68));
		
		//Rango min dado
		g.setFont(new Font("Courier",Font.PLAIN,20));
		g.drawString("Min", 450, getHeight() - (int) (getHeight() * 0.65));
		
		//Rango max dado
		g.setFont(new Font("Courier",Font.PLAIN,20));
		g.drawString("Max", 550, getHeight() - (int) (getHeight() * 0.65));
		
		//Salario inicial
		g.setFont(new Font("Courier",Font.BOLD,25));
		g.drawString("Salario inicial", 450 , getHeight() - (int) (getHeight() * 0.55));
		
		//Pesos iniciales
		g.setFont(new Font("Courier",Font.BOLD,25));
		g.drawString("Pesos iniciales", 450 , getHeight() - (int) (getHeight() * 0.40));
		
		//Dolares iniciales
		g.setFont(new Font("Courier",Font.BOLD,25));
		g.drawString("Dolares iniciales", 450 , getHeight() - (int) (getHeight() * 0.25));
		
		//Jugadores
		g.setFont(new Font("Courier",Font.BOLD,30));
		g.drawString("Jugadores", 950 , getHeight() - (int) (getHeight() * 0.75));
	
		//Monedas
		g.setColor(Color.green);
		g.drawString("$", 685 , getHeight() - (int) (getHeight() * 0.48));
		g.drawString("$", 685 , getHeight() - (int) (getHeight() * 0.32));
		g.drawString("US$", 685 , getHeight() - (int) (getHeight() * 0.16));
		g.drawString("$", 800, getHeight() - (int) (getHeight() * 0.60));
	}
}
