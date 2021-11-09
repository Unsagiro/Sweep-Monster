import java.util.ArrayList;
import java.util.Collections;



public class FloorPlanArray {

    private static ArrayList<ArrayList<Tile>> FloorPlanArray = new ArrayList<ArrayList<Tile>>();
    private int startX;
    private int startY;

    public void initial(int maxX, int maxY) {
        for (int i = 0; i <= maxY; i++) {
            FloorPlanArray.add(new ArrayList<Tile>(Collections.<Tile>nCopies(maxX + 1, null)));
        }
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

    public boolean isNullTile(int x, int y) {
        return (FloorPlanArray.get(y).get(x)) == null;
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



    public int getTotalSize() {
        int count = 0;
        for (ArrayList<Tile> inner : FloorPlanArray) {
            for (Tile t : inner) {
                count++;
            }
        }
        return count;
    }

    //help get particular tile info
//    public Tile getTileInfo(int x, int y){return tilesArray.get(getScale()*x+y);}

}