package com.chucknorris.client;

public class GameParametersResponse {
	public String mapName;
	public double precioDolar;
	public double pesosIniciales;
	public double dolaresIniciales;
	public double salarioInicial;
	public int diceMin;
	public int diceMax;

	public GameParametersResponse(String mapName, double precioDolar, double pesosIniciales, double dolaresIniciales,
			double salarioInicial, int diceMin, int diceMax) {
		this.mapName = mapName;
		this.precioDolar = precioDolar;
		this.pesosIniciales = pesosIniciales;
		this.dolaresIniciales = dolaresIniciales;
		this.salarioInicial = salarioInicial;
		this.diceMin = diceMin;
		this.diceMax = diceMax;
	}

}
