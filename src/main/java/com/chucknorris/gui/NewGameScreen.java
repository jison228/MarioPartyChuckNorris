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
	 */
	public NewGameScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1280,720);
		setResizable(false);
		
		
		newGamePane = new JPanel();
		newGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		newGamePane.setLayout(null);
		setContentPane(newGamePane);

		int titleW = 280;
		int titleH = 50;
		// New Game Title
		JLabel lblNombreMapa = new JLabel("New Game");
		lblNombreMapa.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNombreMapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreMapa.setBounds((getWidth() / 2) - titleW / 2, getHeight() - (int) (getHeight() * 0.90), titleW,
				titleH);
		newGamePane.add(lblNombreMapa);

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

	}

}
