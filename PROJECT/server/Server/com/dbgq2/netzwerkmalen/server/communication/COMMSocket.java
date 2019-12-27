package com.dbgq2.netzwerkmalen.server.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.dbgq2.netzwerkmalen.server.helper.Logger;
/**
 * Socket with a Thread to asynchronously receive messages from client
 */
//TODO: Handle disconnection of a Socket.
public class COMMSocket{

    public Thread incomeListener;
    public String id;
    public Socket connection;
    public BufferedReader in;
    public PrintWriter out; 

    public COMMSocket(String identifier,Socket socket){
        id=identifier;
        connection=socket;
        Logger.log("Setting up new Client Connection");
        try{
            // Initialize both readers.
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
        }catch(IOException e){
            Logger.error("Error while Client Connection Creation");
            e.printStackTrace();
        }
        incomeListener=new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        String incomeLine = null;
                        // Lauft durch und ließt Nachrichten, solange wie der Teufel Leben mag.
                        while((incomeLine=in.readLine())!=null){
                            // Verarbeite die einkommende Nachricht.
                            Logger.debug("Reveived new paket from "+id+": "+incomeLine); 
                            Communication.handlePaket(id,incomeLine);
                        }
                        Logger.log("Exit");
                    }catch(IOException e){
                        Logger.error("Error receiving message from client with ID "+id);
                    }
                }
            });
        incomeListener.start();
    }

    public void sendPaket(String paket) {
        out.println(paket);
        out.flush();
    }

}
