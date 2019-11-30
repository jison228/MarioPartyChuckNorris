package com.chucknorris.client.titulo;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.commons.Dice;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;
import com.chucknorris.singlePlayer.gui.tablero.MainGameScreen;

public class TitleScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TitleScreen frame = new TitleScreen();
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
	public TitleScreen() {
		// JFrame config
		setTitle("ELECCIONES PRESIDENCIALES 2019");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 1280, 720);
		setResizable(false);

		// mainPane config
		mainPane = new TitleImagePanel("portada.jpg");
		mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		// Botones
		int btnW = 400;
		int btnH = 80;

		// Nueva Partida
		JButton btnNewGame = new JButton("Iniciar Sesion");
		btnNewGame.setFont(new Font("Courier", Font.BOLD, 25));
		btnNewGame.setFocusable(false);
		btnNewGame.setBounds((getWidth() / 2) - btnW / 2, getHeight() - (int) (getHeight() * 0.60), btnW, btnH);
		mainPane.add(btnNewGame);

		// Salir
		JButton btnQuit = new JButton("Irse del pais");
		btnQuit.setFont(new Font("Courier", Font.BOLD, 25));
		btnQuit.setFocusable(false);
		btnQuit.setBounds(840, 541, btnW,
				btnH);
		mainPane.add(btnQuit);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Monospaced", Font.BOLD, 25));
		btnRegistrarse.setFocusable(false);
		btnRegistrarse.setBounds(440, 411, 400, 80);
		mainPane.add(btnRegistrarse);
		
		JButton btnJugarOffline = new JButton("Jugar Offline");
		btnJugarOffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameMap mapa1 = null;
				MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("newMap1.txt");
				try {
					mapa1 = mapFileCSVReader.buildGameMap();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Espert p1 = new Espert(150, 150, 100);
				Cristina p2 = new Cristina(150, 100, 900);
				Macri p3 = new Macri(150, 100, 100);
				DelCanio p4 = new DelCanio(150, 100, 100);
				List<Player> listaP = new ArrayList<Player>();
				listaP.add(p1);
				listaP.add(p2);
				listaP.add(p3);
				listaP.add(p4);
				GameInformation test = new GameInformation(listaP, mapa1, new Dice(1, 6), 20);

				MainGameScreen frame = new MainGameScreen(test,"newMap1");
				frame.setVisible(true);
			}
		});
		btnJugarOffline.setFont(new Font("Monospaced", Font.BOLD, 25));
		btnJugarOffline.setFocusable(false);
		btnJugarOffline.setBounds(39, 541, 400, 80);
		mainPane.add(btnJugarOffline);

		// Acciones de los botones
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new LogInScreen().setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
