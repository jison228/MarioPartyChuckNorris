package com.chucknorris.gamemap.initiallizer.file.reader.position;

import com.chucknorris.commons.Position;

import java.util.Scanner;

public class PositionReader implements PositionReadable {

	@Override
	public Position readPosition(String data) {
		Scanner scanner = new Scanner(data);

		int x = scanner.nextInt();

		int y = scanner.nextInt();

		return new Position(x, y);
	}

}
