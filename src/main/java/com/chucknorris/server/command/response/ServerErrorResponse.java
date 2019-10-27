package com.chucknorris.server.command.response;

public final class ServerErrorResponse extends ServerResponse {
	public ServerErrorResponse() {
		status = 500;
	}
}
