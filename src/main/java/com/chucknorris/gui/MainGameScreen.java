package com.chucknorris.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.commons.Position;
import com.chucknorris.game.Game;
import com.chucknorris.gamemap.GameMap;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;

public class MainGameScreen extends JFrame {

	private JPanel contentPane;
	private Game partida;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameScreen frame = new MainGameScreen(new Game(null,null));
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
	public MainGameScreen(Game partida) {
		
		//JFrame config
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel para jugadores
		JPanel playersPanel = new JPanel();
		playersPanel.setBackground(Color.LIGHT_GRAY);
		playersPanel.setBounds(1000, 0, 280, 450);
		contentPane.add(playersPanel);
		
		JLabel playersLbl = new JLabel("PLAYERS");
		playersLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		playersPanel.add(playersLbl);
		
		//Panel para chat (no implementado)
		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(Color.PINK);
		chatPanel.setBounds(1000, 453, 280, 230);
		contentPane.add(chatPanel);
		
		JLabel chatLbl = new JLabel("CHAT");
		chatLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		chatPanel.add(chatLbl);
		
		//Panel del juego
		JPanelGrafico gamePanel = new JPanelGrafico(partida.getGameMap().getMap(),null);
		gamePanel.setBackground(SystemColor.text);
		gamePanel.setBounds(0, 0, 1000, 683);
		contentPane.add(gamePanel);
		
		
	}
	
	public static void dibujarMapa(GameMap mapa) {

		
	}
}
