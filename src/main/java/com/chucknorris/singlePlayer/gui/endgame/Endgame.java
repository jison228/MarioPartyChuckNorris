package com.chucknorris.singlePlayer.gui.endgame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.chucknorris.gui.titulo.TitleImagePanel;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.Player;

public class Endgame extends JFrame {
	private JPanel mainPane;
	private JLabel txt1;
	private JLabel textFieldGanador;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player test = new Cristina("Cristina",0,0,0);
					Endgame frame = new Endgame(test);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Endgame(Player ganador) {
		// JFrame config
		setTitle("RESULTADOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setResizable(false);

		// mainPane config
		mainPane = new TitleImagePanel("endgame.jpg");
		mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		// Botones
		int btnW = 400;
		int btnH = 80;

		// Salir
		JButton btnQuit = new JButton("Irse del pais");
		btnQuit.setFont(new Font("Courier", Font.BOLD, 25));
		btnQuit.setFocusable(false);
		btnQuit.setBounds(438, 542, btnW,
				btnH);
		mainPane.add(btnQuit);
		
		txt1 = new JLabel();
		txt1.setText("GANADOR:");
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setFont(new Font("Courier New", Font.BOLD, 48));
		txt1.setBounds(300, 462, 279, 48);
		mainPane.add(txt1);
		
		textFieldGanador = new JLabel();
		textFieldGanador.setText(ganador.getCharacter());
		textFieldGanador.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldGanador.setFont(new Font("Courier New", Font.BOLD, 48));
		textFieldGanador.setBounds(644, 462, 440, 48);
		mainPane.add(textFieldGanador);
		
		WinnerImagePanel panel = new WinnerImagePanel(ganador.getCharacter());
		panel.setBounds(477, 162, 300, 300);
		mainPane.add(panel);	
		

		// Acciones de los botones

		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
