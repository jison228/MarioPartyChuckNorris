package com.chucknorris.server.command.response;

public class BadRequestResponse extends ServerErrorResponse {
	private static final String BAD_REQUEST = "Bad Request";
	public final String message;

	public BadRequestResponse() {
		message = BAD_REQUEST;
	}
}
