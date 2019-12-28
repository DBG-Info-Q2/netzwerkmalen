package com.dbgq2.netzwerkmalen.server.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import com.dbgq2.netzwerkmalen.server.gameMechanics.Gameserver;
import com.dbgq2.netzwerkmalen.server.helper.Logger;

/**
 * Socket with a Thread to asynchronously receive messages from client
 */
public class COMMSocket {

	private Thread incomeListener;
	private String id;

	private Socket connection;
	private BufferedReader in;
	private PrintWriter out;

	public COMMSocket(String identifier, Socket socket) {
		id = identifier;
		connection = socket;
		Logger.log("Setting up new Client Connection");
		try {
			// Initialize both readers.
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
		} catch (IOException e) {
			Logger.error("Error while Client Connection Creation");
			e.printStackTrace();
		}
		incomeListener = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String incomeLine = null;
					// Lauft durch und ließt Nachrichten, solange wie der Teufel Leben mag.
					while ((incomeLine = in.readLine()) != null) {
						// Verarbeite die einkommende Nachricht.
						Logger.debug("Reveived new paket from " + getID() + ": " + incomeLine);
						Communication.handlePaket(getID(), incomeLine);
					}
					Logger.log("Exit");
				} catch (SocketException e) {
					// The connection to the server has been terminated.
					Logger.log("Client with ID '" + getID() + "' has lost it's connection.");
					disconnect();
				} catch (IOException e) {
					Logger.error("Error receiving message from client with ID " + getID());
					e.printStackTrace();
				}
			}
		});
		incomeListener.start();
	}

	public String getID() {
		return id;
	}

	@SuppressWarnings("deprecation")
	public void disconnect() {
		incomeListener.stop();
		try {
			in.close();
			out.close();
			connection.close();
		} catch (IOException e) {
			Logger.error("Error on disconnecting socket on serverside.");
			e.printStackTrace();
		}
		if (Gameserver.GOTT.COMunit.playerList.containsKey(getID()))
			Gameserver.GOTT.COMunit.playerList.remove(getID());
		else
			Logger.log("There never has been a Socket with ID '" + getID() + "' in the playerlist.");
	}

	public void sendPaket(String paket) {
		if (!connection.isClosed()) {
			out.println(paket);
			out.flush();
		} else {
			Logger.error("There is no established connection to the client. Cannot send paket.");
		}
	}

}
