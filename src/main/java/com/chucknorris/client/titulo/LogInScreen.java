package com.chucknorris.client.titulo;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.chucknorris.client.server.ChatThread;
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
				GameMap mapa1 = null;
				MapFileCSVReader mapFileCSVReader = new MapFileCSVReader("newMap1.txt");
				try {
					mapa1 = mapFileCSVReader.buildGameMap();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ParitariaNode ini = new ParitariaNode(null, new Position(0,0));
				Espert p1 = new Espert(1450, 150, 100);
				Cristina p2 = new Cristina(150, 100, 900);
				Macri p3 = new Macri(500, 100, 100);
				DelCanio p4 = new DelCanio(150, 100, 100);
				p1.setNodeLocation(ini);
				p2.setNodeLocation(ini);
				p3.setNodeLocation(ini);
				p4.setNodeLocation(ini);
				List<Player> listaP = new ArrayList<Player>();
				listaP.add(p1);
				listaP.add(p2);
				listaP.add(p3);
				listaP.add(p4);

				GameInformation gameInformation = new GameInformation(listaP,mapa1,20);

				MainGameScreen frame = new MainGameScreen(gameInformation);
				
				frame.setVisible(true);
				new ChatThread(frame).run();
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
