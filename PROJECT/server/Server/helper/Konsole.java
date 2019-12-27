package helper;

import java.io.*;
import java.lang.*;

import gameMech.Gameserver;
/**
 * Die Konsole ist das Eingabeinterface für den Server. Hier können Befehle
 * 
 * @author Aleksander Stepien
 * @version 2.2
 */
public class Konsole extends Thread
{
    public Konsole(){
        super();
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
                log("Available Commands are: "+"<help>, <exit>, <playersToStart [number]>");
                break;
            case "exit":
                Gameserver.GOTT.stopGame();
                break;
            case "stop":
                Gameserver.GOTT.forceStopGame();
                break;
            case "playerstostart": 
                if(commands.length==2){
                    String number = commands[1].split(" ")[0];
                    try{
                        Integer i = Integer.parseInt(number);
                        //TODO: Gameserver.GOTT.maxPlayer = i
                    }catch(NumberFormatException e){
                        error("Given argument '"+number+"' is not an int!");
                    }
                }else{
                    error("Format for command: <playersToStart [number]>");
                }
                break;
            default: 
                error("Command '"+mainCommand+"' cannot be found. Use <help> to get a list of possible commands");
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
