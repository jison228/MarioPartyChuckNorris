package com.chucknorris.client.sala;

import java.awt.EventQueue;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.chucknorris.Command;
import com.chucknorris.User;
import com.chucknorris.client.ChatResponse;
import com.chucknorris.client.GameParametersResponse;
import com.google.gson.Gson;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.TextArea;

public class SalaFrame extends JFrame {

	private JPanel contentPane;
	private String playerID;
	private SalaPlayersPanel salaPlayersPanel;
	private SalaSpectatorsPanel salaSpectatorsPanel;
	private Socket servidor;
	private Gson gson;
	private PrintStream ps;
	private JComboBox<Double> comboBoxPrecioDolar;
	private JComboBox<Double> comboBoxDolares;
	private JComboBox<String> comboBoxMap;
	private JButton btnCambiar;
	private JButton btnJugar;
	private JComboBox<Integer> comboBoxMin;
	private JComboBox<Integer> comboBoxMax;
	private JComboBox<Double> comboBoxSalario;
	private JComboBox<Double> comboBoxPesos;
	private TextArea chatTA;
	private JTextField chatTF;
	private JButton chatBtn;
	private MapImagePanel lblImagenDelMapa;
	private boolean update;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<User> players = new ArrayList<User>();
					players.add(new User("Roberto", 5, 10));
					players.add(new User("Martin", 6, 50));
					players.add(new User("Mauricio", 0, 0));

					List<User> spectators = new ArrayList<User>();
					spectators.add(new User("Robert", 5, 10));
					spectators.add(new User("Marti", 6, 50));
					spectators.add(new User("Maurici", 0, 0));
					spectators.add(new User("Robert", 5, 10));
					spectators.add(new User("Marti", 6, 50));
					spectators.add(new User("Maurici", 0, 0));

					SalaFrame frame = new SalaFrame("Roberto", new ClientRealSala("Nombre", players, spectators), null);
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
	public SalaFrame(String playerID, ClientRealSala sala, Socket servidor) {
		gson = new Gson();
		update = true;
		this.playerID = playerID;
		try {
			if (servidor != null)
				ps = new PrintStream(servidor.getOutputStream(), true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.servidor = servidor;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 200, 1000, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		salaPlayersPanel = new SalaPlayersPanel(sala.players);
		salaPlayersPanel.setBounds(550, 0, 450, 510);
		contentPane.add(salaPlayersPanel);

		salaSpectatorsPanel = new SalaSpectatorsPanel(sala.spectators);
		salaSpectatorsPanel.setBounds(0, 510, 550, 490);
		contentPane.add(salaSpectatorsPanel);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(0, 0, 550, 510);
		contentPane.add(optionsPanel);
		optionsPanel.setLayout(null);

		JLabel lblNombreSala = new JLabel("NOMBRE SALA");
		lblNombreSala.setText(sala.name);
		lblNombreSala.setFont(new Font("Georgia", Font.BOLD, 30));
		lblNombreSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSala.setBounds(10, 10, 530, 39);
		optionsPanel.add(lblNombreSala);

		JButton btnExitSala = new JButton("Exit Sala");
		btnExitSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ps.println(gson.toJson(new Command("LeaveSala", "")));
			}
		});
		btnExitSala.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExitSala.setBounds(10, 461, 115, 39);
		optionsPanel.add(btnExitSala);

		btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameParametersResponse parametros = new GameParametersResponse((String) comboBoxMap.getSelectedItem(),
						(Double) comboBoxPrecioDolar.getSelectedItem(), (Double) comboBoxPesos.getSelectedItem(),
						(Double) comboBoxDolares.getSelectedItem(), (Double) comboBoxSalario.getSelectedItem(),
						(Integer) comboBoxMin.getSelectedItem(), (Integer) comboBoxMax.getSelectedItem());
				ps.println(gson.toJson(new Command("StartGame", gson.toJson(parametros))));
			}
		});
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnJugar.setBounds(425, 461, 115, 39);
		optionsPanel.add(btnJugar);

		btnCambiar = new JButton("");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnCambiar.getText().equals("Cambiar a Espectador")) {
					ps.println(gson.toJson(new Command("SwitchFromPlayerToSpectator", "")));
				} else {
					ps.println(gson.toJson(new Command("SwitchFromSpectatorToPlayer", "")));
				}
			}
		});
		btnCambiar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCambiar.setBounds(135, 461, 280, 39);
		optionsPanel.add(btnCambiar);

		comboBoxMap = new JComboBox<String>();

		comboBoxMap.setBackground(new Color(255, 250, 205));
		comboBoxMap.setBounds(340, 127, 200, 21);
		optionsPanel.add(comboBoxMap);
		comboBoxMap.addItem("newMap1");
		comboBoxMap.addItem("newMap2");

		JLabel lblMapa = new JLabel("Mapa");
		lblMapa.setForeground(Color.BLUE);
		lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
		lblMapa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMapa.setBounds(340, 92, 200, 25);
		optionsPanel.add(lblMapa);

		lblImagenDelMapa = new MapImagePanel(comboBoxMap.getSelectedItem().toString() + ".jpg");
		lblImagenDelMapa.setBounds(240, 158, 300, 150);
		optionsPanel.add(lblImagenDelMapa);

		JLabel lblDado = new JLabel("Dado");
		lblDado.setForeground(Color.RED);
		lblDado.setHorizontalAlignment(SwingConstants.CENTER);
		lblDado.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDado.setBounds(340, 318, 200, 25);
		optionsPanel.add(lblDado);

		JLabel lblMin = new JLabel("Min");
		lblMin.setForeground(Color.MAGENTA);
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMin.setBounds(340, 353, 75, 21);
		optionsPanel.add(lblMin);

		JLabel lblMax = new JLabel("Max");
		lblMax.setForeground(Color.MAGENTA);
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMax.setBounds(465, 353, 75, 21);
		optionsPanel.add(lblMax);

		comboBoxMin = new JComboBox<Integer>();

		comboBoxMin.setBackground(new Color(255, 250, 205));
		comboBoxMin.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxMin.setBounds(340, 384, 75, 21);
		optionsPanel.add(comboBoxMin);
		comboBoxMin.addItem(1);
		comboBoxMin.addItem(2);
		comboBoxMin.addItem(3);
		comboBoxMin.addItem(4);
		comboBoxMin.addItem(5);
		comboBoxMin.addItem(6);

		comboBoxMax = new JComboBox<Integer>();

		comboBoxMax.setBackground(new Color(255, 250, 205));
		comboBoxMax.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxMax.setBounds(465, 384, 75, 21);
		optionsPanel.add(comboBoxMax);
		comboBoxMax.addItem(6);
		comboBoxMax.addItem(7);
		comboBoxMax.addItem(8);
		comboBoxMax.addItem(9);

		JLabel lblSalarioInicial = new JLabel("Salario Inicial");
		lblSalarioInicial.setForeground(Color.GREEN);
		lblSalarioInicial.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalarioInicial.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalarioInicial.setBounds(10, 351, 146, 25);
		optionsPanel.add(lblSalarioInicial);

		comboBoxSalario = new JComboBox<Double>();

		comboBoxSalario.setBackground(new Color(255, 250, 205));
		comboBoxSalario.setBounds(10, 387, 146, 21);
		optionsPanel.add(comboBoxSalario);
		comboBoxSalario.addItem(300.0);
		comboBoxSalario.addItem(200.0);
		comboBoxSalario.addItem(150.0);
		comboBoxSalario.addItem(100.0);
		comboBoxSalario.addItem(50.0);

		JLabel lblPesosIniciales = new JLabel("Pesos Iniciales\r\n");
		lblPesosIniciales.setForeground(Color.GREEN);
		lblPesosIniciales.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesosIniciales.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPesosIniciales.setBounds(10, 92, 146, 25);
		optionsPanel.add(lblPesosIniciales);

		comboBoxPesos = new JComboBox<Double>();

		comboBoxPesos.setBackground(new Color(255, 250, 205));
		comboBoxPesos.setBounds(10, 128, 146, 21);
		optionsPanel.add(comboBoxPesos);
		comboBoxPesos.addItem(150.0);
		comboBoxPesos.addItem(200.0);
		comboBoxPesos.addItem(250.0);
		comboBoxPesos.addItem(300.0);

		JLabel lblDolaresIniciales = new JLabel("Dolares Iniciales\r\n");
		lblDolaresIniciales.setForeground(Color.GREEN);
		lblDolaresIniciales.setHorizontalAlignment(SwingConstants.CENTER);
		lblDolaresIniciales.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDolaresIniciales.setBounds(10, 223, 155, 25);
		optionsPanel.add(lblDolaresIniciales);

		comboBoxDolares = new JComboBox<Double>();

		comboBoxDolares.setBackground(new Color(255, 250, 205));
		comboBoxDolares.setBounds(10, 259, 146, 21);
		optionsPanel.add(comboBoxDolares);
		comboBoxDolares.addItem(100.0);
		comboBoxDolares.addItem(50.0);
		comboBoxDolares.addItem(0.0);

		JLabel lblPrecioDolar = new JLabel("Precio Dolar");
		lblPrecioDolar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioDolar.setForeground(Color.GREEN);
		lblPrecioDolar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrecioDolar.setBounds(177, 349, 146, 25);
		optionsPanel.add(lblPrecioDolar);

		comboBoxPrecioDolar = new JComboBox<Double>();

		comboBoxPrecioDolar.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxPrecioDolar.setBackground(new Color(255, 250, 205));
		comboBoxPrecioDolar.setBounds(210, 387, 75, 21);
		optionsPanel.add(comboBoxPrecioDolar);
		comboBoxPrecioDolar.addItem(20.0);
		comboBoxPrecioDolar.addItem(30.0);
		comboBoxPrecioDolar.addItem(40.0);

		// CHAT
		// TextArea
		chatTA = new TextArea();
		chatTA.setBounds(560, 520, 420, 110);
		chatTA.setEditable(false);
		chatTA.setFocusable(false);
		contentPane.add(chatTA);
		// TextField
		chatTF = new JTextField();
		chatTF.setBounds(560, 640, 340, 25);
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
					Command commandChat = new Command("SalaChat", chatTF.getText());
					String gsonCommand = gson.toJson(commandChat);
					ps.println(gsonCommand);
					chatTF.setText("");
				}

			}
		});
		contentPane.add(chatTF);
		// Jbutton
		chatBtn = new JButton();
		chatBtn.setText("Enviar");
		chatBtn.setBounds(910, 640, 70, 25);
		chatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintStream ps = null;
				try {
					ps = new PrintStream(servidor.getOutputStream(), true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Command commandChat = new Command("SalaChat", chatTF.getText());
				String gsonCommand = gson.toJson(commandChat);
				ps.println(gsonCommand);
				chatTF.setText("");
			}
		});
		contentPane.add(chatBtn);

		comboBoxMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendOptions();
			}
		});
		comboBoxPrecioDolar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendOptions();
			}
		});
		comboBoxDolares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendOptions();
			}
		});
		comboBoxPesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendOptions();
			}
		});
		comboBoxSalario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendOptions();
			}
		});
		comboBoxMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendOptions();
			}
		});
		comboBoxMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendOptions();
			}
		});

		updateBotones(sala);
	}

	public void updateSalaFrame(ClientRealSala sala) {
		salaPlayersPanel.updatePanel(sala.players);
		salaSpectatorsPanel.updatePanel(sala.spectators);
		updateBotones(sala);
		contentPane.repaint();
	}

	private void updateBotones(ClientRealSala sala) {

		btnJugar.setEnabled(false);
		if (sala.players.size() == 4) {
			btnJugar.setEnabled(true);
		} else {
			btnJugar.setEnabled(false);
		}

		if (sala.players.size() == 0 || !sala.players.get(0).getPlayerID().equals(this.playerID)) {
			comboBoxDolares.setEnabled(false);
			comboBoxMap.setEnabled(false);
			comboBoxMax.setEnabled(false);
			comboBoxMin.setEnabled(false);
			comboBoxPesos.setEnabled(false);
			comboBoxPrecioDolar.setEnabled(false);
			comboBoxSalario.setEnabled(false);
			btnJugar.setEnabled(false);
		} else {
			comboBoxDolares.setEnabled(true);
			comboBoxMap.setEnabled(true);
			comboBoxMax.setEnabled(true);
			comboBoxMin.setEnabled(true);
			comboBoxPesos.setEnabled(true);
			comboBoxPrecioDolar.setEnabled(true);
			comboBoxSalario.setEnabled(true);
		}

		boolean spec = false;
		for (int i = 0; i < sala.spectators.size(); i++) {
			if (sala.spectators.get(i).getPlayerID().equals(this.playerID)) {
				spec = true;
			}
		}
		if (spec) {
			btnCambiar.setText("Cambiar a Jugador");
			if (sala.players.size() == 4) {
				btnCambiar.setEnabled(false);
			} else {
				btnCambiar.setEnabled(true);
			}
		} else {
			btnCambiar.setEnabled(true);
			btnCambiar.setText("Cambiar a Espectador");
		}
	}

	public void addChatText(String mensaje) {
		chatTA.append(
				new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())) + " " + mensaje + "\n");
	}

	public void updateOptions(SalaParametersResponse opciones) {
		update = false;
		this.comboBoxMap.setSelectedItem(opciones.mapName);
		this.comboBoxPesos.setSelectedItem(opciones.pesosIniciales);
		this.comboBoxDolares.setSelectedItem(opciones.dolaresIniciales);
		this.comboBoxPrecioDolar.setSelectedItem(opciones.precioDolar);
		this.comboBoxMin.setSelectedItem(opciones.diceMin);
		this.comboBoxMax.setSelectedItem(opciones.diceMax);

		this.lblImagenDelMapa.updateImage(comboBoxMap.getSelectedItem().toString() + ".jpg");
		contentPane.repaint();
		update = true;
	}

	private void sendOptions() {
		if (update) {
			SalaParametersResponse spr = new SalaParametersResponse((String) comboBoxMap.getSelectedItem(),
					(Double) comboBoxPesos.getSelectedItem(), (Double) comboBoxDolares.getSelectedItem(),
					(Double) comboBoxSalario.getSelectedItem(), (Double) comboBoxPrecioDolar.getSelectedItem(),
					(Integer) comboBoxMin.getSelectedItem(), (Integer) comboBoxMax.getSelectedItem());
			PrintStream ps = null;
			try {
				ps = new PrintStream(servidor.getOutputStream(), true);
				ps.println(gson.toJson(new Command("UpdateOpciones", gson.toJson(spr))));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
