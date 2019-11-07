package com.chucknorris.server.command.response;

public class BadRequestResponse extends ServerErrorResponse {
	public String message = "Bad request";
	public int status = 400;

	public BadRequestResponse() {

	}

	public BadRequestResponse(String reason) {
		message += " " + reason;
	}
}
