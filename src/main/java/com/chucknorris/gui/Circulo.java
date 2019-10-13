package com.chucknorris.gui;

import com.chucknorris.commons.Position;

public class Circulo {
	private Position centro;
	private double radio;
	
	public Circulo(Position centro,double radio) {
		this.centro = centro;
		this.radio = radio;
	}
	
	public Position getCentro() {
		return this.centro;
	}
	
	public double getRadio() {
		return this.radio;
	}
	
	public void setRadio(double radio) {
		this.radio = radio;
	}
}
