package com.chucknorris.gui.compradolares;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.player.Player;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class CompraDolaresFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private JTextField pesosTF;
	private JLabel pesosActualesLbl;
	private JLabel dolaresActualeslbl;
	double resultado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player p = new Player("Cristi", 150, 100);
					CompraDolaresFrame frame = new CompraDolaresFrame(p, 20);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CompraDolaresFrame(Player player, double precioDolar) {
		// JFrameConfig
		setAlwaysOnTop(true);
		setTitle("Comprar Dolares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 200, 400, 250);
		setResizable(false);

		// mainPane config
		mainPane = new CompraDolaresImagePanel("banco.jpg");
		mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel dolaresLbl = new JLabel("0");
		dolaresLbl.setBackground(Color.LIGHT_GRAY);
		dolaresLbl.setForeground(Color.GREEN);
		dolaresLbl.setFont(new Font("Arial Black", Font.PLAIN, 25));
		dolaresLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dolaresLbl.setBounds(216, 94, 105, 30);
		mainPane.add(dolaresLbl);

		pesosActualesLbl = new JLabel("Pesos Actuales: " + player.getPesos());
		pesosActualesLbl.setForeground(Color.WHITE);
		pesosActualesLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		pesosActualesLbl.setBackground(Color.ORANGE);
		pesosActualesLbl.setBounds(10, 176, 173, 36);
		mainPane.add(pesosActualesLbl);

		dolaresActualeslbl = new JLabel("Dolares Actuales: " + player.getDolares());
		dolaresActualeslbl.setForeground(Color.WHITE);
		dolaresActualeslbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		dolaresActualeslbl.setBackground(Color.ORANGE);
		dolaresActualeslbl.setBounds(213, 176, 173, 36);
		mainPane.add(dolaresActualeslbl);
		
		pesosTF = new JTextField();
		pesosTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if((e.getKeyCode()>=48 && e.getKeyCode() <= 57) || (e.getKeyCode()>= 96 && e.getKeyCode() <= 105)) {
					if(!pesosTF.getText().isEmpty()) {
						resultado = Double.valueOf(pesosTF.getText())/precioDolar;
						dolaresLbl.setText(Double.toString(resultado));
					}
				}
			}
		});
		pesosTF.setBackground(Color.LIGHT_GRAY);
		pesosTF.setForeground(Color.GREEN);
		pesosTF.setText("0");
		pesosTF.setHorizontalAlignment(SwingConstants.RIGHT);
		pesosTF.setFont(new Font("Arial Black", Font.PLAIN, 25));
		pesosTF.setBounds(10, 94, 105, 30);
		mainPane.add(pesosTF);
		pesosTF.setColumns(10);
		
		JButton aceptarBtn = new AceptarButton("cambiar.jpg");
		aceptarBtn.setBounds(164, 94, 70, 30);
		mainPane.add(aceptarBtn);
	}
	
	public void call() {
		setVisible(true);
	}
}
