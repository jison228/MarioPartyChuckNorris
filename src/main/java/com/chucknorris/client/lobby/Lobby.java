package com.chucknorris.client.lobby;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.User;
import com.chucknorris.client.ClientInfoSalas;

public class Lobby extends JFrame {

	private JPanel lobbyPane;
	private JPanel salaPane;
	private UserPanel panelUsuarios;
	private RoomPanel panelSalas;

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
					
					List<ClientInfoSalas> salas = new ArrayList<ClientInfoSalas>();
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					salas.add(new ClientInfoSalas("Primer Sala", 3, 4,false));
					salas.add(new ClientInfoSalas("Segunda Sala", 4, 4,true));
					salas.add(new ClientInfoSalas("Tercer Sala", 1, 3,false));
					
					Lobby frame = new Lobby(chabones, salas);
					
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                
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
	public Lobby(List<User> usuarios, List<ClientInfoSalas> salas) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.lobbyPane = new JPanel(new GridLayout(1,2));
		this.setContentPane(lobbyPane);
		
		this.panelUsuarios = new UserPanel(usuarios);
		lobbyPane.add(panelUsuarios);
		
		this.panelSalas = new RoomPanel(salas);
		lobbyPane.add(panelSalas);
		
		
	}

	public void updateLobby(List<User> usuarios, List<ClientInfoSalas> salas) {
		panelUsuarios.updatePanel(usuarios);
		panelSalas.updatePanel(salas);
		lobbyPane.repaint();
	}

}
