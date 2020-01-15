
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
    private int timerLength = 10;
    private int timerUpdateTime = 500; //in ms
    private static boolean gameRunning = false;

    public Communication COMunit = new Communication();
    public Punktemanager points = new Punktemanager();
    public Spielwörter wort = new Spielwörter();
    public Timer timer = new Timer();
    public Konsole console = new Konsole();
    static Gameserver GOTT;
    public Thread game;

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
        //console.start();
        COMunit.startListener();

        /*long time = System.currentTimeMillis()+60000;
        while (COMunit.playerList.size()<maxPlayer && System.currentTimeMillis()<time)
        {

        }

        if (COMunit.playerList.size()==maxPlayer)
        {
        Logger.log("maximal player nummber is reached");
        }
        else
        {
        Logger.log("60 sec are over");
        }

        startNewGame();*/
    }

    /**
     * Methode for starting a game
     *
     */
    public void startNewGame()
    {
        Logger.log("starting game...");
        
        spielwort = wort.gibNeueswort();
        COMunit.sendPaket(drawerID, Communication.PaketUtil.createWordUpdatePaket(spielwort));
        Logger.log("gameword is set to: "+spielwort);
        
        timer.startCounter(timerLength, timerUpdateTime);

        Logger.log("Gamestateupdate Paket sending to all users");
        COMunit.sendPaket("-1", Communication.PaketUtil.createGameStateUpdatePaket(true));
        
        selectDrawerFromPlayerlist();
        Logger.log("drawerID is: "+drawerID);
        COMunit.sendPaket("-1", Communication.PaketUtil.createRoleUpdatePaket(false));
        COMunit.sendPaket(drawerID, Communication.PaketUtil.createRoleUpdatePaket(true));

        runningGame();
    }

    /**
     * Methode for select the drawer from the playerlist
     *
     */
    public void selectDrawerFromPlayerlist()
    {
        ArrayList<String> randomList = new ArrayList<String>(COMunit.playerList.keySet());
        drawerID = randomList.get((int)(Math.random() * (COMunit.playerList.size() - 1)));
    }

    public void runningGame()
    {
        if (!gameRunning)
        {
            gameRunning = true;
            game = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        while (currentRightGuesses<maxPlayer-1 && timer.timerRuns())
                        {
                            Logger.error("Thread läuft...");
                        }
                        //Logger.error("stop game");
                        resetGame();
                    }
                });
            game.start();
        }
    }

    /**
     * Methode resetGame
     *
     */
    public void resetGame()
    {
        game.interrupt();
        //game.stop();
        gameRunning=false;
        
        gameAmountCounter++;
        spielwort = null;
        timer.stopCounter();
        drawerID = null;
        COMunit.sendPaket("-1", Communication.PaketUtil.createGameStateUpdatePaket(false));
        Logger.log("game resetted...");
        if (gameAmountCounter<=gameUntilReset)
        {
            startNewGame();
        }
        else
        {
            COMunit.sendPaket("-1", Communication.PaketUtil.createGameEndUpdatePaket(points.getWinner()));
            stopGame();
        }
       
    }

    /**
     * Methode stopGame
     *
     */
    public void stopGame()
    {
        Logger.log("server is closing in 10sec...");
        long time = System.currentTimeMillis()+10000;
        while (System.currentTimeMillis()<time)
        {
        }
        COMunit.forceShutdown();
        System.exit(1);
    }
}
