package com.dbgq2.netzwerkmalen.server.gameMechanics;
/**
 * Beschreiben Sie hier die Klasse Eingabekontrolle.
 * 
 */

import com.dbgq2.netzwerkmalen.server.helper.Logger;
public class Eingabekontrolle
{
    public static boolean checkWord(String word)
    { 
        if(Gameserver.GOTT==null||Gameserver.GOTT.spielwort==null){
            Logger.error("GOTT or spielwort is null! Can not check word");
            return false;
        }
        if(Gameserver.GOTT.spielwort.equalsIgnoreCase(word))
        {
            Logger.log(word+" is a right guess");
            return true;
        } 
        Logger.log(word+" is a false guess");
        return false;
    }
}
