package src;

import java.io.*;

import com.google.gson.Gson;


public class FloorFileDigestion implements Runnable {

    private TilesArray tilesArray;
    private FloorPlanArray floorPlanArray = new FloorPlanArray();
    public void run()
    {
        Gson g = new Gson();
        Reader reader = null;
        try

        {
            //new Gson Instance

            //we create a reader
            reader = new BufferedReader(new FileReader("./src/Resources/FloorPlanFileTest.json"));
            tilesArray = g.fromJson(reader, TilesArray.class);

            //System.out.println(tilesArray);

            if(tilesArray != null){
                System.out.println("Reading Floor Plan:");
                for(Tile t: tilesArray.getTilesArray()){
                    int x = t.getXVal();
                    int y = t.getYVal();
                    floorPlanArray.setCapacity(x+1, y+1);
                    floorPlanArray.setTile(x, y, t);
                    if (t.getChargingStation().equals("true")) {
                        floorPlanArray.setStartX(x);
                        floorPlanArray.setStartY(y);
                    }
                    System.out.println("Tile " + t.getTile() + ", X: " + t.getX() + ", Y: " + t.getY() + ",Obstacle Type: " + t.getObstacleType() + ",Dirt: " + t.getDirt());
                }
            }

            else{
                System.out.println("The floor plan is not legit!");
            }

        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            if(reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
    }

    public TilesArray getTilesArray() {
        return tilesArray;
    }

    public FloorPlanArray getFloorPlanArray() {
        return floorPlanArray;
    }


}