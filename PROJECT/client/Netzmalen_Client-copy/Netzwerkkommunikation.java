import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class Netzwerkkommunikation
{
    public static Thread s = null;
    public static Socket socket = null;
    
    int time;
    int chatMsgIndicator;
    int playerAmount;
    int[] points;
    String[] chatMessages = new String[120];
    
    
    public static void createTestSocket(){
            s=new Thread(new Runnable(){
                @Override
                public void run(){
                    try{
                        socket=new Socket("localhost",55555);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        
                        String incomeLine = null;
                        // Lauft durch und ließt Nachrichten, solange wie der Teufel Leben mag.
                        while((incomeLine=in.readLine())!=null){
                            // Verarbeite die einkommende Nachricht.
                            System.out.println("Reveived new paket from server: "+incomeLine);
                            if(true){
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
            e.printStackTrace();
        }
    }

    public void decodeMessage(String msg){
        String[] analyse = msg.split(";");
        
        if(analyse.length == 0){
            return;
        }else{
            switch(analyse[0]){
                case "0" : 
                    break;
                case "1" : time = Integer.parseInt(analyse[1]);
                    break;
                case "2" : chatMessages[chatMsgIndicator+1]=analyse[1];
                           chatMsgIndicator++;
                    break;
                case "3" : 
                    break;
                case "4" : 
                    break;
                case "5" : 
                    break;
                case "6" : 
                    break;
                case "7" : 
                    break;
                case "8" : 
                    break;
    
                    
            
            
            
            
            
            
            }
        
        
        
        
        }
        
    
    }
    
    
}
