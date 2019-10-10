package com.chucknorris.commons;

import com.chucknorris.gamemap.presenter.PositionPresenter;

import java.util.Objects;

public class Position {
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

	public String printPosition() {
		return String.format("X = %s, Y = %s", posX, posY);
	}

	public String present(PositionPresenter positionPresenter) {
		return positionPresenter.present(posX, posY);
	}
}
