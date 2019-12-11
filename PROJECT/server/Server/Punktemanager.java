
/**
 * Beschreiben Sie hier die Klasse Punktemanager.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import java.util.*;

public class Punktemanager
{
    HashMap<String,Integer> pointList=new HashMap<String,Integer>();

    public void calculateANDaddPoints(String id, boolean isDrawer)
    {

    }

    public HashMap<String,Integer> getMap(){
        return pointList;
    }

    private void setPoints(String id,int points){
        if (pointList.containsKey(id)){
            pointList.replace(id, points);
        }
        else{
            pointList.put(id,points);
        }
    }

    public int getPoints(String id)
    { if (pointList.containsKey(id)){
            int points = pointList.get(id);
            return points;
        }
        else{
            return -1;
        }
    }

    public void removePoints(String id)
    { if (pointList.containsKey(id)){
            pointList.remove(id);
        }
    }
}
