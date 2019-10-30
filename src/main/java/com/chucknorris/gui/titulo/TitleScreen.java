package com.chucknorris.gui.titulo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.gui.gameoptions.NewGameScreen;

import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TitleScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;

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
		// JFrame config
		setTitle("ELECCIONES PRESIDENCIALES 2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setResizable(false);

		// mainPane config
		mainPane = new TitleImagePanel("portada.jpg");
		mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		// Botones
		int btnW = 400;
		int btnH = 80;

		// Nueva Partida
		JButton btnNewGame = new JButton("Iniciar Sesion");
		btnNewGame.setFont(new Font("Courier", Font.BOLD, 25));
		btnNewGame.setFocusable(false);
		btnNewGame.setBounds((getWidth() / 2) - btnW / 2, getHeight() - (int) (getHeight() * 0.60), btnW, btnH);
		mainPane.add(btnNewGame);

		// Salir
		JButton btnQuit = new JButton("Irse del pais");
		btnQuit.setFont(new Font("Courier", Font.BOLD, 25));
		btnQuit.setFocusable(false);
		btnQuit.setBounds((this.getWidth() / 2) - btnW / 2, this.getHeight() - (int) (this.getHeight() * 0.40), btnW,
				btnH);
		mainPane.add(btnQuit);

		// Acciones de los botones
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new NewGameScreen().setVisible(true);
					dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
