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
            time=nc.time;                                   //vars from nc to logic
            drawer=nc.drawer;
            recentChatMessage=nc.recentChatMessage;
            word=nc.word;
            
            color=vs.color;                                 //vars from vs to logic
            
                                                            //vars from logic to nc
            
            vs.ratewort=word;                               //vars from logic to vs
            
            if(!recentChatMessage.equals("")){  //if new message -> visualized in vs
                vs.schreiben(recentChatMessage.split(":")[1], recentChatMessage.split(":")[0]); //recent chat message e.g. Hermann:Hallo
            }
            
            
            
            
            gameRunning=nc.gameRunning;                     //checking if game is still running
        }
    }
    
    
    
    
    public Logic()
    {}
    
}
