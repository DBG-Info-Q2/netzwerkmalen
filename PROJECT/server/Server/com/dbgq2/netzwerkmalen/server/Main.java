package com.dbgq2.netzwerkmalen.server;

import com.dbgq2.netzwerkmalen.server.gameMechanics.Gameserver;
import com.dbgq2.netzwerkmalen.server.gameMechanics.Spielwoerter;
import com.dbgq2.netzwerkmalen.server.helper.FileHelper;

/**
 * This class launches the server.
 * 
 * @author Aleks
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Excecuting programm..");
		Spielwoerter test = new Spielwoerter();
		System.out.println(test.gibNeueswort());
		
		//FileHelper.source();
		//Gameserver server = new Gameserver();
		// server.startNewGame();

	}

}
