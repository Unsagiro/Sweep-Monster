import org.junit.Test;

public class SweepMonsterApplicationTest {
    

    @Test
    public void testMain() throws InterruptedException{
                FloorFileDigestion digest = new FloorFileDigestion();
                digest.run();
                RoboMemory memory = new RoboMemory();
                SweepMonster robot = new SweepMonster();
                if (!robot.init(250,50, Direction.RIGHT, 0, 0,  memory)){
                    System.out.println("Something is wrong, cannot start the cleaning cycle!");
                }else{
                    System.out.println("\nLet's clean this up!");
                    robot.navigation(digest.getFloorPlanArray());
                }
        
            
        }
        
    
    
    
    }






