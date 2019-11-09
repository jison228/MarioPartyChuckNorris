package com.chucknorris.client.lobby;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.server.ClientThread;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Lobby extends JFrame {

	private JPanel contentPane;
	private List<ClientThread> listaCLientes;
	JLabel primerTF;
	JLabel segundoTF;
	JLabel terceroTF;
	JLabel cuartoTF;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lobby frame = new Lobby(null);
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
	public Lobby(List<ClientThread> listaClientes) {
		this.listaCLientes = listaClientes;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		primerTF = new JLabel("Wating player1");
		primerTF.setBounds(42, 28, 114, 33);
		contentPane.add(primerTF);
		
		segundoTF = new JLabel("Wating player2");
		segundoTF.setBounds(42, 98, 114, 33);
		contentPane.add(segundoTF);
		
		terceroTF = new JLabel("Wating player3");
		terceroTF.setBounds(42, 164, 114, 33);
		contentPane.add(terceroTF);
		
		cuartoTF = new JLabel("Wating player4");
		cuartoTF.setBounds(42, 220, 114, 33);
		contentPane.add(cuartoTF);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(302, 164, 124, 70);
		contentPane.add(btnJugar);
	}
	
	public void updateListClientes(List<ClientThread> listaCLientes) {
		this.listaCLientes = listaCLientes;
		if(listaCLientes.get(0)!=null) {
			primerTF.setText("Jugador1 esta listo");
		}
		if(listaCLientes.get(1)!=null) {
			primerTF.setText("Jugador2 esta listo");
		}
		if(listaCLientes.get(2)!=null) {
			primerTF.setText("Jugador3 esta listo");
		}
		if(listaCLientes.get(3)!=null) {
			primerTF.setText("Jugador4 esta listo");
		}
	}
}
