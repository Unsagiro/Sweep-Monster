package src;


public class SweepMonsterApplication{
    public static void main(String[] args){
        System.out.println("Sweep Monster Online!\n");
        System.out.println("Team Members:");
        System.out.println("Alejandro Zamora");
        System.out.println("Dong Li");
        System.out.println("Jingni Wang");
        System.out.println("Aldo Brigneti\n");
        System.out.println("Let's clean this up!");

        FloorFileDigestion digest = new FloorFileDigestion();
        digest.run();
        SweepMonster a = new SweepMonster();
        a.run(digest.getTilesArray());
    }
}
