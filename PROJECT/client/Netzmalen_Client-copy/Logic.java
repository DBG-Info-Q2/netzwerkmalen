import java.util.*;
public class Logic
{
    /*
    int time,color,amountPlayers = 0;
    int[] playerPoints;
    int[][] drawingCoordinates;
    boolean painting,loginProcess,drawer;
    ArrayList chat;
    Netzwerkkommunikation nc;

    // Access on by Netzwerkkommunikation. 
    public static Visuals vs;

    String word;

    
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

    */
    
    int zustand;
    String id;
    boolean threadisRunning = false;
    boolean gameRunning = false;
    public Thread game;

    public Visuals vis = new Visuals();
    public Netzwerkkommunikation com = new Netzwerkkommunikation();
    public IDManager idM = new IDManager();

    public static Logic SATAN;

    public void intitializeClient()
    {
        com.createSocket();
    }

    public void logIn()
    {
        
    }

    public void gameLoop()
    {
        if (!threadisRunning) {
            threadisRunning = true;
            game = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (gameRunning) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            game.start();
        }
    }
}
