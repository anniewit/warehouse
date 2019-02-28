import java.io.*;
import java.util.*;

/**
* This implements First-choice HillClimbing
* First-choice picks the first neighbour that improves the objective funtion.
*/
public class FirstChoice {

    public static void main(String[] args){
        /**
        * HillClimbing class is called in order to gain access to methods
        */
        HillClimbing hillClimbing = new HillClimbing();
        /**
        * @param    currentState    getting a random starting state as the current state
        *           stateLength     getting its length
        *           current_value   getting its value (the number of PSUs it uses).
        */
        boolean[] currentState = hillClimbing.randomState();
        int stateLength = hillClimbing.randomState().length;
        int current_value = hillClimbing.countPsus(currentState);

        /**
        * @param    neighbour   initializing new state holder for current's neighbour
        */
        boolean[] neighbour = new boolean[stateLength];

        int counter = 0; //counts position of the currentState array

        //MUSS NOCH KOMMENTIERT WERDEN:
        while(counter < (stateLength - 1)){
            neighbour = hillClimbing.neighbour(i);
            if(hillClimfalse.check_validity(neighbour)==false){
                break;
            int neighbour_value = hillClimbing.countPsus(neighbour);
            if(neighbour_value <= current_value){
                counter++;
            }else{
                System.arraycopy(neighbour,0,currentState,0,stateLength);
                break;
            }

        }




    }

}
