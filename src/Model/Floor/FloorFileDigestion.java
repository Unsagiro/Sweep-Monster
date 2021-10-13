package src.Model.Floor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;


public class FloorFileDigestion implements Runnable {

    



public void run(){
    
    
    try 
    
        {
             //new Gson Instance
            Gson g = new Gson();
    
            //we create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/Resources/FloorPlanFileTest.json"));

            //TileMap tileMap = g.fromJson(reader, TileMap.class);

            //System.out.println(tileMap);










        } 
    catch (IOException e) 
        {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }



}









}