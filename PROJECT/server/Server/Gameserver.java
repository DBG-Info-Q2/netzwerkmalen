
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
    
    public Communication COMunit = new Communication();
    /**
     * Methode for starting all server issues
     *
     */
    public void startNewServer()
    {
        COMunit.startListener();
    }
    
    /**
     * Methode for starting a game
     *
     */
    public void startNewGame()
    {
        
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
