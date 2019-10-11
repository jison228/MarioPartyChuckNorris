package com.chucknorris.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JFileChooser;

public class TitleScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleScreen frame = new TitleScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TitleScreen() {
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Titulo
		JLabel Title = new JLabel("INDEFINIDO");
		int titleW = 280;
		int titleH = 50;
		Title.setBounds((getWidth() / 2) - titleW / 2, getHeight() - (int) (getHeight() * 0.90), titleW,
				titleH);
		Title.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 42));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Title);
		
		//Botones
		int btnW = 400;
		int btnH = 80;
		
		//Nueva Partida
		JButton btnNewGame = new JButton("Nueva partida");
		btnNewGame.setBounds((getWidth() / 2) - btnW / 2, getHeight() - (int) (getHeight() * 0.60), btnW,
				btnH);
		contentPane.add(btnNewGame);

		
		//Salir
		JButton btnQuit = new JButton("Salir");
		
		btnQuit.setBounds((this.getWidth() / 2) - btnW / 2, this.getHeight() - (int) (this.getHeight() * 0.40), btnW,
				btnH);
		contentPane.add(btnQuit);
		
		JLabel lblNombreMapa = new JLabel("NOMBRE MAPA1");
		lblNombreMapa.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNombreMapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMapa.setBounds(500, 209, 280, 50);
		//TODO definir bounds
		contentPane.add(lblNombreMapa);
		
		//Acciones de los botones
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewGame.setVisible(false);
				btnQuit.setVisible(false);
				//fileChooser.setVisible(true);
			}
		});
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		
		
	}
}
