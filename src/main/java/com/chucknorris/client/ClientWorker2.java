package com.chucknorris.client;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.chucknorris.client.tablero.MainGameScreen;
import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.nodes.ParitariaNode;
import com.chucknorris.player.Cristina;
import com.chucknorris.player.DelCanio;
import com.chucknorris.player.Espert;
import com.chucknorris.player.Macri;
import com.chucknorris.player.Player;

public class ClientWorker2 extends Thread{
	
	MainGameScreen frame;
	
	public ClientWorker2(MainGameScreen frame) {
		this.frame = frame;
	}
	
	@Override
	public void run() {
		try {
			ParitariaNode ini = new ParitariaNode(null, new Position(0,0));
			Queue<Position> cola = new LinkedList<Position>();
			cola.add(new Position(0,0));
			cola.add(new Position(1,0));
			cola.add(new Position(2,0));
			cola.add(new Position(3,0));
			cola.add(new Position(4,0));
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

			MovementResponse respuesta = new MovementResponse(4, p1, cola, false, null, listaP, false);

			frame.playTurn(respuesta);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
