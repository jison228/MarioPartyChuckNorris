package com.chucknorris.client.sala;

import java.awt.EventQueue;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.User;

public class SalaFrame extends JFrame {

	private JPanel contentPane;
	private String playerID;
	private ClientRealSala sala;
	private SalaPlayersPanel salaPlayersPanel;
	private SalaSpectatorsPanel salaSpectatorsPanel;
	private Socket servidor;
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
					
					SalaFrame frame = new SalaFrame("Roberto", new ClientRealSala("Nombre", players, spectators),null);
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
		salaPlayersPanel.setBounds(550,0,450,510);
		contentPane.add(salaPlayersPanel);
		
		salaSpectatorsPanel = new SalaSpectatorsPanel(sala.spectators);
		salaSpectatorsPanel.setBounds(0, 510, 500, 490);
		contentPane.add(salaSpectatorsPanel);
	}
	
	public void updateSalaFrame(ClientRealSala sala) {
		salaPlayersPanel.updatePanel(sala.players);
		salaSpectatorsPanel.updatePanel(sala.spectators);
	}

}
