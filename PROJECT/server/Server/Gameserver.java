
/**
 * Beschreiben Sie hier die Klasse Gameserver.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.util.*;

public class Gameserver
{
    public String spielwort;
    public String drawerID;
    public int gameAmountCounter;
    private int gameUntilReset = 5;
    public int currentRightGuesses;
    private int maxPlayer = 5;
    private int timerLength = 60;
    private int timerUpdateTime = 10;
    
    public Communication COMunit = new Communication();
    public Spielwörter wort = new Spielwörter();
    public Timer timer = new Timer();
    static Gameserver GOTT;
    
    public Gameserver()
    {
        GOTT = this;
    }
    
    /**
     * Methode for starting all server issues
     *
     */
    public void startNewServer()
    {
        COMunit.startListener();
        long time = System.currentTimeMillis()+60000;
        while (COMunit.playerList.size()<maxPlayer && System.currentTimeMillis()<time)
        {
            //LOG schreiben!!!!!
        }
        startNewGame();
    }
    
    /**
     * Methode for starting a game
     *
     */
    public void startNewGame()
    {
        spielwort = wort.gibNeueswort();
        timer.startCounter(timerLength, timerUpdateTime);
        selectDrawerFromPlayerlist();
        //COMunit.sendPaket
        // muss noch überlegt werden, wie gameserver wartet während gezeichnet wird
    }
    
    /**
     * Methode for select the drawer from the playerlist
     *
     */
    public void selectDrawerFromPlayerlist()
    {
        ArrayList<String> randomList = new ArrayList<String>(COMunit.playerList.keySet());
        drawerID = randomList.get((int)(Math.random() * (COMunit.playerList.size() + 1)));
    }
    
    /**
     * Methode resetGame
     *
     */
    public void resetGame()
    {
        gameAmountCounter++;
        spielwort = null;
        timer.stopCounter();
        drawerID = null;
        //COMunit.sendPaket
        startNewGame();
    }
    
    /**
     * Methode stopGame
     *
     */
    public void stopGame()
    {
        //COMunit.sendPaket in 5 sek
        long time = System.currentTimeMillis()+5000;
        while (System.currentTimeMillis()<time)
        {
            
        }
        System.exit(1);
    }
}
