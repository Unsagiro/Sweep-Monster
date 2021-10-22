package src;

import src.Direction;

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

    private int currentX;
    private int currentY;

    private boolean changedToSouth = false;

    private Set<Integer> cleanedPosition = new HashSet<>();
    private HashMap<Direction, String> neighborMap = new HashMap<Direction, String>();

    // return false indicates SweepMonster could not start
    public boolean init(int currentBattery, int currentDirtCapacity, Direction currentDirection, int startX, int startY) {
        setCurrentBattery(currentBattery);
        setCurrentDirtCapacity(currentDirtCapacity);
        setCurrentDirection(currentDirection);

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

    public void run (TilesArray tilesArray) {
        for(Tile t: tilesArray.getTilesArray()){
            System.out.println(t.getTile() + " has been clean!");
        }

        System.out.println("Finish the floor plan");

        // TODO return to the start
    }

    //I tested the navigation/visiting process by going through the whole floor plan zig-zag for now
    //just to show the dynamically fetch the next tile's function is working (Feel free to replace the code inside)
    public void navigation(TilesArray tilesArr) throws InterruptedException {
        Tile currentTile = getFirstTile(tilesArr);
        while(!(cleanedPosition.size() == tilesArr.getTotal())){
            //After finished cleaning the current tile (I skipped cleaning process here)
            cleanedPosition.add(currentTile.getXVal()*tilesArr.getScale()+currentTile.getYVal());
            System.out.println(currentTile.getTile() + " has been cleaned!");
            //Fake the cleaning time
            TimeUnit.SECONDS.sleep(1);
            //Trying to find the next tile and going forward
            if(changedToSouth){
                currentDirection = Direction.WEST;
                changedToSouth = false;
            }
            Tile nextTile = getNextTileInfo(tilesArr);

            if(nextTile == null){
                //change direction
                if(currentTile.getDownTile().equals("null")){
                    if(currentTile.getLeftTile().equals("null")){
                        currentDirection = Direction.EAST;
                    }else {
                        currentDirection = Direction.WEST;
                    }
                }else{
                    currentDirection = Direction.SOUTH;
                    changedToSouth = true;
                }

                nextTile = getNextTileInfo(tilesArr);
            }
            currentTile = nextTile;
            currentX = currentTile.getXVal();
            currentY = currentTile.getYVal();
        }
        System.out.println("The clean is done!");
    }

    //Sensor Simulation Here

    //    function for calling the simulator to fetch the next step's tile object dynamically
//    If next step is out of the floor plan, return null for next step's tile
    public Tile getNextTileInfo(TilesArray TilesArr){
        int bound = TilesArr.getScale()-1;
        Tile tile = null;
        switch (currentDirection) {
            case NORTH:
                if(currentX-1 >= 0) tile=TilesArr.getTileInfo(currentX-1, currentY);
                break;
            case EAST:
                if(currentY+1 <= bound) tile = TilesArr.getTileInfo(currentX, currentY+1);
                break;
            case SOUTH:
                if(currentX+1 <= bound) tile = TilesArr.getTileInfo(currentX+1, currentY);
                break;
            case WEST:
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




    public Tile getFirstTile(TilesArray tilesArr){
        return tilesArr.getTile(0);
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

}



