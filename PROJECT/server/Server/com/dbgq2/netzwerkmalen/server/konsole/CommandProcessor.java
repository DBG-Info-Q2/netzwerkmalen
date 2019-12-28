package com.dbgq2.netzwerkmalen.server.konsole;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.dbgq2.netzwerkmalen.server.helper.Logger;
import com.dbgq2.netzwerkmalen.server.konsole.cmds.Command;

public class CommandProcessor {

	private static ArrayList<Command> commandClasses = new ArrayList<Command>();

	public static Command[] getCommands() {
		return commandClasses.toArray(new Command[0]);
	}

	public void lookAndLoadCommands() {
		try {
			commandClasses.clear();
			Class<?>[] classes = getClasses("com.dbgq2.netzwerkmalen.server.konsole.cmds");
			for (Class<?> classs : classes) {
				if (!classs.getName().equals(Command.class.getName())) {
					Class<?>[] notCommandClassType = classs.getInterfaces();
					for (Class<?> interfacce : notCommandClassType) {
						if (interfacce.getName().equals(Command.class.getName())) {
							// The classes interface is Command
							Command cmd = (Command) classs.newInstance();
							if (findCommand(cmd.giveName()) == null && !canFindAliasesInDatabase(cmd)) {
								commandClasses.add(cmd);
								Logger.log("Found and registered new command: " + cmd.giveName());
							} else {
								Logger.error("Command with name '" + cmd.giveName()
										+ "' or one of its aliases is already registered in command database. Please ask dev to change command name and or aliases.");
							}
							break;
						}
					}
				}
			}

		} catch (ClassNotFoundException | IOException | InstantiationException | IllegalAccessException e) {
			Logger.error("Cannot find command classes.");
			e.printStackTrace();
		}
	}

	private boolean canFindAliasesInDatabase(Command cmd) {
		if (cmd == null || cmd.giveAliases() == null)
			return false;
		for (String alias : cmd.giveAliases())
			if (findCommand(alias) != null)
				return true;
		return false;
	}

	public static Command findCommand(String commandNameOrAlias) {
		for (Command cmd : getCommands()) {
			if (cmd.giveName().equalsIgnoreCase(commandNameOrAlias))
				return cmd;
			if (cmd.giveAliases() != null)
				for (String alias : cmd.giveAliases())
					if (alias.equalsIgnoreCase(commandNameOrAlias))
						return cmd;

		}
		return null;
	}

	/**
	 * Scans all classes accessible from the context class loader which belong to
	 * the given package and subpackages.
	 *
	 * @author https://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
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
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @author https://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
	 * 
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base
	 *                    directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}
}
