package src;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RoboMemory {


    private HashMap<Integer, String> tileDirtness = new HashMap<Integer, String>();

    private Set<Integer> cleanedPosition = new HashSet<>();

    private HashMap<Integer, String> obstructedTiles = new HashMap<Integer, String>();

////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////

public String whatObstruction(Integer position){
    return obstructedTiles.get(position);

}

public boolean isThereObstacle(Integer position){
    if(obstructedTiles.containsKey(position))
        {
        return true;
        }
        else {return false;}
}


public String howManyMoreCleans(Integer position){ // returns how many more cleans are needed in this tile
    return tileDirtness.get(position);
}


////////////////////////////////////////////////////////////////////////////////////////

    public void setCleanedPosition(Set<Integer> cleanedPosition) {
        this.cleanedPosition = cleanedPosition;
    }

    public void addCleanedPosition(Integer i){
        this.cleanedPosition.add(i);
    }

    public int cleanedPositionSize(){
        return cleanedPosition.size();
    }

    public boolean isItClean(Integer position){  // returns a boolean that determines if the tile is completely cleaned or not
        if (cleanedPosition.contains(position))
            {
                return true;
            }

        else 
            {
                 return false;
            }

    }

}

