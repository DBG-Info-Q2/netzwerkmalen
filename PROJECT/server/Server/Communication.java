
/**
 * Beschreiben Sie hier die Klasse Communication.
 * 
 * @author Aleksander Stepien
 * @version pre0.0.0.0.0.0.0.2
 */
import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class Communication
{
    public HashMap<String,Socket> playerList; 
    public ServerSocket SocketServer;
    public Thread Listener;
    private boolean serverRunning = false;
    
    public static final int SERVER_PORT=55555;
    
    
    public Communication(){
        playerList = new HashMap<String,Socket>();
    }
    
    public void startListener() //thread mit Schleife
    {
        if(!serverRunning){
            serverRunning = true;
            try{
                Logger.log("Starting ServerSocket...");
                SocketServer=new ServerSocket(SERVER_PORT);
            }catch(IOException e){
                Logger.error("Error when creation of Server");
            }
            
            Listener = new Thread(new Runnable(){
            
                @Override
                public void run(){
                    Logger.log("Listening for new connections..");
                }
            });
            Listener.start();
        }
    }
    
    public void handlePaket(String id, String paket)
    {
        
    }
    
    public void sendPaket(String id, String paket)
    {
        
    }
}
