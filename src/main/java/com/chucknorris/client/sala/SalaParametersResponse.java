package com.chucknorris.client.sala;

public class SalaParametersResponse {
	public String mapName;
	public double pesosIniciales;
	public double dolaresIniciales;
	public double salarioInicial;
	public double precioDolar;
	public int diceMin;
	public int diceMax;
	
	public SalaParametersResponse(String mapName, double pesosIniciales, double dolaresIniciales, double salarioInicial,
			double precioDolar, int diceMin, int diceMax) {
		this.mapName = mapName;
		this.pesosIniciales = pesosIniciales;
		this.dolaresIniciales = dolaresIniciales;
		this.salarioInicial = salarioInicial;
		this.precioDolar = precioDolar;
		this.diceMin = diceMin;
		this.diceMax = diceMax;
	}
	
}
