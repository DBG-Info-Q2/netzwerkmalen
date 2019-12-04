
/**
 * Beschreiben Sie hier die Klasse Eingabekontrolle.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Eingabekontrolle
{
    Gameserver server;
    public boolean checkWord(String word)
    { if(server.spielwort.equalsIgnoreCase(word)){
            return true; //umändern
        } return false;
    }
}
