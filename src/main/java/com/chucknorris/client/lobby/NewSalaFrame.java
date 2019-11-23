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
import com.google.gson.Gson;

public class NewSalaFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private Gson gson;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSalaFrame frame = new NewSalaFrame(null);
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
	public NewSalaFrame(Socket servidor) {
		gson = new Gson();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 158);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCrearSala = new JLabel("Crear Sala");
		lblCrearSala.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCrearSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearSala.setBounds(10, 10, 357, 25);
		contentPane.add(lblCrearSala);

		JLabel lblNombreSala = new JLabel("Nombre de la Sala:");
		lblNombreSala.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreSala.setBounds(10, 58, 145, 37);
		contentPane.add(lblNombreSala);

		textField = new JTextField();
		textField.setBounds(165, 58, 126, 37);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textField.getText().equals("")) {
						PrintStream ps = new PrintStream(servidor.getOutputStream(), true);
						Command enviarNombreSala = new Command("CreateSala", textField.getText());
						ps.println(gson.toJson(enviarNombreSala));
						dispose();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnCrear.setBounds(296, 58, 71, 37);
		contentPane.add(btnCrear);
	}
}
