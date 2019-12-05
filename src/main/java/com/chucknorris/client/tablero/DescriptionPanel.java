package com.chucknorris.client.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DescriptionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DescriptionPanel() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage image = null;
		
		int dif=120;
		g.setColor(new Color(255,153,153,240));
		g.fillRect(0, 0, 280, 720);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 25));
		g.drawString("Informaci\u00F3n", 55, 20);
		g.drawString("sobre los nodos", 35, 45);
		
		try {
			image = ImageIO.read(new File("images/nodes/" + "IPP" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 25, dif*0 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "IDP" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 25, dif*1 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "AFIPP" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 25, dif*2 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "BG" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 25, dif*3 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "PWR" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 25, dif*4 + 70, this);
		
		try {
			image = ImageIO.read(new File("images/nodes/" + "IPM" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 170, dif*0 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "IDM" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 170, dif*1 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "AFIPD" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 170, dif*2 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "PARI" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 170, dif*3 + 70, this);
		try {
			image = ImageIO.read(new File("images/nodes/" + "END" + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 170, dif*4 + 70, this);
		
		g.setFont(new Font("Courier",Font.BOLD,15));
		g.drawString("Aumenta un ", 10, dif*0 + 160);
		g.drawString("% en pesos", 10, dif*0 +175);
		g.drawString("Aumenta un ", 10, dif*1 + 160);
		g.drawString("% en dolares", 10, dif*1 +175);
		g.drawString("Resta un % en", 10, dif*2 + 160);
		g.drawString("pesos a todos", 10, dif*2 + 175);
		g.drawString("los jugadores", 10, dif*2 +190);
		g.drawString("Resta la", 10, dif*3 + 160);
		g.drawString("factura de", 10, dif*3 +175);
		g.drawString("gas en pesos", 10, dif*3 +190);
		g.drawString("Aplica el", 15, dif*4 + 160);
		g.drawString("POWERUP", 25, dif*4 +175);
		g.drawString("del personaje", 10, dif*4 +190);
		g.drawString("Resta un ", 170, dif*0 + 160);
		g.drawString("% en pesos", 170, dif*0 +175);
		g.drawString("Resta un ", 170, dif*1 + 160);
		g.drawString("% en dolares", 170, dif*1 +175);
		g.drawString("Resta un % en", 140, dif*2 + 160);
		g.drawString("dolares a todos", 140, dif*2 + 175);
		g.drawString("los jugadores", 140, dif*2 +190);
		g.drawString("Aumenta el", 170, dif*3 + 160);
		g.drawString("sueldo", 170, dif*3 +175);
		g.drawString("Al pasar cobras", 140, dif*4 + 160);
		g.drawString("el sueldo y se", 140, dif*4 + 175);
		g.drawString("compran dolares", 140, dif*4 +190);
	}
}
