package com.chucknorris.server.command.response;

public class ServerErrorResponse extends ServerResponse {
	public ServerErrorResponse() {
		status = 500;
	}
}
