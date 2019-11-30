package com.chucknorris.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorMessage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErrorMessage frame = new ErrorMessage("Error 4808 asdas a0454 asd asd as AAAAAAAAAAA");
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
	public ErrorMessage(String mensajeError) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 373, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblError = new JLabel("ERROR");
		lblError.setBounds(5, 5, 349, 44);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 40));
		contentPane.add(lblError);
		
		JLabel lblerrormessage = new JLabel(mensajeError);
		lblerrormessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblerrormessage.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblerrormessage.setBounds(15, 59, 334, 42);
		contentPane.add(lblerrormessage);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAceptar.setBounds(136, 111, 85, 34);
		contentPane.add(btnAceptar);
		setLocationRelativeTo(null);
	}
}
