package com.chucknorris.client.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.chucknorris.User;

public class UserPanel extends JPanel {
	private List<User> usuarios;
	private JPanel mainList;

	public UserPanel(List<User> usuarios) {
		this.usuarios = usuarios;

		this.setLayout(new BorderLayout());


		mainList = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		mainList.add(new JPanel(), gbc);
		add(new JScrollPane(mainList));
		
		this.updatePanel(usuarios);
	}


	public final void updatePanel(List<User> usuarios) {
		
		this.usuarios = usuarios;

		if(usuarios != null)
		for (int i = 0; i < usuarios.size(); i++) {

			JPanel panel = new JPanel(new GridLayout(1,2));			
			
			JLabel name = new JLabel(usuarios.get(i).getPlayerID());
			name.setHorizontalAlignment(SwingConstants.LEFT);
			panel.add(name);
			
			JLabel cantWins = new JLabel("wins: " + usuarios.get(i).getPartidasGanadas());
			cantWins.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(cantWins);

			panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			mainList.add(panel, gbc, 0);

			this.validate();
		}
		
		//this.repaint();
		
	}
}
