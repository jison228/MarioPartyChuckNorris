package com.chucknorris.server.command.response;

import java.io.Serializable;

public abstract class ServerResponse implements Serializable {
	public int status = 200;
}
