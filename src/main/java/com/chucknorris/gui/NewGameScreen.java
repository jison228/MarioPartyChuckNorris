package com.chucknorris.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.chucknorris.game.Game;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGameScreen extends JFrame {

	private JPanel newGamePane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameScreen frame = new NewGameScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public NewGameScreen() throws Exception {
		setTitle("Empezar Nueva Partida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setResizable(false);

		newGamePane = new JPanel();
		newGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		newGamePane.setLayout(null);
		setContentPane(newGamePane);

		int titleW = 280;
		int titleH = 50;
		// New Game Title
		JLabel lblNombreMapa = new JLabel("New Game");
		lblNombreMapa.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNombreMapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMapa.setBounds((getWidth() / 2) - titleW / 2, getHeight() - (int) (getHeight() * 0.95), titleW,
				titleH);
		newGamePane.add(lblNombreMapa);

		// GameOptions Title
		JLabel gameOptionsLbl = new JLabel("Game Options");
		gameOptionsLbl.setFont(new Font("Tahoma", Font.PLAIN, 25));
		gameOptionsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		gameOptionsLbl.setBounds((getWidth() / 2) + titleW / 2, getHeight() - (int) (getHeight() * 0.80), titleW,
				titleH);// TODO cambiar este enchastre
		newGamePane.add(gameOptionsLbl);

		// New Game Combo box
		JComboBox mapCombo = new JComboBox<String>();
		mapCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mapCombo.setBounds(50, 150, 200, 25);
		newGamePane.add(mapCombo);

		// Map image
		JButton mapImage = new JButton("MAP IMAGE");
		mapImage.setFont(new Font("Tahoma", Font.PLAIN, 25));
		mapImage.setHorizontalAlignment(SwingConstants.CENTER);
		mapImage.setVerticalAlignment(SwingConstants.CENTER);
		mapImage.setBounds(50, 200, 300, 300);
		newGamePane.add(mapImage);

		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBackground(Color.RED);
		btnJugar.setForeground(Color.DARK_GRAY);
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 60));
		btnJugar.setBounds(986, 576, 246, 86);
		newGamePane.add(btnJugar);

		GameMap mapa1;
		MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("map_1.txt");
		mapa1 = mapFileCSVReader.buildGameMap();
		
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainGameScreen(new Game(null, mapa1)).setVisible(true);;
			}
		});

	}
}
