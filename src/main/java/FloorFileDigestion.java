import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
            reader = new BufferedReader(new FileReader("./src/main/java/Resources/FloorPlanFileTest.json"));
            tilesArray = g.fromJson(reader, TilesArray.class);

            //System.out.println(tilesArray);
            int maxX = 0;
            int maxY = 0;
            if(tilesArray != null){
                for(Tile t: tilesArray.getTilesArray()) {
                    maxX = Math.max(maxX, t.getXVal());
                    maxY = Math.max(maxY, t.getYVal());
                }

                floorPlanArray.initial(maxX, maxY);

                System.out.println("Reading Floor Plan:");
                for(Tile t: tilesArray.getTilesArray()){
                    int x = t.getXVal();
                    int y = t.getYVal();
                    floorPlanArray.setTile(x, y, t);
                    if (t.getChargingStation().equals("true")) {
                        floorPlanArray.setStartX(x);
                        floorPlanArray.setStartY(y);
                    }
                    System.out.println("Tile " + t.getTile() + ", X: " + t.getX() + ", Y: " + t.getY() + ",Obstacle Type: " + t.getObstacleType() + ",Dirt: " + t.getDirt());
                }
                System.out.println("Finished parsing the Json file!");
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