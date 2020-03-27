package com.dbgq2.netzwerkmalen.server.konsole.cmds;

import com.dbgq2.netzwerkmalen.server.communication.Communication;
import com.dbgq2.netzwerkmalen.server.gameMechanics.Gameserver;
import com.dbgq2.netzwerkmalen.server.helper.Logger;

public class CommandBroadcast implements Command {

	@Override
	public String giveName() {
		return "broadcast";
	}

	@Override
	public String[] giveAliases() {
		return new String[] {"bc"};
	}

	@Override
	public String giveCommandDescription() {
		return "Broadcast a <message> to all players on the Server.";
	}

	@Override
	public int giveArgumentLength() {
		return 0;
	}

	@Override
	public String giveCorrectSyntax() {
		return "<bc [message]>";
	}

	@Override
	public boolean handleCommand(String[] arguments) {
		if (arguments.length == 0) {
			Logger.log("Cannot send an empty message.");
		} else {
			String message = "";
			for (String s : arguments)
				message += " " + s;
			message = message.replace("\n", "").replaceFirst(" ", "");

			Logger.log("Sending following message to everyone: '" + message + "'");
				Gameserver.GOTT.COMunit
						.broadcastMessage(Communication.SERVER_MESSAGE_PREFIX + message);

		}
		return true;
	}

}
