package com.chucknorris.gui.gameoptions;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.chucknorris.commons.Dice;
import com.chucknorris.gamemap.GameMap;
import com.chucknorris.gamemap.initiallizer.file.reader.csv.MapFileCSVReader;
import com.chucknorris.gui.GameInformation;
import com.chucknorris.gui.tablero.MainGameScreen;
import com.chucknorris.player.Player;
import javax.swing.JTextField;

public class NewGameScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel newGamePane;
	private JTextField dadoMinTF;
	private JTextField dadoMaxTF;
	private JTextField salarioTF;
	private JTextField pesosTF;
	private JTextField dolaresTF;
	private JTextField precioDolarTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameScreen frame = new NewGameScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public NewGameScreen() {
		// JFrame config
		setTitle("Empezar Nueva Partida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		setResizable(false);

		// JPanel config
		newGamePane = new NewGameImagePanel("nueva_partida.jpg");
		newGamePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		newGamePane.setLayout(null);
		setContentPane(newGamePane);

		// New Game Combo box
		JComboBox<String> mapCombo = new JComboBox<String>();
		mapCombo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mapCombo.setBounds(70, 200, 200, 25);
		newGamePane.add(mapCombo);
		mapCombo.addItem("mapa_default");

		JButton jugarBtn = new JButton("JUGAR");
		jugarBtn.setBackground(Color.RED);
		jugarBtn.setForeground(Color.DARK_GRAY);
		jugarBtn.setFont(new Font("Tahoma", Font.BOLD, 60));
		jugarBtn.setBounds(986, 576, 246, 86);
		newGamePane.add(jugarBtn);

		// Textfields
		// dado min
		dadoMinTF = new JTextField();
		dadoMinTF.setBackground(Color.lightGray);
		dadoMinTF.setSelectionColor(Color.PINK);
		dadoMinTF.setFont(new Font("Courier", Font.BOLD, 25));
		dadoMinTF.setForeground(Color.RED);
		dadoMinTF.setAlignmentX(CENTER_ALIGNMENT);
		dadoMinTF.setColumns(10);
		dadoMinTF.setBounds(461, 250, 32, 32);
		newGamePane.add(dadoMinTF);

		// dado max
		dadoMaxTF = new JTextField();
		dadoMaxTF.setBackground(Color.lightGray);
		dadoMaxTF.setSelectionColor(Color.PINK);
		dadoMaxTF.setFont(new Font("Courier", Font.BOLD, 25));
		dadoMaxTF.setForeground(Color.RED);
		dadoMaxTF.setAlignmentX(CENTER_ALIGNMENT);
		dadoMaxTF.setColumns(10);
		dadoMaxTF.setBounds(562, 250, 32, 32);
		newGamePane.add(dadoMaxTF);

		dadoMinTF.setText("1");
		dadoMinTF.setEditable(false);
		dadoMaxTF.setText("6");
		dadoMaxTF.setEditable(false);

		// salario inicial
		salarioTF = new JTextField();
		salarioTF.setBackground(Color.LIGHT_GRAY);
		salarioTF.setSelectionColor(Color.green);
		salarioTF.setFont(new Font("Courier", Font.BOLD, 25));
		salarioTF.setForeground(Color.GREEN);
		salarioTF.setAlignmentX(CENTER_ALIGNMENT);
		salarioTF.setBounds(447, 330, 230, 39);
		newGamePane.add(salarioTF);
		salarioTF.setColumns(10);

		// pesos iniciales
		pesosTF = new JTextField();
		pesosTF.setBackground(Color.LIGHT_GRAY);
		pesosTF.setSelectionColor(Color.green);
		pesosTF.setFont(new Font("Courier", Font.BOLD, 25));
		pesosTF.setForeground(Color.GREEN);
		pesosTF.setAlignmentX(CENTER_ALIGNMENT);
		pesosTF.setColumns(10);
		pesosTF.setBounds(447, 437, 230, 39);
		newGamePane.add(pesosTF);

		// dolares iniciales
		dolaresTF = new JTextField();
		dolaresTF.setBackground(Color.LIGHT_GRAY);
		dolaresTF.setSelectionColor(Color.green);
		dolaresTF.setFont(new Font("Courier", Font.BOLD, 25));
		dolaresTF.setForeground(Color.GREEN);
		dolaresTF.setAlignmentX(CENTER_ALIGNMENT);
		dolaresTF.setColumns(10);
		dolaresTF.setBounds(447, 543, 230, 39);
		newGamePane.add(dolaresTF);

		// Precio Dolar
		precioDolarTF = new JTextField();
		precioDolarTF.setSelectionColor(Color.GREEN);
		precioDolarTF.setForeground(Color.GREEN);
		precioDolarTF.setFont(new Font("Monospaced", Font.BOLD, 25));
		precioDolarTF.setEditable(false);
		precioDolarTF.setColumns(10);
		precioDolarTF.setBackground(Color.LIGHT_GRAY);
		precioDolarTF.setAlignmentX(0.5f);
		precioDolarTF.setBounds(731, 250, 65, 32);
		newGamePane.add(precioDolarTF);

		salarioTF.setText("300");
		salarioTF.setEditable(false);
		pesosTF.setText("100");
		pesosTF.setEditable(false);
		dolaresTF.setText("10");
		dolaresTF.setEditable(false);
		precioDolarTF.setText("20");
		precioDolarTF.setEditable(false);

		// Map image
		MapImagePanel mapImage = new MapImagePanel(mapCombo.getSelectedItem().toString() + ".jpg");
		mapImage.setBounds(70, getHeight() - (int) (getHeight() * 0.65), 300, 300);
		newGamePane.add(mapImage);

		jugarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameMap mapa1 = null;
				MapFileCSVReader mapFileCSVReader = new MapFileCSVReader(
						mapCombo.getSelectedItem().toString() + ".txt");
				try {
					mapa1 = mapFileCSVReader.buildGameMap();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);

				Player p1 = new Player("Milei", 1450, 150);
				Player p2 = new Player("Morsa", 150, 200);
				Player p3 = new Player("Cristina", 500, 600);
				Player p4 = new Player("Mauricio", 150, 900);
				List<Player> listaP = new ArrayList<Player>();
				listaP.add(p1);
				listaP.add(p2);
				listaP.add(p3);
				listaP.add(p4);

				new MainGameScreen(new GameInformation(listaP, mapa1,
						new Dice(Integer.valueOf(dadoMinTF.getText()), Integer.valueOf(dadoMaxTF.getText())), Integer.valueOf(precioDolarTF.getText()),Integer.valueOf(salarioTF.getText()),Integer.valueOf(pesosTF.getText()),Integer.valueOf(dolaresTF.getText())))
								.setVisible(true);
				dispose();
			}
		});

	}
}
