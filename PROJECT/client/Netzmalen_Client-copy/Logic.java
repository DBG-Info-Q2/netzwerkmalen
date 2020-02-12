import java.util.*;
public class Logic
{
    int time,color,amountPlayers = 0;
    int[] playerPoints;
    int[][] drawingCoordinates;
    boolean painting,loginProcess,gameRunning,drawer;
    ArrayList chat;
    Netzwerkkommunikation nc;
    
    // Access on by Netzwerkkommunikation. 
    public static Visuals vs;
    
    String word;
    
    /**
     * Initialize the client. Create all Class Objects and start the game loop.
     */
    String recentChatMessage;
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
            time=nc.time;
            drawer=nc.drawer;
            recentChatMessage=nc.recentChatMessage;
            word=nc.word;
            
            color=vs.color;
            
            vs.ratewort=word;
            
            if(!recentChatMessage.equals("")){
                vs.schreiben(recentChatMessage.split(":")[1], recentChatMessage.split(":")[0]); //recent chat message e.g. Hermann:Hallo
            }
            
            
            
            
            gameRunning=nc.gameRunning;
        }
    }
    
    
    
    
    public Logic()
    {}
    
}
