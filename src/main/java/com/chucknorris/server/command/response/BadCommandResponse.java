package com.chucknorris.server.command.response;

public final class BadCommandResponse extends ServerResponse {
	public final String message;

	public BadCommandResponse(String command) {
		message = String.format("Unrecognized command: %s", command);
	}
}
