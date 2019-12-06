
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
    public HashMap<String,COMMSocket> playerList; 
    public ServerSocket SocketServer;
    public Thread Listener;
    private boolean serverRunning = false;
    
    public static final int SERVER_PORT=55555;
    
    
    public Communication(){
        playerList = new HashMap<String,COMMSocket>();
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
                            
                            COMMSocket socket = new COMMSocket("test",newClient);
                            
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
    
    public static class PaketUtil{
        public String createTimeUpdatePaket(){
            return null;
        }
    }
    
    public static class COMMSocket{
        
        public Thread incomeListener;
        public String id;
        public Socket connection;
        
        public COMMSocket(String identifier,Socket socket){
            super();
            id=identifier;
            connection=socket;
            Logger.log("Setting up new Client Connection");
            incomeListener=new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String incomeLine = null;
                        // Lauft durch und ließt Nachrichten, solange wie der Teufel Leben mag.
                        while(true){
                            // Lese Lines aus.
                            if((incomeLine=bufferedReader.readLine())!=null){
                                // Verarbeite die einkommende Nachricht.
                                Logger.log("Neue Nachricht von "+id+":"+incomeLine);
                            }
                                
                        }
                    }catch(IOException e){
                        Logger.error("Error receiving message from client with ID "+id);
                    }
                }
            });
            incomeListener.start();
        }
    
       public void sendPaket(String paket) throws IOException {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
            printWriter.print(paket);
            printWriter.flush();
        }
        
    
    }
    
}
