package com.dbgq2.netzwerkmalen.server.gameMechanics;

/**
 * Beschreiben Sie hier die Klasse Punktemanager.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.util.HashMap;

import com.dbgq2.netzwerkmalen.server.helper.Logger;

public class Punktemanager {

	private final HashMap<String, Integer> pointListOverAll = new HashMap<String, Integer>();
	@SuppressWarnings("unused")
	private HashMap<String, Integer> pointListInGame = new HashMap<String, Integer>(); // TODO: EveryGame the
																						// Punktemanager counts, if the
																						// drawer has his first guess or
																						// multiple guesses and if the
																						// guessers are number one or
																						// multiple. Implement that.
																						// Implement also the PointsList
																						// enum from below

	/**
	 * Calculates Points for a specific game operation. If isDrawer is false, the
	 * given player ID will receive points based on a correct guess of the word If
	 * isDrawer is true, the given player ID will receive points for drawing
	 * something, that a player has recognized.
	 * 
	 * @param id
	 * @param isDrawer
	 */
	public void calculateANDaddPoints(String id, boolean isDrawer) {
		if (!Gameserver.GOTT.COMunit.isOnline(id)) {
			Logger.error("The Player with ID '" + id + "' is not online!");
			return;
		}

		if (isDrawer) {
			// The player is the drawer so he gets some special point treatment.
		} else {
			// The player has correctly guessed a word.
		}

		// TODO: Implement PointsList
		setPoints(id, 2);
	}

	/**
	 * Sets the points absolutely for a given Player.
	 * 
	 * @param id
	 * @param points
	 */
	private void setPoints(String id, int points) {
		if (Gameserver.GOTT.COMunit.isOnline(id)) {
			if (pointListOverAll.containsKey(id)) {
				pointListOverAll.replace(id, points);
			} else {
				pointListOverAll.put(id, points);
			}
		} else {
			Logger.error("Player with ID '" + id + "' is not online!");
			return;
		}
	}

	/**
	 * Returns the points for a given player ID
	 * 
	 * @param id
	 * @return
	 */
	public int getPoints(String id) {
		if (Gameserver.GOTT.COMunit.isOnline(id)) {
			if (pointListOverAll.containsKey(id)) {
				int points = pointListOverAll.get(id);
				return points;
			} else {
				return 0;
			}
		} else {
			Logger.error("Player with ID '" + id + "' is not online!");
			return -1;
		}
	}

	public void removePointsEntry(String id) {
		if (id == null) {
			Logger.error("ID cannot be null.");
			return;
		}
		if (pointListOverAll.containsKey(id)) {
			pointListOverAll.remove(id);
		}
	}

	public void clearPointEntrys() {
		pointListOverAll.clear();
		// TODO: Send update paket to all players tellig them to clear their point
		// lists.
	}

	public String getWinner() {
		String winner = "";
		int points = 0;
		for (HashMap.Entry<String, Integer> a : pointListOverAll.entrySet()) {
			if (points < a.getValue()) {
				points = a.getValue();
				winner = a.getKey();
			}
		}
		return winner;
	}

	public static enum PointIndex {
		DRAWER_SOMEONE_GUESSED_A_WORD_RIGHT(1), DRAWER_ADDITIONAL_PLAYER_GUESSED_A_WORD_RIGHT(
				2), GUESSER_FIRST_CORRECT_GUESS(5), GUESSER_CORRECT_GUESS(2);

		private PointIndex(int pointsGiven) {
			this.amountPointsGiven = pointsGiven;
		}

		private int amountPointsGiven;

		public int getAmountPointsGiven() {
			return amountPointsGiven;
		}
	}
}
