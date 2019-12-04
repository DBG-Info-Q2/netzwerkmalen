
/**
 * Beschreiben Sie hier die Klasse Timer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Timer
{
    private int updatedelay;
    private long countdownStart;
    private int countdownDuration;
    public Thread timer;
    
    public void startCounter(int counter, int updatedelay) //updatedelay in ms | Kuss von Dima :D
    {
        countdownStart = System.currentTimeMillis();
        countdownDuration = counter;
        updatedelay = this.updatedelay;
        timer = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                
            }
        });
    }
    
    public void sendUpdateToUsers()
    {
        //Gameserver.GOTT.COMunit.
    }
    
    public void stopCounter()
    {
        
    }
    
    public void getTime()
    {
        
    }
}
