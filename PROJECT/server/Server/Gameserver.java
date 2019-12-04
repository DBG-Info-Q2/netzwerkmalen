
/**
 * Beschreiben Sie hier die Klasse Gameserver.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Gameserver
{
    public String spielwort;
    public String drawerID;
    public int gameAmountCounter;
    private int gameUntilReset;
    public int currentRightGuesses;
    private int maxPlayer = 5;
    private int timerLength = 60;
    private int timerUpdateTime = 10;
    
    public Communication COMunit = new Communication();
    public Spielwörter wort = new Spielwörter();
    public Timer timer = new Timer();
    static Gameserver gott;
    
    public Gameserver()
    {
        gott = this;
    }
    
    /**
     * Methode for starting all server issues
     *
     */
    public void startNewServer()
    {
        COMunit.startListener();
        while (COMunit.playerList.size()<maxPlayer)
        {
            //LOG schreiben!!!!!
        }
    }
    
    /**
     * Methode for starting a game
     *
     */
    public void startNewGame()
    {
        spielwort = wort.gibNeueswort();
        timer.startCounter(timerLength, timerUpdateTime);
    }
    
    /**
     * Methode for select the drawer from the playerlist
     *
     */
    public void selectDrawerFromPlayerlist()
    {
        
    }
    
    /**
     * Methode resetGame
     *
     */
    public void resetGame()
    {
        
    }
    
    /**
     * Methode stopGame
     *
     */
    public void stopGame()
    {
        
    }
}
