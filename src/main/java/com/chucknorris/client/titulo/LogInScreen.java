package com.chucknorris.client.titulo;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.chucknorris.client.GameInformation;
import com.chucknorris.client.ServerThread;
import com.chucknorris.client.tablero.MainGameScreen;
import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gamemap.nodes.ParitariaNode;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;

public class LogInScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nickTF;
	private JTextField ipTF;
	private JTextField portTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInScreen frame = new LogInScreen();
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
	public LogInScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar Sesi\u00F3n");
		lblIniciarSesin.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesin.setFont(new Font("Rockwell", Font.BOLD, 32));
		lblIniciarSesin.setBounds(101, 10, 244, 59);
		contentPane.add(lblIniciarSesin);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InetAddress ip = null;
				try {
					ip = InetAddress.getByName(ipTF.getText());
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Socket serverSocket = null;
				try {
					serverSocket = new Socket(ip, Integer.valueOf(portTF.getText()));
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ServerThread escuchador = new ServerThread(serverSocket);
				escuchador.start();
			}
		});
		btnEntrar.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnEntrar.setBounds(101, 209, 101, 33);
		contentPane.add(btnEntrar);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNick.setBounds(68, 79, 108, 33);
		contentPane.add(lblNick);
		
		JLabel lblContrasea = new JLabel("IP servidor:");
		lblContrasea.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblContrasea.setBounds(68, 122, 108, 33);
		contentPane.add(lblContrasea);
		
		nickTF = new JTextField();
		nickTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		nickTF.setBounds(204, 79, 114, 28);
		contentPane.add(nickTF);
		nickTF.setColumns(10);
		
		ipTF = new JTextField();
		ipTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		ipTF.setColumns(10);
		ipTF.setBounds(204, 122, 114, 28);
		contentPane.add(ipTF);
		
		JButton btnRegistrase = new JButton("Registrar");
		btnRegistrase.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnRegistrase.setBounds(244, 209, 123, 33);
		contentPane.add(btnRegistrase);
		
		JLabel portLbl = new JLabel("Puerto:");
		portLbl.setFont(new Font("Rockwell", Font.PLAIN, 18));
		portLbl.setBounds(68, 165, 108, 33);
		contentPane.add(portLbl);
		
		portTF = new JTextField();
		portTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		portTF.setColumns(10);
		portTF.setBounds(204, 167, 114, 28);
		contentPane.add(portTF);
	}
}
