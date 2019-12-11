
/**
 * Unit tests for functions of programm
 * 
 * @author Aleksander Stepien 
 * @version 0.1
 */

import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.*;
public class UnitTests
{
    public static Socket s = null;
    
    public static void createTestSocket(){
        try{
            s=new Socket("localhost",Communication.SERVER_PORT);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void sendTestMessage(String msg){
        try{
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            Logger.log(msg);
            printWriter.print(msg);
            printWriter.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
