package com.chucknorris.gui.compradolares;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import com.chucknorris.player.Player;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CompraDolaresFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private JFormattedTextField pesosTF;
	private JLabel pesosActualesLbl;
	private JLabel dolaresActualeslbl;
	double resultado;
	private JButton btnComprar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player p = new Player("Cristi", 150, 100);
					CompraDolaresFrame frame = new CompraDolaresFrame(p, 20.0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CompraDolaresFrame(Player player, double precioDolar) {
		// JFrameConfig
		setAlwaysOnTop(true);
		setTitle("Comprar Dolares");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 200, 400, 250);
		setResizable(false);

		// mainPane config
		mainPane = new CompraDolaresImagePanel("banco.jpg");
		mainPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel dolaresLbl = new JLabel("0");
		dolaresLbl.setBackground(Color.LIGHT_GRAY);
		dolaresLbl.setForeground(Color.GREEN);
		dolaresLbl.setFont(new Font("Arial Black", Font.PLAIN, 25));
		dolaresLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		dolaresLbl.setBounds(216, 94, 105, 30);
		mainPane.add(dolaresLbl);

		pesosActualesLbl = new JLabel("Pesos Actuales: " + player.getPesos());
		pesosActualesLbl.setForeground(Color.WHITE);
		pesosActualesLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		pesosActualesLbl.setBackground(Color.ORANGE);
		pesosActualesLbl.setBounds(10, 176, 173, 36);
		mainPane.add(pesosActualesLbl);

		dolaresActualeslbl = new JLabel("Dolares Actuales: " + player.getDolares());
		dolaresActualeslbl.setForeground(Color.WHITE);
		dolaresActualeslbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		dolaresActualeslbl.setBackground(Color.ORANGE);
		dolaresActualeslbl.setBounds(213, 176, 173, 36);
		mainPane.add(dolaresActualeslbl);
		
		pesosTF = new JFormattedTextField(new NumberFormatter(NumberFormat.getNumberInstance(Locale.UK)));
		pesosTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				btnComprar.setVisible(false);
			}
		});
		pesosTF.setValue(new Double(0));
		pesosTF.setBackground(Color.LIGHT_GRAY);
		pesosTF.setForeground(Color.GREEN);
		pesosTF.setText("0");
		pesosTF.setHorizontalAlignment(SwingConstants.RIGHT);
		pesosTF.setFont(new Font("Arial Black", Font.PLAIN, 25));
		pesosTF.setBounds(10, 94, 105, 30);
		mainPane.add(pesosTF);
		pesosTF.setColumns(10);
		
		JButton aceptarBtn = new AceptarButton("cambiar.jpg");
		aceptarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Double.valueOf(pesosTF.getValue().toString())>=player.getPesos()) {
					pesosTF.setValue(player.getPesos());
				}
				dolaresLbl.setText(Double.toString((Double.valueOf(pesosTF.getValue().toString())/precioDolar)));
				btnComprar.setVisible(true);
			}
		});
		aceptarBtn.setBounds(164, 94, 70, 30);
		mainPane.add(aceptarBtn);
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.setVisible(false);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.buyDolares(Double.valueOf(pesosTF.getValue().toString()), Double.valueOf(dolaresLbl.getText()));
				dispose();
			}
		});
		btnComprar.setForeground(new Color(0, 255, 0));
		btnComprar.setFont(new Font("Arial", Font.BOLD, 16));
		btnComprar.setBounds(135, 145, 120, 25);
		mainPane.add(btnComprar);
	}
}
