
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
                            Logger.log("Connecting new Player with ID player"+playerList.size());
                            playerList.put("player"+playerList.size(),socket);
                            
                        }catch(Exception e){
                            Logger.error("Error listening for new connections");
                        }
                    }
                }
            });
            Listener.start();
        }
    }
    
    public static void handlePaket(String id, String paket)
    {
        String[]anaylse = paket.split(";");
        switch (anaylse[0])
        {
            case "0": Logger.error("False protocol. Client "+id+" send LoginUpdatePaket."); break;
            case "1": Logger.error("False protocol. Client "+id+" send TimeUpdatePaket."); break;
            case "2": 
                Logger.log("Client "+id+" send ChatUpdatePaket."); 
                if (Eingabekontrolle.checkWord(anaylse[1]))
                {
                    Gameserver.GOTT.points.calculateANDaddPoints(id, id.equals(Gameserver.GOTT.drawerID));
                }
                else
                {
                    sendPaket("-1", paket);
                }
                break;
            case "3": Logger.error("False protocol. Client "+id+" send PointsUpdatePaket."); break;
            case "4":
                Logger.log("Client "+id+" send DrawUpdatePaket.");
                sendPaket("-1", paket);
                break;
            case "5": Logger.error("False protocol. Client "+id+" send RoleUpdatePaket."); break;
            case "6": Logger.error("False protocol. Client "+id+" send WordUpdatePaket."); break;
            default:  Logger.error("False protocol. Client "+id+" send trash."); break;
        }
    }
    
    public void sendPaket(String id, String paket)
    {
        
    }
    
    public static class PaketUtil{
        public String createLoginUpdatePaket(String name, boolean du)
        {
            return ";0;"+name+";"+du+";";
        }
        public String createTimeUpdatePaket(int leftoverTime)
        {
            return ";1;"+leftoverTime+";";
        }
        public String createChatUpdatePaket(String recievedText)
        {
            return ";2;"+recievedText+";";
        }
        public String createPointsUpdatePaket(int newPionts, String id)
        {
            return ";3;"+newPionts+";"+id+";";
        }
        public String createDrawUpdatePaket(int x, int y, int colour)
        {
            return ";4;"+x+","+y+","+colour+";";
        }
        public String createRoleUpdatePaket()
        {
            return ";5;true;";
        }
        public String createWordUpdatePaket(String word)
        {
            return ";6;"+word+";";
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
                        while((incomeLine=bufferedReader.readLine())!=null){
                            // Verarbeite die einkommende Nachricht.
                            Logger.log("Neue Nachricht von "+id+":"+incomeLine);    
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
