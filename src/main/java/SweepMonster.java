import java.util.*;
import java.util.concurrent.TimeUnit;

public class SweepMonster {
    private static final int MAX_BATTERY = 250;
    private static final int MAX_DIRT_CAPACITY = 50;

    private float currentBattery;
    private static int currentDirtCapacity;
    private Direction currentDirection;
    private int startX;
    private int startY;
    private RoboMemory memory;
    private int currentX;
    private int currentY;
    private float currentUnitsCharge;
    private boolean isFirstTile = false;

    private FloorPlanArray floorPlanArray = null;
    private ArrayList<Pair> cleanedPosition = new ArrayList<Pair>();
    private HashMap<String, Integer> batteryConsume = new HashMap<String, Integer>();

    // return false indicates SweepMonster could not start
    public boolean init(int currentBattery, int currentDirtCapacity, Direction currentDirection, int startX, int startY, RoboMemory memory) {
        setCurrentBattery(currentBattery);
        setCurrentDirtCapacity(currentDirtCapacity);
        setCurrentDirection(currentDirection);
        setMemory(memory);
        setStartX(startX);
        setStartY(startY);

        batteryConsumeFilling();

        currentX = startX;
        currentY = startY;
        if (currentBattery <= 0){
            return false;
        }

        if (currentDirtCapacity <= 0) {
            return false;
        }

        return true;
    }


    //I tested the navigation/visiting process by going through the whole floor plan zig-zag for now
    //just to show the dynamically fetch the next tile's function is working (Feel free to replace the code inside)
    public void navigation(FloorPlanArray floorPlanArray) throws InterruptedException {
        this.floorPlanArray = floorPlanArray;
        Tile currentTile = getFirstTile();
        isFirstTile = true;
        currentUnitsCharge = batteryConsume.get(currentTile.getFloorType());
        Stack<Tile> stack = new Stack<Tile>();

        stack.add(currentTile);
        
        while (!stack.empty()) {
            currentTile = stack.peek();
            currentY = currentTile.getYVal();
            currentX = currentTile.getXVal();

            if(memory.batteryCheck(currentBattery, currentUnitsCharge) || currentDirtCapacity <= 0) {
                //Here we start the method to go back to closest charging station
                goBack();
                System.out.println("Ready to continue...");
                System.out.println("Continue the cleaning work...");
//                break;
            }

            if (isCleaned(currentX, currentY)) {
                stack.pop();
                continue;
            }

            if (isBlocked(currentTile)) {
                System.out.println("I'M BLOCKED!!!");
                return;
            }
         
            //moving to next tile, deduct corresponding units of battery
            if(isFirstTile) isFirstTile = false;
            else currentBattery = currentBattery - currentUnitsCharge;
            // we need to check how we will define this part after recharging
            memory.setPathMemory(currentTile);// We store our steps in memory so we can go back 
            stack.pop();

            System.out.println();
            System.out.println("Now at tile " + currentTile.getTile() + ", Current Battery: " + currentBattery);
            TimeUnit.MILLISECONDS.sleep(500);
            memory.dirtLogWrite(new Pair(currentX, currentY), currentTile.getDirt());//stores the dirt in a hashmap

            int vacuums = cleaningProtocol(new Pair(currentX, currentY), currentBattery, currentUnitsCharge, currentDirtCapacity,memory);//cleans using the dirt hashmap as reference

            currentBattery = currentBattery - vacuums * currentUnitsCharge;
            currentDirtCapacity = currentDirtCapacity - vacuums;

            //After finished cleaning the current tile (I skipped cleaning process here)
            if(vacuums == Integer.parseInt(currentTile.getDirt())) {
                cleanedPosition.add(new Pair(currentX, currentY));
                System.out.println(currentTile.getTile() + " has been cleaned!");
            }
            getNeighbourhood(stack, currentTile);
            //Fake the cleaning time
            TimeUnit.MILLISECONDS.sleep(500);

         



        }
        if(cleanedPosition.size()== 19) {
            System.out.println("The Floor plan is clean!!!!!");
        }
            
      
  
    }

    public static boolean cleanDirt() {
        while (true) {
            System.out.println("Press \"ENTER\" to EMPTY the dirt-container...");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                currentDirtCapacity = MAX_DIRT_CAPACITY;
                return true;
            }
        }
    }

    public float getCurrentBattery() {
        return currentBattery;
    }

    public void setCurrentBattery(int currentBattery) {
        this.currentBattery = currentBattery;
    }

    public int getCurrentDirtCapacity() {
        return currentDirtCapacity;
    }

    public void setCurrentDirtCapacity(int currentDirtCapacity) {
        this.currentDirtCapacity = currentDirtCapacity;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setMemory(RoboMemory memory)
    {
        this.memory = memory;
    }
    private Tile getFirstTile(){
        return this.floorPlanArray.getStartTile();
    }

    private void getNeighbourhood(Stack<Tile> stack, Tile curTile) {

        int height = this.floorPlanArray.getHeight();
        if(currentX - 1 >= 0 && isAccessible(currentX - 1, currentY)) {
            Tile tile = this.floorPlanArray.getTile(currentX - 1, currentY);
            tile.setDirection(Direction.LEFT);
            String currentSurface = curTile.getFloorType();
            String nextSurface = tile.getFloorType();
            currentUnitsCharge = calcUnitsCharge(currentSurface, nextSurface);
            stack.add(tile);
        }

        if(currentX + 1 < this.floorPlanArray.getWidth(currentY) && isAccessible(currentX + 1, currentY)) {
            Tile tile = this.floorPlanArray.getTile(currentX + 1, currentY);
            tile.setDirection(Direction.RIGHT);
            String currentSurface = curTile.getFloorType();
            String nextSurface = tile.getFloorType();
            currentUnitsCharge = calcUnitsCharge(currentSurface, nextSurface);
            stack.add(tile);
        }

        if(currentY - 1 >= 0 && isAccessible(currentX, currentY - 1)) {
            Tile tile = this.floorPlanArray.getTile(currentX, currentY - 1);
            tile.setDirection(Direction.UP);
            String currentSurface = curTile.getFloorType();
            String nextSurface = tile.getFloorType();
            currentUnitsCharge = calcUnitsCharge(currentSurface, nextSurface);
            stack.add(tile);
        }

        if(currentY + 1 < height && isAccessible(currentX, currentY + 1)) {
            Tile tile = this.floorPlanArray.getTile(currentX, currentY + 1);
            tile.setDirection(Direction.DOWN);
            String currentSurface = curTile.getFloorType();
            String nextSurface = tile.getFloorType();
            currentUnitsCharge = calcUnitsCharge(currentSurface, nextSurface);
            stack.add(tile);
        }
    }

    private boolean isAccessible (int x, int y) {
        if (this.floorPlanArray.isNullTile(x, y)) {
            return false;
        }
        Tile tile = this.floorPlanArray.getTile(x, y);
        if (!tile.getObstacleType().equals("open")) {
            return false;
        }
        if (isCleaned(x, y)) {
            return false;
        }

        return true;
    }

    private boolean isCleaned (int x, int y) {
        for (Pair p: cleanedPosition) {
            if (p.getKey().equals(x) && p.getValue().equals(y)) {
                return true;
            }
        }

        return false;
    }

    private boolean isBlocked (Tile curTile) {
        // Check if current tile is obstacle
        if (curTile.getObstacleType().equals("Obstacle")) {
            return true;
        }

        int height = this.floorPlanArray.getHeight();

        // Check if current Tile is blocked by obstacles
        int x = curTile.getXVal();
        int y = curTile.getYVal();
        if(x - 1 >= 0) {
            Tile tile = this.floorPlanArray.getTile(x - 1, y);
            if (!tile.getObstacleType().equals("obstacle")) {
                return false;
            }
        }

        if(x + 1 < this.floorPlanArray.getWidth(y)) {
            Tile tile = this.floorPlanArray.getTile(x + 1, y);
            if (!tile.getObstacleType().equals("obstacle")) {
                return false;
            }
        }

        if(y - 1 >= 0) {
            Tile tile = this.floorPlanArray.getTile(x, y - 1);
            if (!tile.getObstacleType().equals("obstacle")) {
                return false;
            }
        }

        if(y + 1 < height) {
            Tile tile = this.floorPlanArray.getTile(x, y + 1);
            if (!tile.getObstacleType().equals("obstacle")) {
                return false;
            }
        }

        return true;
    }

    public int cleaningProtocol(Pair position, float currentBattery, float currentUnitsCharge, int currentDirtCapacity, RoboMemory memory) throws InterruptedException{

        String dirtness = memory.tileDirtness.get(position);
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
            TimeUnit.MILLISECONDS.sleep(500);
            //SPECIAL INDICATOR
           
            if(curDirt <= 0){
                System.out.println("Warning: The dirt-container is full!!");
                // Press Enter to empty the dirt-container\
                cleanDirt();
                System.out.println("Dirt Capacity is good...resuming cleaning!");
                continue;
                
    //            return totalVacuums
                }
            
            if(curBattery <= 25){//feel free to change the lower bound of the battery
                return totalVacuums;
            }
        }
        return totalVacuums;
    }


    private void batteryConsumeFilling(){
        batteryConsume.put("Bare", 1);
        batteryConsume.put("lowPile", 2);
        batteryConsume.put("highPile", 3);
    }

    private float calcUnitsCharge(String currentSurface, String nextSurface){
        return ((float)batteryConsume.get(currentSurface) + (float)batteryConsume.get(nextSurface))/2;
    }

    public void goBack() throws InterruptedException
        {
        System.out.println("Homecoming protocols initialiazed...");
        TimeUnit.SECONDS.sleep(1);
         while(!(memory.pathMemoryEmpty()))
            {
                Tile memoryTile = memory.popPathMemory();
                
                
                TimeUnit.MILLISECONDS.sleep(500);

                if (memory.pathMemoryEmpty())
                    {
                        System.out.println("Charging Station reached, power source adquired");
                        System.out.println("Recharging...25%\n");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Recharging...50%\n");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Recharging...75%\n");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Recharging...100%\n");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Charged up and ready to clean!\n");
                        TimeUnit.SECONDS.sleep(1);
                        setCurrentBattery(250);
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("Going back to work...");
                        setStartX(memoryTile.getXVal());// I set the new charging station start point
                        setStartY(memoryTile.getYVal());
                        break;
                    }
                System.out.println("Tracing back to Charging Station... Current Tile is " + memoryTile.getTile());

            }

         }
}



