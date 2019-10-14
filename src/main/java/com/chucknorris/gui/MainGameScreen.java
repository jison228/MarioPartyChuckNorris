package com.chucknorris.gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.commons.Dice;
import com.chucknorris.game.Game;
import com.chucknorris.game.GameResponse;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.player.Player;

import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
					//SOLO PARA TESTEAR
					GameMap mapa1;
					MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("map_1.txt");
					mapa1 = mapFileCSVReader.buildGameMap();
					Player p1 = new Player("Milei", 1450, 150);
					Player p2 = new Player("Morsa", 150, 200);
					Player p3 = new Player("Cristina", 500, 600);
					Player p4 = new Player("Mauricio", 150, 900);
					List<Player> listaP = new ArrayList<Player>();
					listaP.add(p1);
					listaP.add(p2);
					listaP.add(p3);
					listaP.add(p4);
					GameInformation test = new GameInformation(listaP, mapa1, 150, new Dice(1, 6), 20);
					
					MainGameScreen frame = new MainGameScreen(test);
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
		partida.getGameMap().initializePlayers(partida.getPlayerList());

		TAB = false;
		//JFrame config
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel del juego
		JPanelGame gamePanel = new JPanelGame(partida.getGameMap().getMap(),partida.getPlayerList());
		gamePanel.setBackground(SystemColor.text);
		gamePanel.setBounds(0, 0, 1280, 720);
		contentPane.add(gamePanel);
		gamePanel.setLayout(null);
		
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
		chatPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel chatLbl = new JLabel("CHAT");
		chatLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		chatPanel.add(chatLbl);
		
		//TAB
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				int key = e.getExtendedKeyCode();
				System.out.println(key);
				if(key == KeyEvent.VK_SHIFT) {
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
				if(key == KeyEvent.VK_ENTER) {
					Player currentPlayer = partida.getPlayerList().get(partida.getCurrentTurn()%4);
					GameResponse respuesta = partida.play(currentPlayer);
					if(respuesta.movementsLeft>0) {
						respuesta = partida.resolveIntersection(currentPlayer, currentPlayer.getNodeLocation().nextNodes().get(0), respuesta.movementsLeft);
					}
					partida.endTurn();
					contentPane.repaint();
				}
			}
		});
		
		//Boton de Tirar Dado
		//JButton btnTirarDado = new JButton("Tirar Dado");
		//btnTirarDado.setBounds(150, 150, 200, 200);
		//contentPane.add(btnTirarDado);
		
	}

}
