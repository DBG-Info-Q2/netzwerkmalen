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
    String[] BOI = new String[117];
    
    
    public String gibneuesWort()
    {
        
        int i=1;
        int x=1;
        while(i!=0)
        {
            Random rand = new Random();
            x = rand.nextInt(116);
            if(BOI [x]!=""){i=0;}
        }
        String gewähltesWort = BOI [x];
        BOI [x] = "";
        return gewähltesWort;
    }
    
    public void leseDateiAus()
    {
        
        File Wörterdatei = new File("T:/Klasse q2/BrandIF/Netzmalen Max/PROJECT/server/Server/Woerter.txt");
        if (!Wörterdatei.canRead() || !Wörterdatei.isFile())
            {System.out.println("Dateifehler");
                System.exit(0);}
        BufferedReader in = null;
        try {
            //in = new BufferedReader(new FileReader("T:\\Klasse q2\\BrandIF\\Netzmalen Max\\PROJECT\\server\\Server"));
            in = new BufferedReader(new FileReader(Wörterdatei));
            String zeile = null;
            int i=116;
            while ((zeile = in.readLine()) != null) {
                BOI [i] = zeile;
                i--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        } 
        
    }
}


