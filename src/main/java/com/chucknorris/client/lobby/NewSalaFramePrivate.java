package com.chucknorris.client.lobby;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.chucknorris.Command;
import com.chucknorris.server.SalaResponse;
import com.google.gson.Gson;

public class NewSalaFramePrivate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private Gson gson;
	private JTextField textFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSalaFramePrivate frame = new NewSalaFramePrivate(null);
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
	public NewSalaFramePrivate(Socket servidor) {
		gson = new Gson();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 222);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCrearSala = new JLabel("Crear Sala");
		lblCrearSala.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCrearSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearSala.setBounds(10, 10, 386, 25);
		contentPane.add(lblCrearSala);

		JLabel lblNombreSala = new JLabel("Nombre de la Sala:");
		lblNombreSala.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreSala.setBounds(10, 58, 145, 37);
		contentPane.add(lblNombreSala);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(165, 58, 126, 37);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textFieldNombre.getText().equals("") && !textFieldPassword.getText().equals("")) {
						PrintStream ps = new PrintStream(servidor.getOutputStream(), true);
						SalaResponse sr = new SalaResponse(textFieldNombre.getText(), textFieldPassword.getText(),true);
						Command enviarNombreSala = new Command("CreateSala", gson.toJson(sr));
						ps.println(gson.toJson(enviarNombreSala));
						dispose();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnCrear.setBounds(301, 60, 95, 37);
		contentPane.add(btnCrear);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContrasea.setBounds(10, 116, 145, 37);
		contentPane.add(lblContrasea);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(165, 116, 126, 37);
		contentPane.add(textFieldPassword);
		
		JButton btnCancelar = new JButton("Cancelar\r\n");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(301, 116, 95, 37);
		contentPane.add(btnCancelar);
	}
}
