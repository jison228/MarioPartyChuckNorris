package com.chucknorris.server.command.response;

public final class BadCommandResponse extends BadRequestResponse {

	public BadCommandResponse(String command) {
		message = String.format("Unrecognized command: %s", command);
	}
}
