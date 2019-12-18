import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * Beschreiben Sie hier die Klasse Spielwörter.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Spielwörter
{
    String dateiname;
    String[] wörter;
    
    public String gibNeueswort()
    {
        if (leseDateiAus())
        {
            return wörter[(int)(Math.random() * (wörter.length - 1))];
        }
        else
        {
            return null;
        }
    }

    public boolean leseDateiAus()
    {
        File Wörterdatei = new File("T:/Klasse q2/BrandIF/Netzmalen Max/PROJECT/server/Server/Woerter.txt"); //TODO: Datei in Cloud
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(Wörterdatei));
            String inhalt = "";
            while (reader.readLine() != null)
            {
                inhalt=inhalt+";"+reader.readLine();
            }
            wörter=inhalt.split(";");
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Logger.error("File doesn't exists");
            return false;
        }
    }
}

