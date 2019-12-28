package com.dbgq2.netzwerkmalen.server.communication;

/**
 * Util to create Names for Players
 */
public class PlayerNamesUtil{
    public static final String[] sampleNames;

    /**
     * Procedure finds an not used name according to Array of names that are already used. 
     * Also adds 1!!!... at the end if all names are being used.
     * suggestedNameAddon can be a suffix to the user name
     */
    public static String findNewName(String[] existingNames,String suggestedNameAddon){
        if(existingNames==null)
            return null;

        String name = selectRandomName();
        if(suggestedNameAddon!=null)
            name=name+suggestedNameAddon;
        
        boolean foundExistingName=false;
        for(String exName: existingNames)
            if(exName.equalsIgnoreCase(name))
                foundExistingName=true;

        if(foundExistingName)
            if(suggestedNameAddon==null)
                return findNewName(existingNames,"1");
            else
                return findNewName(existingNames,suggestedNameAddon+"!");
        return name;  
    }
    
    private static String selectRandomName() {
    	return sampleNames[((int)(Math.random() * (sampleNames.length - 1)))]; 
    }

    static{
        /**
         * This is where all the names are stored.
         */
        sampleNames=new String[]{"Hermann","Jonas","Peter","Kacka","Bratan","Arian","Ketchup","Majo","Senf","Butterbrot","Netzwerkadmin","H41","C4"};
    }
}