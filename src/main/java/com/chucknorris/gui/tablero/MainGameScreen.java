package com.chucknorris.gui.tablero;

import com.chucknorris.commons.Dice;
import com.chucknorris.game.Game;
import com.chucknorris.game.GameResponse;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gamemap.nodes.Node;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.gui.compradolares.CompraDolaresFrame;
import com.chucknorris.gui.endgame.Endgame;
import com.chucknorris.gui.minigame.userinterface.GameWindow;
import com.chucknorris.player.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainGameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Game partida;
	private boolean TAB;
	private boolean ctrl;
	private JButton btnTirarDado;
	private JButton btnCamino1;
	private JButton btnCamino2;
	private JButton btnEndTurn;
	private Player currentPlayer;
	private GameResponse respuesta;
	private boolean comprarDolares;
	private CompraDolaresFrame dolaresFrame;
	private JPanelGame gamePanel;
	private GameInformation info;
	private InfoPanel characterPanel;
	private JLabel diceImage;
	private DescriptionPanel panelDescrip;
	private GameWindow minijuego;
	private boolean running;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// SOLO PARA TESTEAR
					GameMap mapa1;
					MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("newMap1.txt");
					mapa1 = mapFileCSVReader.buildGameMap();
					Espert p1 = new Espert(1450, 150, 100);
					Cristina p2 = new Cristina(150, 100, 900);
					Macri p3 = new Macri(500, 100, 100);
					DelCanio p4 = new DelCanio(150, 100, 100);
					List<Player> listaP = new ArrayList<Player>();
					listaP.add(p1);
					listaP.add(p2);
					listaP.add(p3);
					listaP.add(p4);
					GameInformation test = new GameInformation(listaP, mapa1, new Dice(1, 6), 20);

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
		comprarDolares = false;
		running = false;
		// Iniciar partida
		partida = new Game(info);
		partida.getGameMap().initializePlayers(partida.getPlayerList());

		TAB = false;
		ctrl = false;
		// JFrame config
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel para jugadores
		JPanelPlayers playersPanel = new JPanelPlayers(partida.getPlayerList());
		playersPanel.setBounds(1000, 0, 280, 450);
		contentPane.add(playersPanel);
		playersPanel.setVisible(false);
		
		// Panel para descripcion
		panelDescrip = new DescriptionPanel();
		panelDescrip.setBounds(0, 0, 280, 720);
		contentPane.add(panelDescrip);
		panelDescrip.setVisible(false);

		// Panel para chat (no implementado)
		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(Color.lightGray);
		chatPanel.setBounds(1000, 453, 280, 230);
		contentPane.add(chatPanel);
		chatPanel.setVisible(false);
		chatPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel chatLbl = new JLabel("CHAT");
		chatLbl.setFont(new Font("Tahoma", Font.BOLD, 24));
		chatPanel.add(chatLbl);

		// BOTONES DE DECISION (experimentacion)
		// 1
		btnCamino1 = new DecisionButton();
		btnCamino1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCamino1.setVisible(false);
				btnCamino2.setVisible(false);
				respuesta = partida.resolveIntersectionGUI(currentPlayer,
						currentPlayer.getNodeLocation().nextNodes().get(0), respuesta.movementsLeft);
				Node transitionNode;
				int size = respuesta.nodePath.size();
				for (int i = 0; i < size; i++) {
					transitionNode = respuesta.nodePath.poll();
					if (transitionNode.getType().equals("END"))
						comprarDolares = true;
					currentPlayer.setNodeLocation(transitionNode);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.paintImmediately(0, 0, 1280, 720);
				}
				if (respuesta.movementsLeft > 0) {
					tomarDecision(currentPlayer);
				} else
					endTurn();
				repaint();
			}
		});
		contentPane.add(btnCamino1);
		btnCamino1.setVisible(false);
		btnCamino1.setFocusable(false);

		// 2
		btnCamino2 = new DecisionButton();
		btnCamino2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCamino1.setVisible(false);
				btnCamino2.setVisible(false);
				respuesta = partida.resolveIntersectionGUI(currentPlayer,
						currentPlayer.getNodeLocation().nextNodes().get(1), respuesta.movementsLeft);
				Node transitionNode;
				int size = respuesta.nodePath.size();
				for (int i = 0; i < size; i++) {
					transitionNode = respuesta.nodePath.poll();
					if (transitionNode.getType().equals("END"))
						comprarDolares = true;
					currentPlayer.setNodeLocation(transitionNode);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.paintImmediately(0, 0, 1280, 720);
				}
				if (respuesta.movementsLeft > 0) {
					tomarDecision(currentPlayer);
				} else
					endTurn();
				repaint();
			}
		});
		contentPane.add(btnCamino2);
		btnCamino2.setVisible(false);
		btnCamino2.setFocusable(false);
		
		// Panel del juego
		gamePanel = new JPanelGame(partida.getGameMap().getMap(), partida.getPlayerList());
		gamePanel.setBackground(SystemColor.text);
		gamePanel.setBounds(0, 0, 1280, 600);
		contentPane.add(gamePanel);
		gamePanel.setLayout(null);

		// Panel para el boton
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(840, 560, 440, 160);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		// Foto Dado
		diceImage = new JLabel();
		diceImage.setBounds(200, 0, 115, 115);
		diceImage.setVisible(false);
		buttonPanel.add(diceImage);

		// Panel para el personaje
		characterPanel = new InfoPanel(partida.getPlayerList().get(partida.getCurrentTurn() % 4),
				partida.getCurrentTurn(), partida.getPrecioDolar());
		characterPanel.setBounds(0, 560, 840, 160);
		contentPane.add(characterPanel);

		// TAB
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getExtendedKeyCode();
				if (key == KeyEvent.VK_SHIFT) {
					if (TAB) {
						chatPanel.setVisible(false);
						playersPanel.setVisible(false);
						TAB = false;
					} else {
						chatPanel.setVisible(true);
						playersPanel.setVisible(true);
						TAB = true;
					}
				}
				if (key == KeyEvent.VK_CONTROL) {
					if (ctrl) {
						panelDescrip.setVisible(false);
						ctrl = false;
					} else {
						panelDescrip.setVisible(true);
						ctrl = true;
					}
				}
				
			}
		});

		// BOTON DE TIRAR DADO
		btnTirarDado = new JButton("TIRAR DADO");
		btnTirarDado.setForeground(Color.RED);
		btnTirarDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playTurn();
				diceImage.setVisible(true);
				diceImage.setIcon(new ImageIcon("images/dice/" + respuesta.diceResult + ".png"));
				Node transitionNode = respuesta.nodePath.poll();
				int size = respuesta.nodePath.size();
				for (int i = 0; i < size; i++) {
					transitionNode = respuesta.nodePath.poll();
					if (transitionNode.getType().equals("END"))
						comprarDolares = true;
					currentPlayer.setNodeLocation(transitionNode);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					contentPane.paintImmediately(0, 0, 1280, 720);
				}
				if (respuesta.movementsLeft > 0)
					tomarDecision(currentPlayer);
				else
					endTurn();
				repaint();
			}
		});
		btnTirarDado.setBounds(0, 25, 150, 60);
		buttonPanel.add(btnTirarDado);
		btnTirarDado.setFocusable(false);

		btnEndTurn = new JButton("FINALIZAR TURNO");
		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endTurnIndeed();
			}
		});
		btnEndTurn.setVisible(false);
		btnEndTurn.setForeground(Color.RED);
		btnEndTurn.setFocusable(false);
		btnEndTurn.setBounds(0, 25, 150, 60);
		buttonPanel.add(btnEndTurn);

	}

	public void playTurn() {
		currentPlayer = partida.getPlayerList().get(partida.getCurrentTurn() % 4);
		respuesta = partida.playGUI(currentPlayer);
	}

	public void endTurn() {		
		//currentPlayer.addDolar(500);
		if (comprarDolares) {
			currentPlayer.cobrarSalario();
			dolaresFrame = new CompraDolaresFrame(currentPlayer, partida.getPrecioDolar());
			dolaresFrame.setVisible(true);
			comprarDolares = false;
			dolaresFrame.addWindowListener(new WindowListener() {

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void windowClosed(WindowEvent e) {
					repaint();

				}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}
		btnEndTurn.setVisible(true);
		btnTirarDado.setVisible(false);
		repaint();
	}

	public void tomarDecision(Player currentPlayer) {
		btnTirarDado.setVisible(false);
		btnCamino1.setBounds(50 + currentPlayer.getNodeLocation().nextNodes().get(0).getPositionCoords().getX() * 150,
				30 + currentPlayer.getNodeLocation().nextNodes().get(0).getPositionCoords().getY() * 150, 75, 75);
		btnCamino2.setBounds(50 + currentPlayer.getNodeLocation().nextNodes().get(1).getPositionCoords().getX() * 150,
				30 + currentPlayer.getNodeLocation().nextNodes().get(1).getPositionCoords().getY() * 150, 75, 75);
		btnCamino1.setVisible(true);
		btnCamino2.setVisible(true);
	}

	public void endTurnIndeed() {
		if (partida.getCurrentTurn() % 4 == 3) {
			partida.aumentarPrecioDolar();
			minijuego = new GameWindow();
			minijuego.startGame();
			minijuego.addWindowListener(new WindowListener() {
				
				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void windowClosed(WindowEvent e) {
					String ganador1 = minijuego.listaGanadores.pop();
					String ganador2 = minijuego.listaGanadores.pop();
					for(Player player:partida.getPlayerList()) {
						if(player.getCharacter().equals(ganador1)|| player.getCharacter().equals(ganador2)) {
							player.addPesosByPercentage(50);
							player.addDolaresByPercentage(50);
						}
					}
					repaint();
				}
				
				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			partida.aumentarPrecioDolar();
		}
		partida.endTurn();
		btnTirarDado.setVisible(true);
		btnEndTurn.setVisible(false);
		Player ganador = new PlayerBuilder()
				.setCharacter("Dummy")
				.build();

		for (Player player : partida.getPlayerList()) {
			if (player.getDolares() > 300 && player.getDolares() >= ganador.getDolares()) {
				ganador = player;
			}
		}
		if (ganador.getDolares() > 150) {
			new Endgame(ganador).setVisible(true);
			dispose();
		}
		characterPanel.actualizar(partida.getPlayerList().get(partida.getCurrentTurn() % 4), partida.getCurrentTurn(), partida.getPrecioDolar());
		diceImage.setVisible(false);
		repaint();
	}
}
