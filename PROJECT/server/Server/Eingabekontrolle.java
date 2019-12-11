
/**
 * Beschreiben Sie hier die Klasse Eingabekontrolle.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Eingabekontrolle
{
    public static boolean checkWord(String word)
    { 
        if(Gameserver.GOTT.spielwort.equalsIgnoreCase(word))
        {
            Logger.log(word+" is a right guess");
            return true;
        } 
        Logger.log(word+" is a false guess");
        return false;
    }
}
