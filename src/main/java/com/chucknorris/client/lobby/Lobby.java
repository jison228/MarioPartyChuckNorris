package com.chucknorris.client.lobby;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<User> chabones = new ArrayList<User>();
					chabones.add(new User("Primer Man"));
					chabones.add(new User("Peronista"));
					Lobby frame = new Lobby(chabones, null);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 720);
		lobbyPane = new JPanel();
		lobbyPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		lobbyPane.setLayout(null);
		setContentPane(lobbyPane);
		panelUsuarios = new UserPanel(usuarios);
		panelUsuarios.setBounds(0,0,700,720);
		lobbyPane.add(panelUsuarios);
	}
	
	public void updateLobby(List<User> usuarios, List<ClientInfoSalas> salas) {
		panelUsuarios.updatePanel(usuarios);
		lobbyPane.repaint();
	}

}
