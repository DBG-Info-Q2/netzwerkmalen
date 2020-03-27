import java.util.*;
public class Logic
{
    int zustand;
    String id;
    boolean threadisRunning = false;
    boolean gameRunning = false;
    boolean role;
    String spielwort;
    public Thread game;
    

    public Visuals vis = new Visuals();
    public Netzwerkkommunikation com = new Netzwerkkommunikation();
    public IDManager idM = new IDManager();

    public static Logic SATAN;
    
    // When creating the Class object, also instantiate the SATAN singleton.
    // , so other classes can access.
    public Logic(){
        SATAN=this;
        
    }

    // This procedure starts the client. IT is to be called when the client has to be started
    public void intitializeClient()
    {
        com.createSocket();
        vis.setup(); 
    }

    // Set the players own ingame name
    public void logIn(String name)
    {
        id = name;
        System.out.println("Your current name given by the Server is: "+name);
    }
    
    public void setRole(boolean drawer)
    {
        role = drawer;
    }
    
    public void setWord(String word)
    {
        spielwort = word;
    }
    
    public void startGame()
    {
        gameRunning = true;
        gameLoop();
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
