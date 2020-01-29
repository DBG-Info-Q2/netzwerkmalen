package com.dbgq2.netzwerkmalen.server.konsole.cmds;

public interface Command {

	public String giveName();
	
	public String[] giveAliases();
	
	public String giveCommandDescription();
	
	@Deprecated
	public int giveArgumentLength();
	
	public String giveCorrectSyntax();
	
	public boolean handleCommand(String[] arguments);
}
