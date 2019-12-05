package com.chucknorris;

public class Command {
	
	private String commandName;
	private String commandJSON;
	
	public Command(String commandName, String commandJSON) {
		this.commandName = commandName;
		this.commandJSON = commandJSON;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public String getCommandJSON() {
		return commandJSON;
	}

	public void setCommandJSON(String commandJSON) {
		this.commandJSON = commandJSON;
	}

}
