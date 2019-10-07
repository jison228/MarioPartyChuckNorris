package com.chucknorris.gamemap.presenter;

public class WhiteSpacePositionPresenter implements PositionPresenter {

	@Override
	public String present(int... coords) {
		return String.format("%s %s", coords[0], coords[1]);
	}
}
