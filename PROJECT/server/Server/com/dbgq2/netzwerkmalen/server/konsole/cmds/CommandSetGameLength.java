package com.dbgq2.netzwerkmalen.server.konsole.cmds;

import com.dbgq2.netzwerkmalen.server.gameMechanics.Gameserver;
import com.dbgq2.netzwerkmalen.server.helper.Logger;

public class CommandSetGameLength implements Command {

	@Override
	public String giveName() {
		return "setGameLength";
	}

	@Override
	public String[] giveAliases() {
		return new String[] { "gameLength" };
	}

	@Override
	public String giveCommandDescription() {
		return "Sets the duration of one game in seconds.";
	}

	@Override
	public int giveArgumentLength() {
		return 0;
	}

	@Override
	public String giveCorrectSyntax() {
		return "<setGameLength [duration]>";
	}

	@Override
	public boolean handleCommand(String[] arguments) {
		if (arguments.length == 1) {
			try {
				int i = Integer.parseInt(arguments[0]);
				Gameserver.GOTT.setGameDuration(i);
				Logger.log("New game duration set to "+i+" seconds.");
			} catch (NumberFormatException e) {
				Logger.error("Given Argument '" + arguments[0] + "' is not a number!");
				return false;
			}
		} else {
			// Wrong Syntax.
			Logger.error("Wrong Syntax. Use " + giveCorrectSyntax() + "!");
		}
		return false;
	}

}
