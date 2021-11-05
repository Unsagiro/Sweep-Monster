import java.util.HashMap;

public class RoboMemory {


    private HashMap<Pair,String> tileDirtness = new HashMap<Pair,String>();


public void dirtLogWrite(Pair position, String dirt){
    tileDirtness.put(position, dirt);
    
}

public void cleaningProtocol(Pair position){

    String dirtness = tileDirtness.get(position);
    int newDirtness = Integer.parseInt(dirtness);
    System.out.println("Cleaning..."); 
    while(newDirtness != 0 ){
        newDirtness = newDirtness - 1;
        System.out.println("Starting new cleaning cycle..." + "Dirtness left = " + newDirtness);
        
    }
}


public String howManyMoreCleans(Pair position){ // returns how many more cleans are needed in this tile
    return tileDirtness.get(position);
}


}

