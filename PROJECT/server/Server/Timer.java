
/**
 * Beschreiben Sie hier die Klasse Timer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Timer
{
    private static int updatedelay;
    private long countdownStart;
    private int countdownDuration;
    public Thread timer;
    private static boolean timerRunning = false;
    int currentCountdown;

    public boolean timerRuns()
    {
        return timerRunning;
    }
    
    public void startCounter(int counter, int updatedelay) //updatedelay in ms | Kuss von Dima :D
    {
        if (!timerRunning)
        {
            Logger.log("starting timer...");
            timerRunning = true;
            countdownStart = System.currentTimeMillis();
            countdownDuration = counter*1000;
            this.updatedelay = updatedelay;
            timer = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while (System.currentTimeMillis()<countdownStart+countdownDuration)
                        {
                            long updatewaiting = System.currentTimeMillis()+Timer.updatedelay;
                            while (updatewaiting>System.currentTimeMillis())
                            {

                            }
                            currentCountdown = (int)(countdownStart+countdownDuration-System.currentTimeMillis());
                            sendUpdateToUsers();
                        }
                        currentCountdown=0;
                        sendUpdateToUsers();
                        stopCounter();
                    }
                });
            timer.start();
        }
    }

    public void sendUpdateToUsers()
    {
        Logger.log("timer by: "+currentCountdown);
        Gameserver.GOTT.COMunit.sendPaket("-1", Communication.PaketUtil.createTimeUpdatePaket(currentCountdown));
    }

    public void stopCounter()
    {
        Logger.log("stopping timer...");
        timerRunning = false;
        timer.stop();
    }

    public int getTime()
    {
        return currentCountdown;
    }
}
