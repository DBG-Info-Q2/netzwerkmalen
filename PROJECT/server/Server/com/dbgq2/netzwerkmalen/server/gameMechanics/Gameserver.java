package com.dbgq2.netzwerkmalen.server.gameMechanics;

import java.util.ArrayList;

import com.dbgq2.netzwerkmalen.server.communication.Communication;
import com.dbgq2.netzwerkmalen.server.communication.PaketUtil;
import com.dbgq2.netzwerkmalen.server.helper.Logger;
import com.dbgq2.netzwerkmalen.server.konsole.Konsole;

/**
 * All Main issues happening here.
 * 
 * @author Lord_von_X
 */
public class Gameserver {
	public String spielwort;
	public String drawerID;
	public int gameAmountCounter;
	private int gameUntilReset = 5;
	public int currentRightGuesses;
	private int maxPlayer = 5;
	private int timerLength = 60;
	private int timerUpdateTime = 500; // in ms
	private static boolean gameRunning = false;

	public Communication COMunit = new Communication();
	public Punktemanager points = new Punktemanager();
	public Spielwoerter wort = new Spielwoerter();
	public Timer timer = new Timer();
	public Konsole console = new Konsole();
	public static Gameserver GOTT; // Singleton. Static access to main Gameserver Class..
	public Thread game;

	public Gameserver() {
		GOTT = this;
	}

	/**
	 * Methode for starting all server issues
	 *
	 */
	public void startNewServer() {
		console.start();
		COMunit.startListener();

		// TODO: Waiting for sufficient number of players.
		/*
		 * long time = System.currentTimeMillis()+60000; while
		 * (COMunit.playerList.size()<maxPlayer && System.currentTimeMillis()<time) {
		 * 
		 * }
		 * 
		 * if (COMunit.playerList.size()==maxPlayer) {
		 * Logger.log("maximal player nummber is reached"); } else {
		 * Logger.log("60 sec are over"); }
		 * 
		 * startNewGame();
		 */
	}

	/**
	 * Methode for starting a game
	 *
	 */
	public void startNewGame() {
		Logger.log("starting game...");

		spielwort = wort.gibNeueswort();
		Communication.sendPaket(drawerID, PaketUtil.createWordUpdatePaket(spielwort));
		Logger.log("gameword is set to: " + spielwort);

		timer.startCounter(timerLength, timerUpdateTime);

		Logger.log("Gamestateupdate Paket sending to all users");
		Communication.sendPaket("-1", PaketUtil.createGameStateUpdatePaket(true));

		selectDrawerFromPlayerlist();
		Logger.log("drawerID is: " + drawerID);
		Communication.sendPaket("-1", PaketUtil.createRoleUpdatePaket(false));
		Communication.sendPaket(drawerID, PaketUtil.createRoleUpdatePaket(true));

		runningGame();
	}

	/**
	 * Methode for select the drawer from the playerlist
	 *
	 */
	public void selectDrawerFromPlayerlist() {
		ArrayList<String> randomList = new ArrayList<String>(COMunit.getAllPlayerIDs());
		drawerID = randomList.get((int) Math.round((Math.random() * (COMunit.getAmountOfOnlinePlayers() - 1))));
	}

	public void runningGame() {
		if (!gameRunning) {
			gameRunning = true;
			game = new Thread(new Runnable() {
				@Override
				public void run() {
					while (currentRightGuesses < maxPlayer && timer.timerRuns()) {

					}
					resetGame();
				}
			});
			game.start();
		}
	}

	/**
	 * Methode resetGame
	 *
	 */
	@SuppressWarnings("deprecation")
	public void resetGame() {
		game.stop();
		gameRunning = false;

		gameAmountCounter++;
		spielwort = null;
		timer.stopCounter();
		drawerID = null;
		Communication.sendPaket("-1", PaketUtil.createGameStateUpdatePaket(false));
		Logger.log("game resetted...");
		if (gameAmountCounter <= gameUntilReset) {
			startNewGame();
		} else {
			Communication.sendPaket("-1", PaketUtil.createGameEndUpdatePaket(points.getWinner()));
			stopGame();
		}
	}

	/**
	 * Methode stopGame
	 *
	 */
	public void stopGame() {
		Logger.log("server is closing in 10sec...");
		long time = System.currentTimeMillis() + 10000;
		while (System.currentTimeMillis() < time) {
		}
		forceStopGame();
	}

	@SuppressWarnings("deprecation")
	public void forceStopGame() {
		Logger.log("server now closing...");
		COMunit.forceShutdown();
		timer.stopCounter();
		if (game != null)
			game.stop();
		GOTT = null;
		// TODO: Clear all Variables to restore space
		System.exit(1);
		if (console != null)
			console.stop();
		return;
	}
}
