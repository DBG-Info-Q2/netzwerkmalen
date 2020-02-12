import java.util.*;
public class Logic
{
    int time,color,amountPlayers = 0;
    int[] playerPoints;
    int[][] drawingCoordinates;
    boolean painting,loginProcess,gameRunning,drawer;
    ArrayList chat;
    Netzwerkkommunikation nc;
    Visuals vs;
    
    String drawnWord;
    
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
            
            color=vs.color;
            
            if(!recentChatMessage.equals("")){
                vs.schreiben(recentChatMessage.split(":")[1], recentChatMessage.split(":")[0]); //recent chat message e.g. Hermann:Hallo
            }
            
            
            
            
            gameRunning=nc.gameRunning;
        }
    }
    
    public void setRecentMsg(String ff){
        recentChatMessage = "kecko";
    }
    
    
    
    
    public Logic()
    {}
    
}
