/**
 * Util to create Pakets 
 */
public class PaketUtil{
    /**
     * Sample format: "0;Hermann;false;";
     * @param du true == Client sets its name to the given name.
     */
    public static String createLoginUpdatePaket(String name, boolean du){
        return "0;"+name+";"+du+";";
    }

    /**
     * Sample format: "1;19;";
     * @param leftoverTime Time in ms, how long the round will durate
     */
    public static String createTimeUpdatePaket(int leftoverTime){
        return "1;"+leftoverTime+";";
    }

    /**
     * Sample format: "2;foobar message lol;";
     * @param receivedText The message, someone wants to convey
     */
    public static String createChatUpdatePaket(String recievedText){
        return "2;"+recievedText+";";
    }

    /**
     * Sample format: "3;45;Hermann;";
     * @param newPionts The updated score of the player with the ID id
     * @param id ID of the updated player in the clients scoreboard.
     */
    public static String createPointsUpdatePaket(int newPionts, String id){
        return "3;"+newPionts+";"+id+";";
    }

    /**
     * Paket to transmit Drawn image
     */
    public static String createDrawUpdatePaket(int x1, int y1, int x2, int y2, int colour){
        return "4;"+x1+";"+y1+";"+x2+";"+y2+";"+colour+";";
    }

    /**
     * Sample format: "5;true;";
     * @param drawer Defines whether the reveiving client is a drawer or not.
     */
    public static String createRoleUpdatePaket(boolean drawer){
        return "5;"+drawer+";";
    }

    /**
     * Sample format: "6;Vogelkacka;";
     * @param word The new word, that only the drawer has to see and draw with
     */
    public static String createWordUpdatePaket(String word){
        return "6;"+word+";";
    }

    /**
     * Sample format: "7;true;";
     * @param gameRunning true == Gamephase has begun; false == Lobbyphase has begun. Game is finished, awaiting new game.
     */
    public static String createGameStateUpdatePaket(boolean gameRunning){
        return "7;"+gameRunning+";";
    }

    /**
     * Sample format: "8;Hermann;";
     * @param winner ID of the player that has won the games according to server leaderboard.
     */
    public static String createGameEndUpdatePaket(String winner){
        return "8;"+winner+";";
    }
}