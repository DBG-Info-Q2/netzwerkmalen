package com.dbgq2.netzwerkmalen.server.communication;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import com.dbgq2.netzwerkmalen.server.gameMechanics.Eingabekontrolle;
import com.dbgq2.netzwerkmalen.server.gameMechanics.Gameserver;
import com.dbgq2.netzwerkmalen.server.helper.Logger;

/**
 * Beschreiben Sie hier die Klasse Communication.
 * 
 * @author Aleksander Stepien
 * @version alpha 1.4
 */
public class Communication {
	protected HashMap<String, COMMSocket> playerList;
	public ServerSocket SocketServer;
	public Thread Listener;

	// Defines whether or not the server should be running. Please use startListener
	// and forceShutdown instead of setting this variable.
	private boolean serverRunning = false;

	// Defines the port, where all clients should access.
	public static final int SERVER_PORT = 55555;

	public static final String SERVER_MESSAGE_PREFIX = "[SERVER]: ";

	/**
	 * Creator. Setting some variables on creation to their default states.
	 */
	public Communication() {
		playerList = new HashMap<String, COMMSocket>();
		SocketServer = null;
		Listener = null;
	}

	/**
	 * Creates the initial listening Thread and Socket for incoming connections.
	 */
	public void startListener() {
		// Check if there is already an instance of the Server running.
		if (!serverRunning) {
			serverRunning = true;
			Logger.log("Starting ServerSocket at PORT '" + SERVER_PORT + "'...");
			try {
				SocketServer = new ServerSocket(SERVER_PORT); // Create ServerSocket that handles first incoming
																// connections.
			} catch (IOException e) {
				// Error on creation. PORT could be occupied or firewall is denying creation.
				Logger.error("Error when creation of Server");
				// Print detailed error report.
				e.printStackTrace();
				return;
			}

			// Create asynchronous Loop in order to listen to incoming connections without
			// interrupting the gameplay.
			Listener = new Thread(new Runnable() {

				@Override
				public void run() {
					Logger.log("Listening for new connections..");
					while (serverRunning) { // As long as the server should be running. If for some reason serverRunning
											// gets set to false, Thread will gracefully come to an end.
						try {
							Socket newClient = SocketServer.accept(); // Halting listener Thread until new Connection
																		// established with client.

							// Create an Unique ID for the Player.
							String clientID = PlayerNamesUtil
									.findNewName(playerList.keySet().toArray(new String[playerList.keySet().size()])); // Converts
																														// current
																														// Player
																														// List
																														// to
																														// Names
																														// Array
							Logger.log("Created new name for Socket #" + playerList.size() + ": " + clientID);

							// Create Subhandler Custom class for handling the Socket
							COMMSocket socket = new COMMSocket(clientID, newClient);
							Logger.log("Connected new Player with ID " + clientID);

							// Add Subhandler with ID to players List
							playerList.put(clientID, socket);

							// Update the clients name to Unique ID
							Communication.sendPaket(clientID, PaketUtil.createLoginUpdatePaket(clientID, true));
							// Send out a new login paket to all connected clients in order to update
							// leaderboard / welcome message.
							Communication.sendPaket("-1", PaketUtil.createLoginUpdatePaket(clientID, false));
						} catch (Exception e) {
							// Handle occuring errors.
							Logger.error("Error listening for new connections");
							e.printStackTrace();
							// No return in order for the loop to continue running.
							continue;
						}
					}
				}
			});
			// Run the listener Thread.
			Listener.start();
		} else {
			// Self-explanitory
			Logger.error(
					"There is already a SocketServer running and listening for incoming connections on the serverside.");
		}
	}

	/**
	 * This procedure closes all availible Threads and disables all possible traffic
	 * to server
	 */
	@SuppressWarnings("deprecation")
	public void forceShutdown() {
		// Each communicationthread gets disabled
		for (HashMap.Entry<String, COMMSocket> a : playerList.entrySet()) {
			a.getValue().disconnect();
		}
		// Nullhandle if no listener running
		if (Listener != null)
			Listener.stop();
		// Server has been disabled
		serverRunning = false;
	}

	/**
	 * This procedure handles an incoming Paket from a client with a given id. If
	 * the protocol is correct, so the Paket is destin for the server, it gets
	 * handled.
	 * 
	 * @author Lord_von_X
	 */
	public static void handlePaket(String id, String paket) {
		String[] analyse = paket.split(";"); // Split Paket into argument groups. First one is always Paket type.
												// Following are Paket argumentation.
		if (analyse.length == 0) {
			Logger.error("False protocol. Paket length cannot be 0");
			return;
		}
		switch (analyse[0]) {
		case "0":
			Logger.error("False protocol. Client " + id + " sent LoginUpdatePaket.");
			break; // Paket destin for client
		case "1":
			Logger.error("False protocol. Client " + id + " sent TimeUpdatePaket.");
			break; // Paket destin for client
		case "2": // Chat Paket used to communicatie
			Logger.log("Client '" + id + "' sent ChatUpdatePaket: " + analyse[1]);
			// Analyse, if the chat, the player has sent contains the gameword.
			if (Eingabekontrolle.checkWord(analyse[1])) {
				// Word is contained, calculate points for the user and handle adding them to
				// him.
				Gameserver.GOTT.points.calculateANDaddPoints(id, id.equals(Gameserver.GOTT.drawerID));
				// Increment amount of right guesses for the current game.
				Gameserver.GOTT.currentRightGuesses++;
			} else {
				sendPaket("-1", analyse[0]+";"+id+": "+analyse[1]+";");
			}
			break;
		case "3":
			Logger.error("False protocol. Client " + id + " sent PointsUpdatePaket.");
			break; // Paket destin for client
		case "4":
			// WIP
			// Logger.log("Client "+id+" sent DrawUpdatePaket.");
			sendPaket("-1", paket);
			break;
		case "5":
			Logger.error("False protocol. Client " + id + " sent RoleUpdatePaket.");
			break; // Paket destin for client
		case "6":
			Logger.error("False protocol. Client " + id + " sent WordUpdatePaket.");
			break; // Paket destin for client
		case "7":
			Logger.error("False protocol. Client " + id + " sent GameStateUpdatePaket.");
			break; // Paket destin for client
		case "8":
			Logger.error("False protocol. Client " + id + " sent GameEndUpdatePaket.");
			break; // Paket destin for client
		default:
			Logger.error("False protocol. Client " + id + " sent trash.");
			break; // Bullshit
		}
		// Yep. You have reached the end of our beatiful handler.
	}

	/**
	 * This procedure sends out Pakets to a given client or broadcasts it ("-1") to
	 * all clients.
	 */
	public static void sendPaket(String id, String paket) {
		// Fault-Check
		if (id == null || paket == null) {
			Logger.error("ID, Paket equal null");
			return;
		}
		// Check if Any one of these are null and could throw an error later.
		if (Gameserver.GOTT == null || Gameserver.GOTT.COMunit == null || Gameserver.GOTT.COMunit.playerList == null) {
			Logger.error("PlayerList is not accessible.");
			return;
		}
		if (id == "-1") {
			// Broadcast-Mode. Send Paket to all connected clients.
			for (HashMap.Entry<String, COMMSocket> a : Gameserver.GOTT.COMunit.playerList.entrySet()) {
				// COMMSocket has a custom per Socket method to send Data.
				a.getValue().sendPaket(paket);
			}
			// End of discussion.
			return;
		}
		// Send Paket to defined client.
		if (Gameserver.GOTT.COMunit.playerList.get(id) == null) {// Check if client exists in database.
			// Client with ID could not be found.
			Logger.error("Player ID " + id + " not found");
			return;
		}

		// Get the COMMSocket for the client in question and use its build in procedure
		// to send the Paket.
		COMMSocket client = Gameserver.GOTT.COMunit.playerList.get(id);
		client.sendPaket(paket);

		// Tadaa. Thats all.
	}

	public void broadcastMessage(String message) {
		if(message==null) {
			Logger.error("Message cannot be null");
			return;
		}
		sendPaket("-1", PaketUtil.createChatUpdatePaket(message));
	}

	public void sendMessage(String id, String message) {
		if(id==null||message==null) {
			Logger.error("Player ID or Message cannot be null");
			return;
		}
		if(!isOnline(id)) {
			Logger.error("Player with ID '"+id+"' is not online!");
			return;
		}
		COMMSocket player = getPlayer(id);
		player.sendMessage(message);
	}

	public int getAmountOfOnlinePlayers() {
		if (playerList == null)
			return 0;
		return playerList.size();
	}

	public ArrayList<String> getAllPlayerIDs() {
		if (playerList == null)
			return null;
		return new ArrayList<String>(playerList.keySet());
	}

	public ArrayList<COMMSocket> getAllPlayers() {
		if (playerList == null)
			return null;
		return new ArrayList<COMMSocket>(playerList.values());
	}

	public COMMSocket getPlayer(String id) {
		if (!isOnline(id))
			return null;
		return playerList.get(id);
	}

	public boolean isOnline(String id) {
		if (id == null || playerList == null)
			return false;
		return playerList.containsKey(id);
	}
}
