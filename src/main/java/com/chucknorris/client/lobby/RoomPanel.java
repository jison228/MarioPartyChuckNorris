package com.chucknorris.client.lobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.chucknorris.User;
import com.chucknorris.client.ClientInfoSalas;

public class RoomPanel extends JPanel {
	
	private List<ClientInfoSalas> salas;
	private JPanel mainList;

	public RoomPanel(List<ClientInfoSalas> salas) {
		this.salas = salas;

		this.setLayout(new BorderLayout());


		mainList = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		mainList.add(new JPanel(), gbc);
		add(new JScrollPane(mainList));
		
		this.updatePanel(salas);
	}


	public final void updatePanel(List<ClientInfoSalas> salas) {
		
		this.salas = salas;
		
		if(salas != null)
		for (int i = 0; i < this.salas.size(); i++) {

			JPanel panel = new JPanel(new BorderLayout());
			

			JPanel subPanelData = new JPanel(new GridLayout(2,1));
			panel.add(subPanelData, BorderLayout.CENTER);
			
			JPanel subPanelTop = new JPanel(new GridLayout(1,2));
			subPanelData.add(subPanelTop);
			
			JPanel subPanelBottom = new JPanel(new GridLayout(1,2));
			subPanelData.add(subPanelBottom);
			
			JLabel name = new JLabel(salas.get(i).name);
			name.setHorizontalAlignment(SwingConstants.LEFT);
			subPanelTop.add(name);
			
			JLabel cantPlayers = new JLabel("players: " + salas.get(i).cantPlayers + "/4");
			cantPlayers.setHorizontalAlignment(SwingConstants.LEFT);
			subPanelTop.add(cantPlayers);
			
			
			JLabel cantSpecs = new JLabel("Specs: " + salas.get(i).cantSpectadores);
			cantPlayers.setHorizontalAlignment(SwingConstants.LEFT);
			subPanelBottom.add(cantSpecs);
			
			
			JLabel state = new JLabel(salas.get(i).playing ? "playing" : "waitng for players");
			state.setHorizontalAlignment(SwingConstants.LEFT);
			subPanelBottom.add(state);
			
			String specOrPlay = salas.get(i).cantPlayers == 4? "spec" : "play";
			JButton enter = new JButton(specOrPlay);
			enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*
                     * poner aqui la acion
                     */
                }
            });
			panel.add(enter,BorderLayout.EAST);
			if(salas.get(i).playing) {
				enter.setEnabled(false);
			}
			
			
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
