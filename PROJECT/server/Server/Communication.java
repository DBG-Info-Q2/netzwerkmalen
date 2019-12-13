/**
 * Beschreiben Sie hier die Klasse Communication.
 * 
 * @author Aleksander Stepien
 * @version pre0.0.0.1.41
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
    
    public void forceShutdown(){
        for(HashMap.Entry<String,COMMSocket>  a: playerList.entrySet()){
            a.getValue().incomeListener.stop();
        }
        Listener.stop();
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
                            String clientID =PlayerNames.findNewName(playerList.keySet().toArray(new String[playerList.keySet().size()]),null); 
                            Logger.log("Created new name for Socket #"+playerList.size()+": "+clientID);
                            
                            COMMSocket socket = new COMMSocket(clientID,newClient);
                            Logger.log("Connected new Player with ID "+clientID);
                            playerList.put(clientID,socket);
                            
                            Communication.sendPaket(clientID,PaketUtil.createLoginUpdatePaket(clientID,true));
                            Communication.sendPaket("-1",PaketUtil.createLoginUpdatePaket(clientID,false));
                        }catch(Exception e){
                            Logger.error("Error listening for new connections");
                            e.printStackTrace();
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
    
    public static void sendPaket(String id, String paket)
    {
        if(id==null||paket==null){
            Logger.error("ID, Paket equal null");
            return;
        }
        if(id=="-1"){
            // Broadcast
            for(HashMap.Entry<String,COMMSocket>  a: Gameserver.GOTT.COMunit.playerList.entrySet()){
                a.getValue().sendPaket(paket);
            }
            return;
        }
        if(Gameserver.GOTT.COMunit.playerList.get(id)==null){
          Logger.error("Player ID "+id+" not found");
          return;
        }
        
        COMMSocket client = Gameserver.GOTT.COMunit.playerList.get(id);
        client.sendPaket(paket);
    }
    
    /**
     * Util to create Names for Players
     */
    public static class PlayerNames{
        public static final String[] sampleNames;
        
        /**
         * Procedure finds an not used name according to Array of names that are already used. 
         * Also adds 1!!!... at the end if all names are being used.
         * suggestedNameAddon can be a suffix to the user name
         */
        public static String findNewName(String[] existingNames,String suggestedNameAddon){
          if(existingNames==null)
            return null;
          
          String name = sampleNames[((int)(Math.random() * (sampleNames.length - 1)))];
          if(suggestedNameAddon!=null)
            name=name+suggestedNameAddon;
          boolean foundExistingName=false;
          boolean nameEmpty=false;
          for(String exName: existingNames)
            if(exName.equalsIgnoreCase(name))
                foundExistingName=true;
            else
                nameEmpty=true;
          
          if(nameEmpty&&foundExistingName)
            return findNewName(existingNames,null);
          
          if(!nameEmpty&&foundExistingName)
             if(suggestedNameAddon==null)
                return findNewName(existingNames,"1");
             else
                return findNewName(existingNames,suggestedNameAddon+"!");
          return name;  
        }
        
        static{
            /**
             * This is where all the names are stored.
             */
            sampleNames=new String[]{"Hermann","Jonas","Peter","Kacka","Bratan","Aran","Ketchup","Majo","Senf","Butterbrot","Netzwerkadmin","H41","C4"};
        }
    }
    
    /**
     * Util to create Pakets 
     */
    public static class PaketUtil{
        public static String createLoginUpdatePaket(String name, boolean du)
        {
            return "0;"+name+";"+du+";";
        }
        public static String createTimeUpdatePaket(int leftoverTime)
        {
            return "1;"+leftoverTime+";";
        }
        public static String createChatUpdatePaket(String recievedText)
        {
            return "2;"+recievedText+";";
        }
        public static String createPointsUpdatePaket(int newPionts, String id)
        {
            return "3;"+newPionts+";"+id+";";
        }
        public static String createDrawUpdatePaket(int x, int y, int colour)
        {
            return "4;"+x+","+y+","+colour+";";
        }
        public static String createRoleUpdatePaket()
        {
            return "5;true;";
        }
        public static String createWordUpdatePaket(String word)
        {
            return "6;"+word+";";
        }
    }
    
    /**
     * Socket with a Thread to asynchrounously receive messages from client
     */
    public static class COMMSocket{
        
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
    
}
