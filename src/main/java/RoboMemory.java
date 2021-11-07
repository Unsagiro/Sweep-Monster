import java.util.HashMap;

public class RoboMemory {


    private HashMap<Pair,String> tileDirtness = new HashMap<Pair,String>();


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
            return totalVacuums;
        }
        if(curBattery <= 25){//feel free to change the lower bound of the battery
            return totalVacuums;
        }
    }
    return totalVacuums;
}


public String howManyMoreCleans(Pair position){ // returns how many more cleans are needed in this tile
    return tileDirtness.get(position);
}


}

