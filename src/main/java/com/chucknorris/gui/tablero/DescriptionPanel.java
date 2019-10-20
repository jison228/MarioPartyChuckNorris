package com.chucknorris.gui.tablero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DescriptionPanel extends JPanel {

	public DescriptionPanel() {

		JLabel lblInformacinSobreLos = new JLabel("Informaci\u00F3n sobre los nodos");
		lblInformacinSobreLos.setFont(new Font("Courier New", Font.BOLD, 17));
		add(lblInformacinSobreLos);
	}

	@Override
	protected void paintComponent(Graphics g) {
		JLabel lblNewLabel = new JLabel("Informaci\u00F3n sobre los nodos");
		lblNewLabel.setFont(new Font("Courier New", Font.BOLD, 16));
		g.setColor(Color.pink);
		g.fillRect(0, 0, 280, 720);
		g.setColor(Color.RED);
		g.fillOval(0, 70 * 1, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Dolares", 0, 70);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("AFIP", 10, 99);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 14));
		g.drawString("Tributan todos", 0, 133);

		g.setColor(Color.ORANGE);
		g.fillOval(0, 180, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Pesos", 8, 178);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("AFIP", 10, 208);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 14));
		g.drawString("Tributan todos", 0, 242);

		g.setColor(Color.MAGENTA);
		g.fillOval(0, 290, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Boleta", 4, 287);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("GAS", 12, 320);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 14));
		g.drawString("PAGUE", 0, 354);

		g.setColor(Color.BLACK);
		g.fillOval(0, 400, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Salida", 0, 396);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 11));
		g.drawString("Cobra salario", 0, 460);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 11));
		g.drawString("Puede comprar dolares", 0, 474);

		g.setColor(Color.BLUE);
		g.fillOval(0, 510, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Power Up!", 0, 508);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Activame!", 0, 570);

		// verde oscuro
		g.setColor(new Color(29, 118, 22));
		g.fillOval(180, 70, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Inversion", 172, 54);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Dolares", 178, 70);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("ESSSTASIS", 178, 130);

		// verde claro
		g.setColor(new Color(45, 222, 33));
		g.fillOval(180, 180, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Perdida", 180, 164);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Dolares", 180, 180);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 14));
		g.drawString("Pasaron cosas", 168, 240);

		// gris oscuro
		g.setColor(new Color(100, 103, 104));
		g.fillOval(180, 290, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Perdida", 180, 274);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Pesos", 184, 290);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 14));
		g.drawString("Malio sal", 174, 350);

		// gris claro
		g.setColor(new Color(190, 191, 191));
		g.fillOval(180, 400, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Inversion", 174, 384);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Pesos", 184, 400);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 12));
		g.drawString("En serio gane?", 170, 460);

		g.setColor(Color.YELLOW);
		g.fillOval(180, 510, 48, 48);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.drawString("Paritarias", 170, 505);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 14));
		g.drawString("Sube su sueldo", 156, 575);
	}
}
