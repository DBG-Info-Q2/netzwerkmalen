package com.dbgq2.netwerkmalen.server;

import com.dbgq2.netzwerkmalen.server.gameMechanics.Gameserver;

/**
 * This class launches the server.
 * 
 * @author Aleks
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Excecuting programm..");

		Gameserver server = new Gameserver();
		server.startNewServer();

	}

}
