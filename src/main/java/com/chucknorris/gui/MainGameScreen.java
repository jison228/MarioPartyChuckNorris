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
import com.chucknorris.player.Player;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainGameScreen extends JFrame {

	private JPanel contentPane;
	private Game partida;
	private boolean TAB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameScreen frame = new MainGameScreen(null);
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
	public MainGameScreen(GameInformation info) {
		partida = new Game(info.players, info.gameMap);
		
		
		TAB = false;
		//JFrame config
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel para jugadores
		JPanelPlayers playersPanel = new JPanelPlayers(info.players);
		playersPanel.setBounds(1000, 0, 280, 450);
		contentPane.add(playersPanel);
		playersPanel.setVisible(false);
	
		
		//Panel para chat (no implementado)
		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(Color.lightGray);
		chatPanel.setBounds(1000, 453, 280, 230);
		contentPane.add(chatPanel);
		chatPanel.setVisible(false);
		
		JLabel chatLbl = new JLabel("CHAT");
		chatLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		chatPanel.add(chatLbl);
		
		//Panel del juego
		JPanelGame gamePanel = new JPanelGame(partida.getGameMap().getMap(),info.players);
		gamePanel.setBackground(SystemColor.text);
		gamePanel.setBounds(0, 0, 1280, 720);
		contentPane.add(gamePanel);
		
		//TAB
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				int key = e.getExtendedKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					if(TAB) {
						chatPanel.setVisible(false);
						playersPanel.setVisible(false);
						TAB = false;
					} else {
						chatPanel.setVisible(true);
						playersPanel.setVisible(true);
						TAB = true;
					}
				}
			}
		});
		
		
	}
	
	public static void dibujarMapa(GameMap mapa) {

		
	}
}
