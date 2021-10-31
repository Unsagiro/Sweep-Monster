package src;


public class SweepMonsterApplication{
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Sweep Monster Online!\n");
        System.out.println("Team Members:");
        System.out.println("Alejandro Zamora");
        System.out.println("Dong Li");
        System.out.println("Jingni Wang");
        System.out.println("Aldo Brigneti\n");


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
