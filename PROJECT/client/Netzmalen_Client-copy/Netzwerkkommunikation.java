import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;


public class Netzwerkkommunikation
{
    public static Thread s = null;
    public static Socket socket = null;
    

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
                            //ID=leseIDaus();
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
                        Logic.SATAN.logIn(analyse[1]);
                        //TODO: Set the players own name. For chat or other purposes..
                    }else{
                        //TODO: Add the player name to the scoreboard
                    }
                    break;
                case "1" : Integer.parseInt(analyse[1]);
                    break;
                case "2" : Logic.SATAN.vis.schreiben(analyse[1]);     //msg
                    break;
                case "3" : 
                    // Point update Paket. param0: newPointcount, param1: ID of player
                    break;
                case "4" :
                    // Paket update Drawing. Only valid if !drawer
                    try{
                        Logic.SATAN.vis.zeichne(Integer.parseInt(analyse[1]), Integer.parseInt(analyse[2]), Integer.parseInt(analyse[3]), Integer.parseInt(analyse[4]), Integer.parseInt(analyse[5]));
                        // Run in Visuals a zeichne to display the paket.
                    }catch(Exception e){
                        System.err.println("Error receiving draw paket ..");
                        e.printStackTrace();
                    }
                    break;
                case "5" : 
                    Logic.SATAN.setRole(Boolean.parseBoolean(analyse[1]));
                    break;
                case "6" : Logic.SATAN.setWord(analyse[1]);
                    break;
                case "7" : if (Boolean.parseBoolean(analyse[1])){Logic.SATAN.startGame();}
                
                    break;
                case "8" : String winner = analyse[1];
                    break;
            }
        }
    } 
}
