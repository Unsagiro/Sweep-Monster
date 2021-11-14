import org.junit.Test;

public class FloorFileDigestionTest {

@Test
public void  nulltest(){
    
    
    FloorFileDigestion digest = new FloorFileDigestion();
    
    digest.run();

    if (digest != null){
        System.out.println(" Standard Digestion is succesful!");
    }

}

}
