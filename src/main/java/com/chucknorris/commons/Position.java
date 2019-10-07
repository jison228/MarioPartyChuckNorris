package com.chucknorris.commons;

import com.chucknorris.gamemap.presenter.PositionPresenter;

import java.util.Objects;

public class Position implements Comparable<Position> {
	private int posX;
	private int posY;

	public Position(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;

		Position position = (Position) o;
		return posX == position.posX &&
				posY == position.posY;
	}

	@Override
	public int hashCode() {
		return Objects.hash(posX, posY);
	}

	public void setPos(Position pos) {
		this.posX = pos.posX;
		this.posY = pos.posY;
	}

	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public int getX() {
		return this.posX;
	}

	public int getY() {
		return this.posY;
	}

	public String printPosition() {
		return String.format("X = %s, Y = %s", posX, posY);
	}

	@Override
	public int compareTo(Position o) {
		return Double.compare(calculateLength(), o.calculateLength());
	}

	private double calculateLength() {
		return Math.sqrt((Math.pow(posX, 2) + Math.pow(posY, 2)));
	}

	public String present(PositionPresenter positionPresenter) {
		return positionPresenter.present(posX, posY);
	}
}
