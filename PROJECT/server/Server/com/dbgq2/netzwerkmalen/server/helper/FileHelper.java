package com.dbgq2.netzwerkmalen.server.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import com.dbgq2.netzwerkmalen.server.Main;

public class FileHelper {

	public static final String WORD_CACHE_FILE_NAME = "spielwoerter_cache.txt";

	/**
	 * @return true if local cache not equal the currentCache
	 */
	public static boolean localWordCacheChanged(String[] currentCache) {
		if (currentCache == null)
			return true;

		String newCacheString = "";
		for (String s : currentCache) {
			newCacheString += s + "\\n";
		}

		if (!localCacheCheck())
			return true;

		// Lese Datei aus.
		String fileCacheString = "";
		File wordCacheFile = new File(FileHelper.source() + WORD_CACHE_FILE_NAME);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(wordCacheFile));

			while (reader.readLine() != null) {
				fileCacheString = reader.readLine() + "\\n";
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("There was an error reading the file.");
			e.printStackTrace();
			return true;
		}

		return newCacheString.equals(fileCacheString);
	}

	public static boolean localCacheCheck() { // existiert schon eine Datei
		return new File(source() + WORD_CACHE_FILE_NAME).exists();
	}

	public static String source() { // gibt Pfad an
		String absoluteRuntimePath = null;
		try {
			absoluteRuntimePath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI())
					.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			// There is no running directory bruh..
			System.err.println("There was an error with the programm URI");
		}
		return absoluteRuntimePath;
	}
}
