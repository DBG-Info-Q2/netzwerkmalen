package com.dbgq2.netzwerkmalen.server.konsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.dbgq2.netzwerkmalen.server.helper.Logger;
import com.dbgq2.netzwerkmalen.server.konsole.cmds.Command;

/**
 * Die Konsole ist das Eingabeinterface fuer den Server. Hier koennen Befehle eingegeben werden.
 * 
 * @author Aleksander Stepien
 * @version 2.2
 */
public class Konsole extends Thread {

	private CommandProcessor processor;

	public Konsole() {
		super();
		processor = new CommandProcessor();
		processor.lookAndLoadCommands();
	}

	@Override
	public void run() {
		while (true) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

				String cmd = null;
				while ((cmd = reader.readLine()) != null) {
					handleCommand(cmd);
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void handleCommand(String command) {
		if (command == null || command.equals("")) {
			error("Command cannot be empty");
			return;
		}

		String[] commands = command.split(" ", 2);
		if (commands.length == 0) {
			error("Error. Commands length is zero.");
			return;
		}

		String cmd = commands[0];
		String[] args = new String[0];

		if (commands.length > 1) {
			// There is argumentation to the code.
			args = commands[1].split(" ");
		}

		Command commandClass = CommandProcessor.findCommand(cmd);

		if (commandClass != null) {
			Logger.debug("Running command: '" + cmd + "'");
			commandClass.handleCommand(args);
		} else {
			error("Cannot find command with name '" + cmd + "'!");
			return;
		}
		
		return;
	}

	public void error(String error) {
		Logger.error("CommandProcessor: " + error);
	}

	public void log(String log) {
		Logger.logPr("CommandProcessor: " + log);
	}
}
