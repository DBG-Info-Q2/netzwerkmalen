package com.dbgq2.netzwerkmalen.server.konsole.cmds;

import com.dbgq2.netzwerkmalen.server.helper.Logger;
import com.dbgq2.netzwerkmalen.server.konsole.CommandProcessor;

public class CommandHelp implements Command {

	@Override
	public String giveName() {
		return "help";
	}

	@Override
	public int giveArgumentLength() {
		return 0;
	}

	@Override
	public String giveCorrectSyntax() {
		return "<help>";
	}

	@Override
	public boolean handleCommand(String[] arguments) {
		if (arguments.length == 0) {
			Logger.log("Availible commands:");
			for (Command cmd : CommandProcessor.getCommands())
				Logger.log(cmd.giveCorrectSyntax() + " - " + cmd.giveCommandDescription());
		} else {
			Command cmdLookingFor = CommandProcessor.findCommand(arguments[0]);
			if (cmdLookingFor == null) {
				Logger.error("Cannot find any help for command '" + arguments[0]
						+ "'! Use <help> to get a list of all availible commands.");
				return true;
			}
			Logger.log(cmdLookingFor.giveCorrectSyntax() + " - " + cmdLookingFor.giveCommandDescription());
			if (cmdLookingFor.giveAliases() != null) {
				String alias = "";
				for (String s : cmdLookingFor.giveAliases()) {
					alias += ", " + s;
				}
				alias = alias.replaceFirst(", ", "");

				Logger.log("Aliases: " + alias);
			}
		}
		return true;
	}

	@Override
	public String[] giveAliases() {
		return null;
	}

	@Override
	public String giveCommandDescription() {
		return "Gives help to all Commands.";
	}

}
