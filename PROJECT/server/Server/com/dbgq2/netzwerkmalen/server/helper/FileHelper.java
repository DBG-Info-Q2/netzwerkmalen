package com.dbgq2.netzwerkmalen.server.helper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class FileHelper {
	public static boolean localCacheCheck() // existiert schon eine Datei
	{
		return false;

	}

	public static String source() // gibt Pfad an
	{
		try {
			getClasses("com.dbgq2.netzwerkmalen.server.konsole");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return "H:\\____________Informatik____________\\NetzmalenProjekt\\JuhuEsFunktioniert\\PROJECT\\server\\Server\\";
	}

	private static Class<?>[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		for (File directory : dirs) {
			System.out.println(directory);
		}
		return classes.toArray(new Class[classes.size()]);
	}
}
