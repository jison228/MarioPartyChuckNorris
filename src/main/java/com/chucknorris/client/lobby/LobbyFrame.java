package com.chucknorris.client.lobby;

import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.chucknorris.Command;
import com.chucknorris.User;
import com.chucknorris.client.ClientLobbySala;
import com.chucknorris.server.SalaResponse;
import com.google.gson.Gson;

public class LobbyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel lobbyPane;
	private UsersPanel panelUsuarios;
	private SalasPanel panelSalas;
	private TextArea chatTA;
	private JTextField chatTF;
	private JButton chatBtn;
	private JButton crearSalaBtn;
	private String playerID;
	private JButton salirJuego;
	private NewSalaFramePrivate nuevasala;
	private JPanel panelDeBotones;
	private PrintStream ps;
	private Gson gson;
	private Socket servidor;
	private JButton btnCrearSalaPublica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					List<User> chabones = new ArrayList<User>();
					chabones.add(new User("Primer Man", 5, 600));
					chabones.add(new User("Peronista", 9, 1200));
					chabones.add(new User("Primer Man", 5, 600));
					chabones.add(new User("Peronista", 9, 1200));
					chabones.add(new User("Primer Man", 5, 600));
					chabones.add(new User("Peronista", 9, 1200));
					chabones.add(new User("Primer Man", 5, 600));
					chabones.add(new User("Peronista", 9, 1200));
					chabones.add(new User("Primer Man", 5, 600));
					chabones.add(new User("Peronista", 9, 1200));
					chabones.add(new User("Primer Man", 5, 600));
					chabones.add(new User("Peronista", 9, 1200));
					chabones.add(new User("Primer Man", 5, 600));
					chabones.add(new User("Peronista", 9, 1200));

					List<ClientLobbySala> salas = new ArrayList<ClientLobbySala>();
					salas.add(new ClientLobbySala("Primer Sala", 4, 4, false, true));
					salas.add(new ClientLobbySala("Segunda Sala", 4, 4, true, true));
					salas.add(new ClientLobbySala("Tercer Sala", 1, 3, false, false));
					salas.add(new ClientLobbySala("Primer Sala", 3, 4, false, false));

					LobbyFrame frame = new LobbyFrame("Roberto", chabones, salas, null);

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
	public LobbyFrame(String playerID, List<User> usuarios, List<ClientLobbySala> salas, Socket servidor) {
		if (servidor != null) {
			try {
				ps = new PrintStream(servidor.getOutputStream(), true);
				this.servidor = servidor;
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		gson = new Gson();
		this.playerID = playerID;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 200, 1000, 720);
		setLocationRelativeTo(null);
		this.lobbyPane = new JPanel(null);
		this.lobbyPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(lobbyPane);

		panelDeBotones = new JPanel();
		panelDeBotones.setLayout(null);
		panelDeBotones.setBounds(900, 0, 100, 380);
		panelDeBotones = new JPanel();
		panelDeBotones.setLayout(null);
		panelDeBotones.setBounds(900, 0, 100, 380);
		for (int i = 0; i < salas.size(); i++) {
			ClientLobbySala salita = salas.get(i);
			String specOrPlay = salita.cantPlayers == 4 ? "Spec" : "Play";
			JButton enter = new JButton(specOrPlay);
			enter.setBounds(0, 10 + i * 70, 90, 68);
			enter.setFocusable(true);
			enter.transferFocus();
			if (salita.playing) {
				enter.setEnabled(false);
			}
			enter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (salita.priv) {
						new JoinSalaFrame(salita.name, servidor).setVisible(true);
					}
					else
						ps.println(gson
								.toJson(new Command("JoinSala", gson.toJson(new SalaResponse(salita.name, "NoPassword", true)))));
				}
			});
			panelDeBotones.add(enter);
		}
		lobbyPane.add(panelDeBotones);

		this.panelUsuarios = new UsersPanel(usuarios);
		panelUsuarios.setBounds(0, 0, 500, 720);
		lobbyPane.add(panelUsuarios);
		panelUsuarios.setLayout(null);

		this.panelSalas = new SalasPanel(salas);
		this.panelSalas.setBounds(500, 0, 400, 380);
		lobbyPane.add(panelSalas);

		// CHAT
		// TextArea
		chatTA = new TextArea();
		chatTA.setBounds(500, 450, 480, 180);
		chatTA.setEditable(false);
		chatTA.setFocusable(false);
		lobbyPane.add(chatTA);
		// TextField
		chatTF = new JTextField();
		chatTF.setBounds(500, 645, 390, 30);
		chatTF.setHorizontalAlignment(SwingConstants.RIGHT);
		chatTF.setText("Escriba aqui su mensaje");
		chatTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (chatTF.getText().equals("Escriba aqui su mensaje")) {
					chatTF.setText("");
				}
			}
		});
		chatTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getExtendedKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					PrintStream ps = null;
					try {
						ps = new PrintStream(servidor.getOutputStream(), true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Command commandChat = new Command("ChatLobby", chatTF.getText());
					String gsonCommand = gson.toJson(commandChat);
					ps.println(gsonCommand);
					chatTF.setText("");
				}

			}
		});
		lobbyPane.add(chatTF);
		// Jbutton
		chatBtn = new JButton();
		chatBtn.setText("Enviar");
		chatBtn.setBounds(900, 645, 80, 30);
		chatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintStream ps = null;
				try {
					ps = new PrintStream(servidor.getOutputStream(), true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Command commandChat = new Command("ChatLobby", chatTF.getText());
				String gsonCommand = gson.toJson(commandChat);
				ps.println(gsonCommand);
				chatTF.setText("");
			}
		});
		lobbyPane.add(chatBtn);

		// CrearSala
		crearSalaBtn = new JButton("Crear Sala Privada");
		crearSalaBtn.setBounds(510, 390, 156, 50);
		crearSalaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevasala = new NewSalaFramePrivate(servidor);
				nuevasala.setVisible(true);
			}
		});
		lobbyPane.add(crearSalaBtn);

		// Salir Sala
		salirJuego = new JButton("Salir Juego");
		salirJuego.setBounds(852, 390, 128, 50);
		salirJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		lobbyPane.add(salirJuego);

		btnCrearSalaPublica = new JButton("Crear Sala Publica");
		btnCrearSalaPublica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewSalaFramePublic(servidor).setVisible(true);
			}
		});
		btnCrearSalaPublica.setBounds(686, 390, 156, 50);
		lobbyPane.add(btnCrearSalaPublica);

	}

	public void updateLobby(List<User> usuarios, List<ClientLobbySala> salas) {
		panelDeBotones = new JPanel();
		panelDeBotones.setLayout(null);
		panelDeBotones.setBounds(900, 0, 100, 380);
		for (int i = 0; i < salas.size(); i++) {
			ClientLobbySala salita = salas.get(i);
			String specOrPlay = salas.get(i).cantPlayers == 4 ? "Spec" : "Play";
			JButton enter = new JButton(specOrPlay);
			enter.setBounds(0, 10 + i * 70, 90, 68);
			enter.setFocusable(true);
			enter.transferFocus();
			if (salita.playing) {
				enter.setEnabled(false);
			}
			String nombreSala = salas.get(i).name;
			enter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (salita.priv) {
						new JoinSalaFrame(nombreSala, servidor).setVisible(true);
					}
					else
						ps.println(gson
								.toJson(new Command("JoinSala", gson.toJson(new SalaResponse(nombreSala, "", true)))));
				}
			});
			panelDeBotones.add(enter);
		}
		lobbyPane.add(panelDeBotones);

		panelUsuarios.updatePanel(usuarios);
		panelSalas.updatePanel(salas);
		lobbyPane.paintImmediately(lobbyPane.getBounds());
		panelDeBotones.repaint();
	}

	public void addChatText(String mensaje) {
		chatTA.append(
				new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())) + " " + mensaje + "\n");
	}
}
