import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SweepMonster {
    private static final int MAX_BATTERY = 250;
    private static final int MAX_DIRT_CAPACITY = 50;

    private int currentBattery;
    private int currentDirtCapacity;
    private Direction currentDirection;
    private int startX;
    private int startY;
    private RoboMemory memory;
    private int currentX;
    private int currentY;

    private boolean changedToSouth = false;
    private FloorPlanArray floorPlanArray;
    private ArrayList<Pair<Integer, Integer>> cleanedPosition = new ArrayList<Pair<Integer, Integer>>();
    private HashMap<Direction, String> neighborMap = new HashMap<Direction, String>();

    // return false indicates SweepMonster could not start
    public boolean init(int currentBattery, int currentDirtCapacity, Direction currentDirection, int startX, int startY, RoboMemory memory) {
        setCurrentBattery(currentBattery);
        setCurrentDirtCapacity(currentDirtCapacity);
        setCurrentDirection(currentDirection);
        setMemory(memory);
        setStartX(startX);
        setStartY(startY);

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
        Stack<Tile> stack = new Stack<Tile>();

        stack.add(currentTile);
        while (!stack.empty()) {
            currentTile = stack.pop();
            currentY = currentTile.getYVal();
            currentX = currentTile.getXVal();

            getNeighbourhood(stack);
            
            memory.dirtLogWrite(new Pair<Integer, Integer>(currentX, currentY), currentTile.getDirt());//stores the dirt in a hashmap

            memory.cleaningProtocol(new Pair<Integer, Integer>(currentX, currentY));//cleans using the dirt hashmap as reference


            //After finished cleaning the current tile (I skipped cleaning process here)
            cleanedPosition.add(new Pair<Integer, Integer>(currentX, currentY));
            System.out.println(currentTile.getTile() + " has been cleaned!");
            //Fake the cleaning time
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println("The cleaning is done!");
    }

    //Sensor Simulation Here

    //    function for calling the simulator to fetch the next step's tile object dynamically
//    If next step is out of the floor plan, return null for next step's tile
    public Tile getNextTileInfo(TilesArray TilesArr){
        int bound = TilesArr.getScale()-1;
        Tile tile = null;
        switch (currentDirection) {
            case UP:
                if(currentX-1 >= 0) tile=TilesArr.getTileInfo(currentX-1, currentY);
                break;
            case RIGHT:
                if(currentY+1 <= bound) tile = TilesArr.getTileInfo(currentX, currentY+1);
                break;
            case DOWN:
                if(currentX+1 <= bound) tile = TilesArr.getTileInfo(currentX+1, currentY);
                break;
            case LEFT:
                if(currentY-1 >= 0) tile = TilesArr.getTileInfo(currentX, currentY-1);
                break;
            default:
                break;
        }
        return tile;
    }

    //blocked tile identifier
    public boolean isBlocked(Tile t){
        if(t.getObstacleType().equals("open")) return false;
        else return true;
    }

    public int getCurrentBattery() {
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

    private void getNeighbourhood(Stack<Tile> stack) {

        int height = this.floorPlanArray.getHeight();
        if(currentX - 1 >= 0 && isAccessible(currentX - 1, currentY)) {
            Tile tile = this.floorPlanArray.getTile(currentX - 1, currentY);
            tile.setDirection(Direction.LEFT);
            stack.add(tile);
        }

        if(currentX + 1 < this.floorPlanArray.getWidth(currentY) && isAccessible(currentX + 1, currentY)) {
            Tile tile = this.floorPlanArray.getTile(currentX + 1, currentY);
            tile.setDirection(Direction.RIGHT);
            stack.add(tile);
        }

        if(currentY - 1 >= 0 && isAccessible(currentX, currentY - 1)) {
            Tile tile = this.floorPlanArray.getTile(currentX, currentY - 1);
            tile.setDirection(Direction.UP);
            stack.add(tile);
        }

        if(currentY + 1 < height && isAccessible(currentX, currentY + 1)) {
            Tile tile = this.floorPlanArray.getTile(currentX, currentY + 1);
            tile.setDirection(Direction.DOWN);
            stack.add(tile);
        }
    }

    private boolean isAccessible (int x, int y) {
        if (this.floorPlanArray.isNullTile(x, y)) {
            return false;
        }
        Tile tile = this.floorPlanArray.getTile(x, y);
        if (tile.getObstacleType().equals("obstacle")) {
            return false;
        }
        if (isCleaned(x, y)) {
            return false;
        }
        // TODO check power
        // TODO check dirt capacity
        return true;
    }

    private boolean isCleaned (int x, int y) {
        for (Pair<Integer, Integer> p: cleanedPosition) {
            if (p.getKey().equals(x) && p.getValue().equals(y)) {
                return true;
            }
        }

        return false;
    }
}



