package tests;

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;

import comm.Communication;

import helper.Logger;
/**
 * Unit tests for functions of programm
 * 
 * @author Aleksander Stepien 
 * @version 0.1
 */
public class UnitTests
{
    public static Thread s = null;
    public static Socket socket = null;
    
    
    public static void createTestSocket(){
        
            s=new Thread(new Runnable(){
            
                @Override
                public void run(){
                    
                    try{
                        socket=new Socket("localhost",Communication.SERVER_PORT);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        
                        String incomeLine = null;
                        // Lauft durch und ließt Nachrichten, solange wie der Teufel Leben mag.
                        while((incomeLine=in.readLine())!=null){
                            // Verarbeite die einkommende Nachricht.
                            Logger.logPr("Reveived new paket from server: "+incomeLine); 
                            
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
            Logger.logPr("socket sending "+msg);
            printWriter.println(msg);
            printWriter.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
