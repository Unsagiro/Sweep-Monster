import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class RoboMemory {


private Pair closestCS;

private boolean goBackFlag;
public  HashMap<Pair,String> tileDirtness = new HashMap<Pair,String>();
private Stack<Tile> pathMemory = new Stack<Tile>(); // here we store the steps to go back to our CS

public void dirtLogWrite(Pair position, String dirt){
    tileDirtness.put(position, dirt);
    
}



// returns how many more cleans are needed in this tile
public String howManyMoreCleans(Pair position){ 
    return tileDirtness.get(position);
}
// This function checks if we can go back with the battery we got
public boolean batteryCheck(float currentBattery, float currentUnitsCharge){  
  if((pathMemory.size() + 10)  < (currentBattery - currentUnitsCharge))
    {
        return false;
    }
    else{
        return true;
    }

}

    public Pair getClosestCS() {
        return closestCS;
    }

    public void setClosestCS(Pair closestCS) {
        this.closestCS = closestCS;
    }

    public HashMap<Pair,String> getTileDirtness() {
        return tileDirtness;
    }

    public void setTileDirtness(HashMap<Pair,String> tileDirtness) {
        this.tileDirtness = tileDirtness;
    }

    public Tile popPathMemory() {
       return pathMemory.pop();
    }

    public void setPathMemory(Tile tile) {
        pathMemory.push(tile);
    }
    public boolean pathMemoryEmpty(){
        return pathMemory.empty();
    }


    /**
     * @return boolean return the goBackFlag
     */
    public boolean isGoBackFlag() {
        return goBackFlag;
    }

    /**
     * @param goBackFlag the goBackFlag to set
     */
    public void setGoBackFlag(boolean goBackFlag) {
        this.goBackFlag = goBackFlag;
    }

}

