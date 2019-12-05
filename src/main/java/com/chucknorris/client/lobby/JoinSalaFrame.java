package com.chucknorris.client.lobby;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.charset.CoderMalfunctionError;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.chucknorris.Command;
import com.chucknorris.server.SalaResponse;
import com.google.gson.Gson;

public class JoinSalaFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldContrasenia;
	private Socket mySocket;
	private Gson gson = new Gson();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinSalaFrame frame = new JoinSalaFrame("Homero",null);
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
	public JoinSalaFrame(String nombreSala, Socket mySocket) {
		this.mySocket = mySocket;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUnirseASala = new JLabel("Unirse A Sala: " + nombreSala);
		lblUnirseASala.setBounds(5, 5, 426, 34);
		lblUnirseASala.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		contentPane.add(lblUnirseASala);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		lblContrasenia.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
		lblContrasenia.setBounds(5, 64, 194, 34);
		contentPane.add(lblContrasenia);
		
		textFieldContrasenia = new JTextField();
		textFieldContrasenia.setBounds(209, 64, 194, 34);
		contentPane.add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream ps = new PrintStream(mySocket.getOutputStream());
					SalaResponse sr = new SalaResponse(nombreSala, textFieldContrasenia.getText(), true);
					Command unirseSala = new Command("JoinSala", gson.toJson(sr));
					ps.println(gson.toJson(unirseSala));
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAceptar.setBounds(72, 129, 100, 34);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(276, 129, 100, 34);
		contentPane.add(btnCancelar);
		setLocationRelativeTo(null);
	}

}
