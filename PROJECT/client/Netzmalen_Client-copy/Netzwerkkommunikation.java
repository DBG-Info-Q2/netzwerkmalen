import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class Netzwerkkommunikation
{
    public static Thread s = null;
    public static Socket socket = null;

    static boolean du;
    static boolean drawer;
    static boolean gameRunning;
    static int time;
    static int chatMsgIndicator;
    static int playerAmount;
    static int winner;
    static String word;
    static String playerName;
    static String recentChatMessage;
    int[] points;

    
    

    static String[] chatMessages = new String[120];


    /**
     * Creates the communication Socket to the server. Tries to establish a connection.
     * Incoming messages get read in a thread and handled through #decodeMessage(String);
     */

    public static void createSocket(){
            s=new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        socket=new Socket("localhost",55555);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        
                        String incomeLine = null;
                        // Leauft durch und ließt Nachrichten, solange wie der Teufel Leben mag.
                        while((incomeLine=in.readLine())!=null){
                            // Verarbeite die einkommende Nachricht.
                            decodeMessage(incomeLine);
                            if(true){
                            //sendMessage(ID);
                            System.out.println("Game starting");
                            }
                            
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            );
            s.start();
    }
    
    
    
    
    
    
    
    
    
    /*
        public String leseIDAus() {
        readFileFromGitHubRepoOrFromLocal();
        File Woerterdatei = new File(FileHelper.source() + FileHelper.ID);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(Woerterdatei));
            String ID = "";
            ID = reader.readLine();
            reader.close();
            return ID;
        } catch (IOException e) {
            e.printStackTrace();
            Logger.error("ID doesn't exists");
            return "";
        }
    }*/
    
    /*
    Neue Version
    
    public void leseIDaus()
    {
        
        File IDdatei = new File("T:"+(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI())
                    .getPath()+"/ID");
        if (!IDdatei.canRead() || !IDdatei.isFile())
            {System.out.println("Dateifehler");
                System.exit(0);}
        BufferedReader in = null;
        try {
            String zeile = null;
            String ID="";
            while ((zeile = in.readLine()) != null) {
                ID = zeile;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        } 
        
    }
    
    }*/

    //File "ID" not yet implimented
    
    
    
    
    
    
    public static void sendMessage(String msg){
        try{
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("socket sending "+msg);
            printWriter.println(msg);
            printWriter.flush();
        }catch(Exception e){
            //e.printStackTrace();
        }
    }


    public static void decodeMessage(String msg){

        String[] analyse = msg.split(";");
        
        if(analyse.length == 0){
            return;
        }else{
            switch(analyse[0]){
                case "0" : 
                    // Paket User Login. param0: name, param1: isHimself
                    String name = analyse[1];
                    boolean du = Boolean.parseBoolean(analyse[2]);
                    if(du){

                        //TODO: Set the players own name. For chat or other purposes..
                    }else{
                        //TODO: Add the player name to the scoreboard
                    }
                    break;
                case "1" : time = Integer.parseInt(analyse[1]);
                    break;
                case "2" : 
                           //Visuals.schreiben(analyse[1], "Gert");
                           chatMessages[chatMsgIndicator+1]=analyse[1];
                           chatMsgIndicator++;
                    break;
                case "3" : 
                    // Point update Paket. param0: newPointcount, param1: ID of player
                break;
                case "4" :
                    // Paket update Drawing. Only valid if !drawer
                    try{
                        int x,y,x2,y2,color;
                    }catch(Exception e){}
                    // Logic.vs.zeichne();
                break;
                case "5" : 
                
                    drawer = Boolean.parseBoolean(analyse[1]);
                    
                break;
                case "6" : word = analyse[1];
                    break;
                case "7" : gameRunning = Boolean.parseBoolean(analyse[1]);
                    break;
                case "8" : winner = Integer.parseInt(analyse[1]);
                    break;
            }
        }
    } 
}
