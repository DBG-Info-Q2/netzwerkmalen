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
    static String recentChatMessage;
    int[] points;
<<<<<<< HEAD
    
    
=======
    static String[] chatMessages = new String[120];
<<<<<<< HEAD

    /**
     * Creates the communication Socket to the server. Tries to establish a connection.
     * Incoming messages get read in a thread and handled through #decodeMessage(String);
     */
>>>>>>> de4ce85c1b1674c03cc68b632bff4842ad5f684c
=======
    
>>>>>>> facd73c31f59b81bec303d0982b1f094d21942c0
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
                            sendMessage(ID);
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
    
    
    
    
    
    
    
    
    
    
    	public string leseIDAus() {
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
	}
    
    //File "ID" not yet implimented
    
    
    
    
    
    
    public static void sendMessage(String msg){
        try{
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("socket sending "+msg);
            printWriter.println(msg);
            printWriter.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void decodeMessage(String msg){

        String[] analyse = msg.split(";");
        
        if(analyse.length == 0){
            return;
        }else{
            switch(analyse[0]){
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> facd73c31f59b81bec303d0982b1f094d21942c0
                case "0" : du = Boolean.parseBoolean(analyse[1]);
                    break;
                case "1" : time = Integer.parseInt(analyse[1]);
                    break;
<<<<<<< HEAD
                case "2" : recentChatMessage = analyse[1];
=======
                case "0" : 
                    // Paket User Login. param0: name, param1: isHimself
                    String name = analyse[1];
                    boolean du = Boolean.parseBoolean(analyse[2]);
                    if(du){
                        //TODO: Set the players own name. For chat or other purposes..
                    }else{
                        //TODO: Add the player name to the scoreboard
                    }
>>>>>>> de4ce85c1b1674c03cc68b632bff4842ad5f684c
=======
                case "2" : chatMessages[chatMsgIndicator+1]=analyse[1];
                           chatMsgIndicator++;
>>>>>>> facd73c31f59b81bec303d0982b1f094d21942c0
                    break;
                case "3" : 
                    break;
                case "4" : 
                    break;
                case "5" : drawer = Boolean.parseBoolean(analyse[1]);
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
