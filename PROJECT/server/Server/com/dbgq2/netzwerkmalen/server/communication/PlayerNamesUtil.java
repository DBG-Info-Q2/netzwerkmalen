package com.dbgq2.netzwerkmalen.server.communication; 

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.istack.internal.NotNull;

/**
 * Util to create Names for Players
 */
public class PlayerNamesUtil {
	public static final String[] sampleNames;
	public static final String[] samplePrefixes;
	private static ArrayList<String> choosingNames;

	/*
	 * /** Procedure finds an not used name according to Array of names that are
	 * already used. Also adds 1!!!... at the end if all names are being used.
	 * suggestedNameAddon can be a suffix to the user name
	 *
	 * public static String findNewName(String[] existingNames, String
	 * suggestedNameAddon) { if (existingNames == null) return null;
	 * 
	 * String name = "";// selectRandomName(); if (suggestedNameAddon != null) name
	 * = name + suggestedNameAddon;
	 * 
	 * boolean foundExistingName = false; for (String exName : existingNames) if
	 * (exName.equalsIgnoreCase(name)) foundExistingName = true;
	 * 
	 * if (foundExistingName) if (suggestedNameAddon == null) return
	 * findNewName(existingNames, "1"); else return findNewName(existingNames,
	 * suggestedNameAddon + "!"); return name; }
	 */

	public static String findNewName(String[] existingNames) {
		if (existingNames == null)
			return selectRandomName(getPrefixNameConcurrentOptionsList()); // Return a random
																			// name here

		// Convert sampleNames to ArrayList
		ArrayList<String> availibleNames = new ArrayList<String>(Arrays.asList(getPrefixNameConcurrentOptionsList()));
		// Filter out all existing names
		availibleNames.removeAll(Arrays.asList(existingNames));

		if (availibleNames.size() == 0) {
			// Chose random name with ending and start verification process again
			return findNewNameWithSuffix(selectRandomName(getPrefixNameConcurrentOptionsList()), existingNames, 1);
		} else {
			// Chose random name from that list.
			return selectRandomName(availibleNames.toArray(new String[availibleNames.size()]));
		}
	}

	/**
	 * Fallback to when no custom names are availible. Addds numbers to the end.
	 * 
	 * @param existingNames
	 * @return
	 */
	private static String findNewNameWithSuffix(String nameToAddSuffix, @NotNull String[] existingNames,
			int currentNewNumber) {
		if (nameToAddSuffix == null)
			return null;
		String currentWorkingName = nameToAddSuffix + Integer.toString(currentNewNumber);
		for (String concurrentNameCheck : existingNames) {
			if (currentWorkingName.equalsIgnoreCase(concurrentNameCheck))
				return findNewNameWithSuffix(nameToAddSuffix, existingNames, currentNewNumber + 1);
		}
		return currentWorkingName;
	}

	/**
	 * Selects a random string from the given array
	 * 
	 * @param array Array to analyze
	 * @return A random string.
	 */
	private static String selectRandomName(String[] array) {
		if (array == null)
			return null;
		return array[(int) (Math.round((Math.random() * (array.length - 1))))];
	}

	/**
	 * Create all possible prefix - name combintaions and cache them in order so
	 * save some calculation power.
	 * 
	 * @return String array with all possible ids for the player.
	 */
	private static String[] getPrefixNameConcurrentOptionsList() {
		if (choosingNames == null) {
			choosingNames = new ArrayList<String>();
			for (String name : sampleNames) {
				for (String prefix : samplePrefixes) {
					choosingNames.add(prefix + name);
				}
			}
		}
		return choosingNames.toArray(new String[choosingNames.size()]);
	}

	static {
		/**
		 * A pre selection of names that construct one part of the players unique id.
		 */
		sampleNames = new String[] { "Hermann", "Peter", "George", "C4", "User", "Admin", "Operator", "Gymnast",
				"Power", "Jeff" };
		/**
		 * A pre selection of prefixes that construct another part of the players unique
		 * id.
		 */
		samplePrefixes = new String[] { "The", "NotA", "Some", "Maybe", "Quite", "", "Unsure" };
	}
}