package helper;
/**
 * Logger ist das Equivalente zu Debugs mt System.out.println(String s);
;*
 * @author Aleksander Stepien
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Logger
{
    public static boolean showDebug=true;
    
    /**
     * Schreibt text in Konsole mit Server Prefix
     */
    public static void log(String s){
        System.out.println("Server: "+s);
    }
    
    /**
     * Schreibt text in Konsole
     */
    public static void logPr(String s){
        System.out.println(s);
    }
    
    /**
     * Schreibt debug-Text in Konsole.
     * Wenn Debug Modus deaktiviert, werden diese Nachrichten nicht mehr angezeigt,
     */
    public static void debug(String s){
        if(showDebug)
            System.out.println(s);
    }
    
    /**
     * Schreibt fehler-Text in Konsole.
     * Wird in Roter schrift geschrieben.
     */
    public static void error(String s){
        System.err.println(s);
    }
}
