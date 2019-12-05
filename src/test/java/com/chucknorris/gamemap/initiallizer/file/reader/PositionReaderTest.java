package com.chucknorris.gamemap.initiallizer.file.reader;

import com.chucknorris.commons.Position;
import com.chucknorris.gamemap.initiallizer.file.reader.position.PositionReadable;
import com.chucknorris.gamemap.initiallizer.file.reader.position.PositionReader;
import org.junit.Assert;
import org.junit.Test;

public class PositionReaderTest {

	@Test
	public void testReadPosition_normal_case() {
		PositionReadable positionReader = new PositionReader();

		Position position = positionReader.readPosition("1 2");

		Assert.assertEquals("X = 1, Y = 2", position.printPosition());
	}

	@Test
	public void testReadPosition_more_empty_spaces_between_coords() {
		PositionReadable positionReader = new PositionReader();

		Position position = positionReader.readPosition("1   2");

		Assert.assertEquals("X = 1, Y = 2", position.printPosition());
	}

	@Test(expected = Exception.class)
	public void testReadPosition_invalid_data_one_coord() {
		PositionReadable positionReader = new PositionReader();

		positionReader.readPosition("2");
	}

	@Test(expected = Exception.class)
	public void testReadPosition_invalid_data_empty_data() {
		PositionReadable positionReader = new PositionReader();

		positionReader.readPosition("");
	}
}