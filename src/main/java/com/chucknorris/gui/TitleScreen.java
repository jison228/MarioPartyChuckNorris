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
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JFileChooser;

public class TitleScreen extends JFrame {

	private JPanel mainPane;
	private JPanel newGamePane;

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
		// JPanel config
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);

		// mainPane config
		mainPane = new JPanel();
		mainPane.setBackground(SystemColor.scrollbar);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		// newGamePane config
		newGamePane = new JPanel();
		newGamePane.setBackground(SystemColor.cyan);
		newGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		newGamePane.setLayout(null);

		// Titulo
		JLabel Title = new JLabel("INDEFINIDO");
		int titleW = 280;
		int titleH = 50;
		Title.setBounds((getWidth() / 2) - titleW / 2, getHeight() - (int) (getHeight() * 0.90), titleW, titleH);
		Title.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 42));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		mainPane.add(Title);

		// Botones
		int btnW = 400;
		int btnH = 80;

		// Nueva Partida
		JButton btnNewGame = new JButton("Nueva partida");
		btnNewGame.setBounds((getWidth() / 2) - btnW / 2, getHeight() - (int) (getHeight() * 0.60), btnW, btnH);
		mainPane.add(btnNewGame);

		// Salir
		JButton btnQuit = new JButton("Salir");

		btnQuit.setBounds((this.getWidth() / 2) - btnW / 2, this.getHeight() - (int) (this.getHeight() * 0.40), btnW,
				btnH);
		mainPane.add(btnQuit);
		
		//New Game Title
		JLabel lblNombreMapa = new JLabel("New Game");
		lblNombreMapa.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNombreMapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMapa.setBounds((getWidth() / 2) - titleW / 2, getHeight() - (int) (getHeight() * 0.90), titleW, titleH);
		newGamePane.add(lblNombreMapa);

		// New Game Combo box
		JComboBox mapCombo = new JComboBox<String>();
		mapCombo.setFont(new Font("Tahoma",Font.PLAIN,15));
		mapCombo.setBounds(50, 150, 200, 25);
		newGamePane.add(mapCombo);
		
		// Map image
		JButton mapImage = new JButton("MAP IMAGE");
		mapImage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		mapImage.setHorizontalAlignment(SwingConstants.CENTER);
		mapImage.setVerticalAlignment(SwingConstants.CENTER);
		mapImage.setBounds(50,200,300,300);
		newGamePane.add(mapImage);
		
		
		
		// Acciones de los botones
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.setVisible(false);
				setContentPane(newGamePane);
				// newGamePane.setVisible(true);
			}
		});

		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
