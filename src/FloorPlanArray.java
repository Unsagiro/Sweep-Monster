package src;

import java.util.ArrayList;
import java.util.List;


public class FloorPlanArray {

    // Max map size is 1000*1000
    private static ArrayList<ArrayList<Tile>> FloorPlanArray = new ArrayList<>();
    private int startX;
    private int startY;

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

    public Tile getStartTile() {
        return FloorPlanArray.get(startY).get(startX);
    }

    public ArrayList<ArrayList<Tile>> getFloorPlanArray() {
        return FloorPlanArray;
    }

    public Tile setTile(int x, int y, Tile tile) {
        return FloorPlanArray.get(y).set(x, tile);
    }


    public Tile getTile(int x, int y) {
        return FloorPlanArray.get(y).get(x);
    }

    public int getWidth(int y) {
        if (y < 0)
            return 0;

        if (FloorPlanArray.size() > y) {
            return FloorPlanArray.get(y).size();
        } else {
            return 0;
        }
    }

    public int getHeight() {
        return FloorPlanArray.size();
    }

    public void setMinHeight(int minHeight) {
        if (getHeight() < minHeight) {
            FloorPlanArray.ensureCapacity(minHeight);
        }
    }

    public void setCapacity(int minWidth, int y) {
        setMinHeight(y);
        if (getWidth(y) < minWidth) {
            FloorPlanArray.get(y).ensureCapacity(minWidth);
        }
    }
//    public int getTotal() {return tilesArray.size();}

    //help get particular tile info
//    public Tile getTileInfo(int x, int y){return tilesArray.get(getScale()*x+y);}

}