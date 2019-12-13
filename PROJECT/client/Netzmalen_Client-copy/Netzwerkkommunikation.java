import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

public class Netzwerkkommunikation
{
    public static Thread s = null;
    public static Socket socket = null;

    
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
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
            
            s.start();
       
    }
    
    public static void sendTestMessage(String msg){
        try{
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("socket sending "+msg);
            printWriter.println(msg);
            printWriter.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
