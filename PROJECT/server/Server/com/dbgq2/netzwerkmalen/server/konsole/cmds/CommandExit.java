package com.dbgq2.netzwerkmalen.server.konsole.cmds;

import com.dbgq2.netzwerkmalen.server.communication.Communication;
import com.dbgq2.netzwerkmalen.server.gameMechanics.Gameserver;
import com.dbgq2.netzwerkmalen.server.helper.Logger;

public class CommandExit implements Command {

	@Override
	public String giveName() {
		return "stop";
	}

	@Override
	public String[] giveAliases() {
		return null;
	}

	@Override
	public String giveCommandDescription() {
		return "Gracefully stops the whole server. Optionally send a shutdown message to all users.";
	}

	@Override
	public int giveArgumentLength() {
		return 0;
	}

	@Override
	public String giveCorrectSyntax() {
		return "<stop [message]>";
	}

	@Override
	public boolean handleCommand(String[] arguments) {
		if (arguments.length == 0) {
			Logger.log("Shutting down Server without message.");
			if (Gameserver.GOTT != null) {
				Gameserver.GOTT.COMunit.broadcastMessage(Communication.SERVER_MESSAGE_PREFIX + "shutting down.");
				Gameserver.GOTT.stopGame();
			} else
				Logger.error("Server never has been active.");
		} else {
			String message = "";
			for (String s : arguments)
				message += " " + s;
			message = message.replace("\n", "");

			Logger.log("Shutting down Server for reason: '" + message + "'");
			if (Gameserver.GOTT != null) {
				Gameserver.GOTT.COMunit
						.broadcastMessage(Communication.SERVER_MESSAGE_PREFIX + "shutting down: " + message);
				Gameserver.GOTT.stopGame();
			} else
				Logger.error("Server never has been active.");

		}
		return true;
	}

}
