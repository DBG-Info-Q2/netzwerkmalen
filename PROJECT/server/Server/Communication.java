
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
                    while(serverRunning){
                        try{
                            Socket newClient = SocketServer.accept();
                            
                            
                        }catch(Exception e){
                            Logger.error("Error listening for new connections");
                        }
                    }
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
    
    public static class COMMSocket extends Socket{
        
        public Thread incomeListener;
        public String id;
        
        public COMMSocket(String identifier){
            super();
            id=identifier;
            Logger.log("Setting up new Client Connection");
            incomeListener=new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getInputStream()));
                        
                        //TODO:while(){
                        //}
                    }catch(IOException e){
                        Logger.error("Error receiving message from client with ID "+id);
                    }
                }
            });
            incomeListener.start();
        }
    
       public void schreibeNachricht(String nachricht) throws IOException {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(getOutputStream()));
            printWriter.print(nachricht);
            printWriter.flush();
        }
        
    
    }
    
}
