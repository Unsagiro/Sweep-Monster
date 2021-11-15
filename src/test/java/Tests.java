import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import com.google.gson.Gson;

import org.junit.Test;

public class Tests {
    

FloorFileDigestion digest = new FloorFileDigestion();
RoboMemory memory = new RoboMemory();
SweepMonster robot = new SweepMonster();

    @Test
    public void  nulltest(){
        
    digest.run();
        
        if (digest != null){
            System.out.println("Standard Digestion is succesful!");
        }
    
    }

@Test
public void test(){

    memory.toString();
    assertNotNull(memory);
    
}
 
@Test
public void  nulltest2(){

assertNotNull(robot);
assertNotNull(robot.getCurrentBattery());                

}

@Test
public void  nulltest3(){

    assertNotNull(robot.getCurrentDirection());   

}
 
@Test
public void  nulltest4(){
 
    assertEquals(0,robot.getStartX());
    assertEquals(0,robot.getStartY());
}

@Test
public void  nulltest5(){

    assertNotNull(digest);
}
 
@Test
public void  nulltest6(){

   assertNotNull(robot.init(250,50, Direction.RIGHT, 0, 0,  memory));

}

@Test
public void  nulltest8() throws FileNotFoundException{
    Reader reader = null;
    reader = new BufferedReader(new FileReader("./src/main/java/Resources/FloorPlanFileTest.json"));
    Gson g = new Gson();
    
    TilesArray tilesArray = g.fromJson(reader, TilesArray.class);
    assertNotNull(tilesArray);


}

}
