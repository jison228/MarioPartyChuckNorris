package com.chucknorris.client.tablero;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.Command;
import com.chucknorris.client.ClientPlayer;
import com.chucknorris.client.EndTurnResponse;
import com.chucknorris.client.GameInformation;
import com.chucknorris.client.MovementResponsePrivate;
import com.chucknorris.client.MovementResponsePublic;
import com.chucknorris.client.compradolares.CompraDolaresFrame;
import com.chucknorris.client.endgame.Endgame;
import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gamemap.nodes.ParitariaNode;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;
import com.google.gson.Gson;

public class MainGameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean ctrl;
	private JButton btnTirarDado;
	private DecisionButton btnCamino1;
	private DecisionButton btnCamino2;
	private JButton btnEndTurn;
	private JPanelGame gamePanel;
	private InfoPanel characterPanel;
	private JLabel diceImage;
	private DescriptionPanel panelDescrip;
	private Endgame finPartida;
	private JPanelPlayers playersPanel;
	private List<ClientPlayer> clientPlayersList;
	private CompraDolaresFrame compraDolaresFrame;
	private Socket servidor;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					 SOLO PARA TESTEAR
					GameMap mapa1;
					MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("newMap1.txt");
					mapa1 = mapFileCSVReader.buildGameMap();
					ParitariaNode ini = new ParitariaNode(null, new Position(0,0));
					Espert p1 = new Espert(1450, 150, 100);
					Cristina p2 = new Cristina(150, 100, 900);
					Macri p3 = new Macri(500, 100, 100);
					DelCanio p4 = new DelCanio(150, 100, 100);
					p1.setNodeLocation(ini);
					p2.setNodeLocation(ini);
					p3.setNodeLocation(ini);
					p4.setNodeLocation(ini);
					List<Player> listaP = new ArrayList<Player>();
					listaP.add(p1);
					listaP.add(p2);
					listaP.add(p3);
					listaP.add(p4);

					GameInformation gameInformation = new GameInformation(listaP,mapa1,20);

					MainGameScreen frame = new MainGameScreen(gameInformation,null);
					frame.setVisible(true);

					//ChatThread chatThread = new ChatThread();
					//chatThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGameScreen(GameInformation info, Socket servidor) {
		this.servidor = servidor;
		setTitle("Elecciones Presidenciales 2019");
		// Iniciar partida
		clientPlayersList = info.getPlayers();

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
		playersPanel = new JPanelPlayers(clientPlayersList);
		playersPanel.setBounds(1000, 0, 280, 500);
		contentPane.add(playersPanel);

		// Panel para descripcion
		panelDescrip = new DescriptionPanel();
		panelDescrip.setBounds(0, 0, 280, 720);
		contentPane.add(panelDescrip);
		panelDescrip.setVisible(false);

		// Panel para chat (no implementado)
		JPanel chatPanel = new JPanel();
		chatPanel.setBackground(Color.lightGray);
		chatPanel.setBounds(1000, 500, 280, 230);
		contentPane.add(chatPanel);
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

				/** Mando al server que el jugador decidio el camino 1 **/
				//El servidor tendria que armar una ServerResponse1 y llamar a playTurn
				
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

				/** Mando al server que el jugador decidio el camino 2 **/
				//El servidor tendria que armar una ServerResponse1 y llamar a playTurn

			}
		});
		contentPane.add(btnCamino2);
		btnCamino2.setVisible(false);
		btnCamino2.setFocusable(false);

		// Panel del juego
		gamePanel = new JPanelGame(info.getNodes(), info.getPlayers());
		gamePanel.setBackground(SystemColor.text);
		gamePanel.setBounds(0, 0, 1000, 500);
		contentPane.add(gamePanel);
		gamePanel.setLayout(null);

		// Panel para el boton
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(720, 500, 280, 220);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(null);
		buttonPanel.setBackground(new Color(0, 0, 0, 0));

		// Foto Dado
		diceImage = new JLabel();
		diceImage.setBounds(150, 25, 115, 115);
		diceImage.setVisible(false);
		buttonPanel.add(diceImage);

		// Panel para el personaje
		characterPanel = new InfoPanel(info.getPlayers().get(0), 0, info.getPrecioDolar());
		characterPanel.setBounds(0, 500, 1000, 220);
		contentPane.add(characterPanel);

		// TAB
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getExtendedKeyCode();
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
		btnTirarDado = new DiceButton();
		btnTirarDado.setForeground(Color.RED);
		btnTirarDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					PrintStream ps = new PrintStream(servidor.getOutputStream(), true);
					Command tirarDado = new Command("TirarDado", "");
					String send = new Gson().toJson(tirarDado);
					ps.println(send);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				/** Le digo al server que el jugador tiro el dado **/
				//El servidor tendria que armar una ServerResponse1 y llamar a playTurn
				
				btnTirarDado.setVisible(false);
			}
		});
		btnTirarDado.setBounds(0, 25, 120, 120);
		buttonPanel.add(btnTirarDado);
		btnTirarDado.setFocusable(false);

		btnEndTurn = new JButton("TERMINAR");
		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/** Le digo al server que termino el turno **/
				//El servidor tendria que armar una ServerResponse3 y llamar a EndTurnIndeed
				//El servidor tendria que cambiar de Player y habilitarle el boton de TirarDado
			
			}
		});
		btnEndTurn.setVisible(false);
		btnEndTurn.setForeground(Color.RED);
		btnEndTurn.setFocusable(false);
		btnEndTurn.setBounds(0, 25, 120, 120);
		buttonPanel.add(btnEndTurn);

	}
	
	public void playTurnPublic(MovementResponsePublic respuesta) {
		ClientPlayer currentClientPlayer = null;
		for(int i=0;i<clientPlayersList.size();i++) {
			if(respuesta.playerID.equals(clientPlayersList.get(i).getCharacter())) {
				currentClientPlayer = clientPlayersList.get(i);
			}
		}
 		moverJugador(respuesta.diceResult, currentClientPlayer, respuesta.nodePath);
 		playersPanel.updatePanelPlayers(respuesta.currentClientPlayerList);
	}
	public void playTurnPrivate(MovementResponsePrivate respuesta) {
		ClientPlayer currentClientPlayer = null;
		for(int i=0;i<clientPlayersList.size();i++) {
			if(respuesta.playerID.equals(clientPlayersList.get(i).getCharacter())) {
				currentClientPlayer = clientPlayersList.get(i);
			}
		}
 		moverJugador(respuesta.diceResult, currentClientPlayer, respuesta.nodePath);
		if (respuesta.options!=null) {
			tomarDecision(respuesta);
		} else {
			playersPanel.updatePanelPlayers(respuesta.currentClientPlayerList);
			endTurn(respuesta);
		}
	}

	public void endTurn(MovementResponsePrivate respuesta) {
		ClientPlayer currentClientPlayer = null;
		for(int i=0;i<clientPlayersList.size();i++) {
			if(respuesta.playerID.equals(clientPlayersList.get(i).getPlayerName())) {
				currentClientPlayer = clientPlayersList.get(i);
			}
		}
		if(respuesta.compra_dolares) {
			compraDolaresFrame = new CompraDolaresFrame(currentClientPlayer, characterPanel.getPrecioDolar());
			compraDolaresFrame.setVisible(true);
		}
		btnEndTurn.setVisible(true);
	}
	
	public void announceWinner(ClientPlayer ganador) {
		finPartida = new Endgame(ganador.getCharacter());
		finPartida.setVisible(true);
		dispose();
	}

	private void moverJugador(int diceResult, ClientPlayer currentPlayer, Queue<Position> nodePath) {
		diceImage.setVisible(true); //Solo al Player que corresponde
		diceImage.setIcon(new ImageIcon("images/dice/" + diceResult + ".png"));
		Position transitionNode = nodePath.poll();
		int size = nodePath.size();
		for (int i = 0; i < size; i++) {
			transitionNode = nodePath.poll();
			currentPlayer.setPosition(transitionNode);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			contentPane.paintImmediately(0, 0, 1280, 720);
		}
	}
	
	private void tomarDecision(MovementResponsePrivate decision) {
		btnTirarDado.setVisible(false);
		btnCamino1.setBounds(30 + decision.options.get(0).getPosition().getX() * 125,
				30 + decision.options.get(0).getPosition().getY() * 125, 75, 75);
		btnCamino1.actualizarImagen(decision.options.get(0).getType());
		btnCamino2.setBounds(30 + decision.options.get(1).getPosition().getX() * 125,
				30 + decision.options.get(1).getPosition().getY() * 125, 75, 75);
		btnCamino2.actualizarImagen(decision.options.get(1).getType());

		btnCamino1.setVisible(true); //Solo al Player que corresponde
		btnCamino2.setVisible(true); //Solo al Player que corresponde
	}

	public void endTurnIndeed(EndTurnResponse respuesta) {

		characterPanel.actualizar(respuesta.currentPlayer, respuesta.currentTurn, respuesta.currentPrecioDolar);
		diceImage.setVisible(false);

	}

	public void habilitarDado() {
		btnTirarDado.setVisible(true);
	}
	
}