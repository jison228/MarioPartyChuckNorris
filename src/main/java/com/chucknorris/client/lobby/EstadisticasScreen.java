package com.chucknorris.client.lobby;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.gui.gameoptions.NewGameScreen;

import java.awt.Font;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EstadisticasScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EstadisticasPanel mainPane;
	private JTextField maxPuntajeTF;
	private JTextField winsTF;
	private JTextField userTF;
	private int wins;
	private int maxMinigame;
	private Jugador player;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EstadisticasScreen frame = new EstadisticasScreen();
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
	public EstadisticasScreen() {
		player = new Jugador();
		List<Partida> listaDePartidas = new ArrayList<Partida>();
		Partida partida1 = new Partida(5788, "Jason", "Pepe", "asd", "3434", "Facu");
		Partida partida2 = new Partida(5788, "asd", "Pepe", "asd", "3434", "Facu");
		Partida partida3 = new Partida(5788, "fe", "Pepe", "asd", "3434", "Facu");
		Partida partida4 = new Partida(5788, "Jason", "Pepe", "asd", "3434", "Facu");
		Partida partida5 = new Partida(5788, "34", "Pepe", "asd", "3434", "Facu");
		listaDePartidas.add(partida1);
		listaDePartidas.add(partida2);
		listaDePartidas.add(partida3);
		listaDePartidas.add(partida4);
		listaDePartidas.add(partida5);
		this.player.setUsername("Jason");
		// JFrame config
		setTitle("Mis estadísticas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setResizable(false);

		// mainPane config
		mainPane = new EstadisticasPanel("estadisticas.jpg");
		mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		// Botones
		int btnW = 400;
		int btnH = 80;

		JButton btnOpciones = new JButton("Volver");
		btnOpciones.setFont(new Font("Monospaced", Font.BOLD, 25));
		btnOpciones.setFocusable(false);
		btnOpciones.setBounds(984, 569, 254, 60);
		mainPane.add(btnOpciones);

		JLabel lblCantidadDePartidas = new JLabel("Cantidad de");
		lblCantidadDePartidas.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblCantidadDePartidas.setBounds(747, 250, 176, 68);
		mainPane.add(lblCantidadDePartidas);

		JLabel lblPartidasGanadas = new JLabel("partidas ganadas:");
		lblPartidasGanadas.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblPartidasGanadas.setBounds(705, 279, 255, 68);
		mainPane.add(lblPartidasGanadas);

		JLabel lblMaximoPuntaje = new JLabel("Maximo puntaje");
		lblMaximoPuntaje.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblMaximoPuntaje.setBounds(720, 386, 225, 68);
		mainPane.add(lblMaximoPuntaje);

		JLabel lblDeMinijuego = new JLabel("de minijuego:");
		lblDeMinijuego.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblDeMinijuego.setBounds(735, 415, 225, 68);
		mainPane.add(lblDeMinijuego);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblUsuario.setBounds(771, 137, 128, 68);
		mainPane.add(lblUsuario);

		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.anadirPartida(listaDePartidas, player.getUsername());
				mainPane.repaint();
				userTF.setText(player.getUsername());
				winsTF.setText(String.valueOf(player.getWins()));
				maxPuntajeTF.setText(String.valueOf(player.getMaxminigame()));
			}
		});
		btnRefrescar.setFont(new Font("Monospaced", Font.BOLD, 25));
		btnRefrescar.setFocusable(false);
		btnRefrescar.setBounds(705, 569, 254, 60);
		mainPane.add(btnRefrescar);

		maxPuntajeTF = new JTextField();
		maxPuntajeTF.setHorizontalAlignment(SwingConstants.CENTER);
		maxPuntajeTF.setFont(new Font("Monospaced", Font.PLAIN, 25));
		maxPuntajeTF.setEditable(false);
		maxPuntajeTF.setBounds(984, 413, 254, 60);
		mainPane.add(maxPuntajeTF);
		maxPuntajeTF.setColumns(10);

		winsTF = new JTextField();
		winsTF.setHorizontalAlignment(SwingConstants.CENTER);
		winsTF.setFont(new Font("Monospaced", Font.PLAIN, 25));
		winsTF.setEditable(false);
		winsTF.setColumns(10);
		winsTF.setBounds(984, 279, 254, 60);
		mainPane.add(winsTF);

		userTF = new JTextField();
		userTF.setHorizontalAlignment(SwingConstants.CENTER);
		userTF.setFont(new Font("Monospaced", Font.PLAIN, 25));
		userTF.setEditable(false);
		userTF.setColumns(10);
		userTF.setBounds(984, 141, 254, 60);
		mainPane.add(userTF);

		JLabel lblPartida = new JLabel("Partida");
		lblPartida.setHorizontalAlignment(SwingConstants.CENTER);
		lblPartida.setBounds(71, 189, 56, 16);
		mainPane.add(lblPartida);

		JLabel lblJugador = new JLabel("Jugador 1");
		lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugador.setBounds(185, 189, 70, 16);
		mainPane.add(lblJugador);

		JLabel lblJugador_1 = new JLabel("Jugador 2");
		lblJugador_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugador_1.setBounds(267, 189, 67, 16);
		mainPane.add(lblJugador_1);

		JLabel lblJugador_2 = new JLabel("Jugador 3");
		lblJugador_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugador_2.setBounds(346, 189, 70, 16);
		mainPane.add(lblJugador_2);

		JLabel lblJugador_3 = new JLabel("Jugador 4");
		lblJugador_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugador_3.setBounds(428, 189, 70, 16);
		mainPane.add(lblJugador_3);

		JLabel lblGanador = new JLabel("Ganador");
		lblGanador.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanador.setBounds(554, 189, 56, 16);
		mainPane.add(lblGanador);

		JLabel lblHistorial = new JLabel("Historial");
		lblHistorial.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblHistorial.setBounds(269, 137, 147, 68);
		mainPane.add(lblHistorial);

	}
}
