package com.dbgq2.netzwerkmalen.server.gameMechanics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.dbgq2.netzwerkmalen.server.helper.FileHelper;
import com.dbgq2.netzwerkmalen.server.helper.Logger;

/**
 * Beschreiben Sie hier die Klasse Spielwoerter.
 * 
 * @author (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Spielwoerter {
	String dateiname;
	String[] woerter;

	public String gibNeueswort() {
		if (leseDateiAus()) {
			return woerter[(int) Math.round((Math.random() * (woerter.length - 1)))];
		} else {
			return null;
		}
	}

	public boolean leseDateiAus() {
		readFileFromGitHubRepoOrFromLocal();
		File Woerterdatei = new File(FileHelper.source() + FileHelper.WORD_CACHE_FILE_NAME);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(Woerterdatei));
			String inhalt = "";
			while (reader.readLine() != null) {
				inhalt = inhalt + ";" + reader.readLine();
			}
			woerter = inhalt.split(";");
			reader.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			Logger.error("File doesn't exists");
			return false;
		}
	}

	/**
	 * WIP Requests an internet connection to the repositories raw Woerter.txt file.
	 * By this all words are always centrally synchronized.
	 * 
	 * @author Aleksander Stepien
	 */

	// fallback in case the internet is down or some shit.
	public boolean readFileFromGitHubRepoOrFromLocal() {
		try {

			// Create URL or WebLink where the Woerter.txt file is located.
			URL url = new URL(
					"https://raw.githubusercontent.com/DBG-Info-Q2/netzwerkmalen/master/PROJECT/server/Server/Woerter.txt");
			// Open the connection to said link. May throw several errors.
			URLConnection connect = url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			String line = null;
			String input = "";
			// Read all the buffered wordlines and put them in a String.
			while ((line = reader.readLine()) != null) {
				input = input + ";" + line;
			}
			String[] gitDownload = input.split(";");

			if (FileHelper.localWordCacheChanged(gitDownload)) {
				File file = new File(FileHelper.source() + FileHelper.WORD_CACHE_FILE_NAME);
				file.delete();

				FileWriter fw = new FileWriter(FileHelper.source() + FileHelper.WORD_CACHE_FILE_NAME);
				BufferedWriter bw = new BufferedWriter(fw);

				for (int i = 0; i < gitDownload.length; i++) {
					bw.write(gitDownload[i]);
					bw.newLine();
				}

				reader.close();
				bw.close();
				return true;
			}

		} catch (Exception e) {
			// There has been some error.
			Logger.error("There was an error reading out the new file from github.com");
			Logger.error("Please check your internet connection and follow the debug");
			e.printStackTrace();
		}
		return false;
	}
}
