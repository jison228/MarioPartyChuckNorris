package com.chucknorris.client.titulo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.client.server.ChatThread;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInScreen extends JFrame {

	private JPanel contentPane;
	private JTextField nickTF;
	private JTextField contraTF;

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
				new ChatThread().run();
			}
		});
		btnEntrar.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnEntrar.setBounds(101, 209, 101, 33);
		contentPane.add(btnEntrar);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblNick.setBounds(68, 91, 108, 33);
		contentPane.add(lblNick);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Rockwell", Font.PLAIN, 18));
		lblContrasea.setBounds(68, 153, 108, 33);
		contentPane.add(lblContrasea);
		
		nickTF = new JTextField();
		nickTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		nickTF.setBounds(204, 91, 114, 28);
		contentPane.add(nickTF);
		nickTF.setColumns(10);
		
		contraTF = new JTextField();
		contraTF.setFont(new Font("Rockwell", Font.PLAIN, 18));
		contraTF.setColumns(10);
		contraTF.setBounds(204, 153, 114, 28);
		contentPane.add(contraTF);
		
		JButton btnRegistrase = new JButton("Registrar");
		btnRegistrase.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnRegistrase.setBounds(244, 209, 123, 33);
		contentPane.add(btnRegistrase);
	}
}
