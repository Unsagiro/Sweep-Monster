package src;

import java.util.HashMap;

import javafx.util.Pair;

public class RoboMemory {


    private HashMap<Pair<Integer,Integer>,String> tileDirtness = new HashMap<Pair<Integer,Integer>,String>();

     

////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////



public void dirtLogWrite(Pair<Integer,Integer> position, String dirt){
    tileDirtness.put(position, dirt);
    
}

public void cleaningProtocol(Pair<Integer,Integer> position){

    String dirtness = tileDirtness.get(position);
    int newDirtness = Integer.parseInt(dirtness);
    System.out.println("Cleaning..."); 
    while(newDirtness != 0 ){
        newDirtness = newDirtness - 1;
        System.out.println("Starting new cleaning cycle..." + "Dirtness left = " + newDirtness);
        
    }

}


public String howManyMoreCleans(Pair<Integer,Integer> position){ // returns how many more cleans are needed in this tile
    return tileDirtness.get(position);
}


////////////////////////////////////////////////////////////////////////////////////////

}

