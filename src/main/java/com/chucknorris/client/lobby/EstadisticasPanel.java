package com.chucknorris.client.lobby;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EstadisticasPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Graphics g;
	private List<Partida> listaDePartidas;
	private String user;

	public EstadisticasPanel(String imagePath) {
		try {
			image = ImageIO.read(new File("images/backgrounds/" + imagePath));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		this.listaDePartidas= new ArrayList<Partida>();
	}

	public void anadirPartida(List<Partida> listaDePartidas, String user) {
		this.user = user;
		this.listaDePartidas = listaDePartidas;
		repaint();
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
		g.fillRect(40, 150, 600, 500);

		g.setColor(Color.black);
		g.setFont(new Font("Courier", Font.BOLD, 42));
		g.drawString("Mis Estadísticas", (getWidth() / 2) - title1W / 3 + 140,
				getHeight() - (int) (getHeight() * 0.90));

		if (!listaDePartidas.isEmpty()) {
			int i=0;
			for (Partida partida : listaDePartidas) {
				if (partida.getWinner().equals(user)) {
					g.setColor(Color.green);
				}else {
					g.setColor(Color.red);
				}
				g.setFont(new Font("Courier", Font.BOLD, 12));
				g.drawString(String.valueOf(partida.getId()), 84, 220+i*20);
				g.drawString(String.valueOf(partida.getPlayer1()), 204, 220+i*20);
				g.drawString(String.valueOf(partida.getPlayer2()), 284, 220+i*20);
				g.drawString(String.valueOf(partida.getPlayer3()), 364, 220+i*20);
				g.drawString(String.valueOf(partida.getPlayer4()), 444, 220+i*20);
				g.drawString(String.valueOf(partida.getWinner()), 560, 220+i*20);
				i++;
			}
		}
	}
}
