import java.util.*;
public class Logic
{
    int time,color,amountPlayers = 0;
    int[] playerPoints;
    int[][] drawingCoordinates;
    boolean painting,loginProcess,gameRunning;
    ArrayList chat;
    Netzwerkkommunikation nc;
    Visuals vs;
    
    String drawnWord;
    
    public void gameloop(){
        nc = new Netzwerkkommunikation();   //init
        vs = new Visuals();
        
        loginProcess = true;
        gameRunning = true;
        
  
        while(loginProcess){
            
            
        }
                                            //init vars for game
        
        
        
        while(gameRunning){
            //repeating stuff
            
            
        }
    }
    
    
    
    public Logic()
    {}
    
}
