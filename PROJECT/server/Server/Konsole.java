
/**
 * Die Konsole ist das Eingabeinterface für den Server. Hier können Befehle
 * 
 * @author Aleksander Stepien
 * @version 2.2
 */

import java.io.*;
import java.lang.*;
public class Konsole extends Thread
{
    public Konsole(){
    
    }
    
    @Override
    public void run(){
        while(true){
            try{
                BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
                
                String cmd = null;
                while((cmd=reader.readLine())!=null){
                    handleCommand(cmd);
                }
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void handleCommand(String command){
        if(command==null||command.equals("")){
            error("Command cannot be empty");
            return;
        }
        String[] commands = command.split(" ",2);
        String mainCommand = commands[0].toLowerCase();
        
        switch (mainCommand){
        
            case "help":
                log("Available Commands are: "+"help;exit;playersToStart<1,2,3...>");
                break;
            case "exit":
                Gameserver.GOTT.stopGame();
                break;
            case "playerstostart": 
                if(commands.length==2){
                    String number = commands[1].split(" ")[0];
                    log(number);
                }else{
                    error("Format for playersToStart: command number");
                }
                break;
            default: 
                error("Command "+mainCommand+" cannot be found. Use help to get a list of possible commands");
                break;
        }
        
        /*if(commands.length==1){
            log("cmd: "+mainCommand);
        } else if(commands.length>1){
            String[] args = commands[1].split(" ");
        
            log("cmd: "+mainCommand);
            for(String arg: args)
            log("arg: "+arg);
        }*/
        
        return;
    }
    
    public void error(String error){
        Logger.error("CommandProcessor: "+error);
    }
    
    public void log(String log){
        Logger.logPr("CommandProcessor: "+log);
    }
}
