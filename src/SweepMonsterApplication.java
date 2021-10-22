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


        SweepMonster robot = new SweepMonster();
        if (!robot.init(250,50, Direction.EAST, 0, 0)){
            System.out.println("Something is wrong, cannot start the cleaning cycle!");
        }else{
            System.out.println("\nLet's clean this up!");
            robot.navigation(digest.getTilesArray());
        }

    }
}
