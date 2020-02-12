import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class Netzwerkkommunikation
{
    public static Thread s = null;
    public static Socket socket = null;
<<<<<<< HEAD

    static boolean du;
=======
>>>>>>> 8ffebe991ca18a3253f3fc2385f570223d430866
    static boolean drawer;
    static boolean gameRunning;
    static int time;
    static int playerAmount;
    static int winner;
    static String word;
    static String playerName;
    static String recentChatMessage;
    int[] points;

<<<<<<< HEAD
    
    

    static String[] chatMessages = new String[120];


=======
>>>>>>> 8ffebe991ca18a3253f3fc2385f570223d430866
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
     * Alte Version bitte nicht benutzen.
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
<<<<<<< HEAD

=======
    
    
>>>>>>> 8ffebe991ca18a3253f3fc2385f570223d430866
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
<<<<<<< HEAD
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
=======
                case "0" : playerName = analyse[1];
                   if(Boolean.parseBoolean(analyse[2])){
                       //TODO: change player name
                   }else{
                        //TODO: Add the player name to the scoreboard
                    }
                    break;
                case "1" : time = Integer.parseInt(analyse[1]); //time
                    break;
<<<<<<< HEAD
                case "2" : recentChatMessage = analyse[1];      //msg
=======
                case "2" : recentChatMessage = analyse[1];
>>>>>>> 8ffebe991ca18a3253f3fc2385f570223d430866
>>>>>>> 43d14e8864fedd027df018a863e0e57cab44d051
                    break;
                case "3" : 
                    // Point update Paket. param0: newPointcount, param1: ID of player
                    break;
                case "4" :
                    // Paket update Drawing. Only valid if !drawer
                    try{
<<<<<<< HEAD
                        int x,y,x2,y2,color;
                    }catch(Exception e){}
                    // Logic.vs.zeichne();
                    break;
                case "5" : 
                    drawer = Boolean.parseBoolean(analyse[1]);      
                    break;
=======
                        int x=Integer.parseInt(analyse[1]),y=Integer.parseInt(analyse[2]),x2=Integer.parseInt(analyse[3]),y2=Integer.parseInt(analyse[4]),color=Integer.parseInt(analyse[5]);
                        // Run in Visuals a zeichne to display the paket.
                        Logic.vs.zeichne(x,y,x2,y2,color);
                    }catch(Exception e){
                        System.err.println("Error receiving draw paket ..");
                        e.printStackTrace();
                    }
                break;
                case "5" : 
                    drawer = Boolean.parseBoolean(analyse[1]);
                    
                break;
>>>>>>> 43d14e8864fedd027df018a863e0e57cab44d051
                case "6" : word = analyse[1];
                    break;
                case "7" : gameRunning = Boolean.parseBoolean(analyse[1]);
                    break;
                case "8" : winner = analyse[1];
                    break;
            }
        }
    } 
}
