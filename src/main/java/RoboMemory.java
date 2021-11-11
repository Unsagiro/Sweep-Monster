import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class RoboMemory {


private Pair closestCS;


private HashMap<Pair,String> tileDirtness = new HashMap<Pair,String>();
private Stack<Tile> pathMemory = new Stack<Tile>(); // here we store the steps to go back to our CS

public void dirtLogWrite(Pair position, String dirt){
    tileDirtness.put(position, dirt);
    
}

public int cleaningProtocol(Pair position, float currentBattery, float currentUnitsCharge, int currentDirtCapacity){

    String dirtness = tileDirtness.get(position);
    int newDirtness = Integer.parseInt(dirtness);
    int totalVacuums = 0;
    float curBattery = currentBattery;
    int curDirt = currentDirtCapacity;
    System.out.println("Cleaning...");
    if(newDirtness == 0){
        System.out.println("No more dirt here!");
        System.out.println("Current Battery:" + curBattery);
        return totalVacuums;
    }
    while(newDirtness != 0 && curBattery > 25 && curDirt > 0){
        newDirtness = newDirtness - 1;
        totalVacuums = totalVacuums + 1;
        curBattery = curBattery - currentUnitsCharge;
        curDirt = curDirt-1;
        System.out.println("Start Vacuum " + totalVacuums + "... Dirtness left = " + newDirtness);
        System.out.println("Current Battery:" + curBattery + " | Current Dirt Capacity:" + curDirt);
        //SPECIAL INDICATOR
        if(curDirt <= 0){
            System.out.println("Warning: The dirt-container is full!EMPTY ME!");
            // Press Enter to empty the dirt-container
            if (SweepMonster.cleanDirt()) {
                continue;
//            return totalVacuums
            }
        }
        if(curBattery <= 25){//feel free to change the lower bound of the battery
            return totalVacuums;
        }
    }
    return totalVacuums;
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

}

