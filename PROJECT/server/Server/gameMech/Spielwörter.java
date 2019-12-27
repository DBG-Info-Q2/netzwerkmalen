package gameMech;

import java.util.Random;
import java.io.*;
import java.net.*;

import helper.Logger;

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

    /**
     * WIP
     * Requests an internet connection to the repositories raw Woerter.txt file. By this all words are always centrally synchronized.
     * @author Aleksander Stepien
     */
    // TODO: Locally store the file when once loaded from github and use as a fallback in case the internet is down or some shit.
    public boolean readFileFromGitHubRepo(){
        try{
            String wordsStream="";
            // Create URL or WebLink where the Woerter.txt file is located.
            URL url = new URL("https://raw.githubusercontent.com/DBG-Info-Q2/netzwerkmalen/master/PROJECT/server/Server/Woerter.txt");
            // Open the connection to said link. May throw several errors.
            URLConnection connect = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            String line = null;
            // Read all the buffered wordlines and put them in a String.
            while ((line = reader.readLine()) != null) 
                wordsStream=wordsStream+wordsStream+"\n";
                
            // Split up said String to create an Array of words needed for this.
            wörter=wordsStream.split("\n");
            return true;

        }catch(Exception e){
            // There has been some error.
            Logger.error("There was an error reading out the new file from github.com");
            Logger.error("Please check your internet connection and follow the debug");
            e.printStackTrace();
        }
        return false;
    }
}

