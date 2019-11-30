package com.chucknorris.client.titulo;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.chucknorris.client.ServerLobbyThread;
import com.chucknorris.server.NamePasswordResponse;
import com.google.gson.Gson;

public class LogInScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nickTF;
	private JTextField ipTF;
	private JTextField passwordTF;
	private Gson gson = new Gson();

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
		setBounds(100, 100, 450, 301);
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
					serverSocket = new Socket(ip, 22222);
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ServerLobbyThread escuchador = new ServerLobbyThread(serverSocket,nickTF.getText());
				escuchador.start();
				
				PrintStream ps;
				try {
					ps = new PrintStream(serverSocket.getOutputStream(), true);
					ps.println(gson.toJson(new NamePasswordResponse(nickTF.getText(), passwordTF.getText())));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEntrar.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnEntrar.setBounds(101, 209, 101, 33);
		contentPane.add(btnEntrar);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNick.setBounds(68, 122, 108, 33);
		contentPane.add(lblNick);
		
		JLabel lblContrasea = new JLabel("IP servidor:");
		lblContrasea.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblContrasea.setBounds(68, 79, 108, 33);
		contentPane.add(lblContrasea);
		
		nickTF = new JTextField();
		nickTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		nickTF.setBounds(204, 122, 114, 28);
		contentPane.add(nickTF);
		nickTF.setColumns(10);
		
		ipTF = new JTextField();
		ipTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		ipTF.setColumns(10);
		ipTF.setBounds(204, 79, 114, 28);
		contentPane.add(ipTF);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnCancelar.setBounds(244, 209, 123, 33);
		contentPane.add(btnCancelar);
		
		JLabel passwordLbl = new JLabel("Contrase\u00F1a");
		passwordLbl.setFont(new Font("Rockwell", Font.PLAIN, 18));
		passwordLbl.setBounds(68, 165, 108, 33);
		contentPane.add(passwordLbl);
		
		passwordTF = new JTextField();
		passwordTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		passwordTF.setColumns(10);
		passwordTF.setBounds(204, 167, 114, 28);
		contentPane.add(passwordTF);
		setLocationRelativeTo(null);
	}
}
