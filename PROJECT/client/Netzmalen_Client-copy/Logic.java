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

    public void intitializeClient()
    {
        com.createSocket();
    }

    public void logIn(String name)
    {
        id = name;
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
