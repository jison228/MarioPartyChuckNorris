package com.chucknorris.server.command.response;

public class BadRequestResponse extends ServerErrorResponse {
	public String message = "Bad request";

	public BadRequestResponse() {
		this.status = 400;
	}
}
