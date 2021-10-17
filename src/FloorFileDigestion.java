package src;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class FloorFileDigestion implements Runnable {

    private TilesArray tilesArray;
    public void run()
    {
        Gson g = new Gson();
        Reader reader = null;
        try

            {
                 //new Gson Instance

                //we create a reader
                reader = new BufferedReader(new FileReader("./Sweep-Monster/src/Resources/FloorPlanFileTest.json"));
                 tilesArray = g.fromJson(reader, TilesArray.class);

                 //System.out.println(tilesArray);

                if(tilesArray != null){
                    for(Tile t: tilesArray.getTilesArray()){

//                            System.out.println(t.getTile());


                        }

                    }

                else{
                    System.out.println("the list is empty");
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
}