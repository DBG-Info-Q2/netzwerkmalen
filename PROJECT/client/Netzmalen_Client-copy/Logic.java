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
        login();
        
        gameRunning = true;
        game();
    }
    
    public void login()
    {
        nc.createSocket();
        while(loginProcess)
        {
            
            
        }
    }
    
    public void game()
    {
        while(gameRunning){
            
            
            
        }
    }
    
    
    
    
    public Logic()
    {}
    
}
