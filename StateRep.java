import java.io.*;
import java.util.*;


public class StateRep{

    public static void main(String[] args){

        //nur zum Testen, kann aus anderer Datei aufgerufen werden
        boolean [] randomState = createRandomState(25); //hier muss die Anzahl der Psus rein
        
    }

//  Haben wir das schon? 
  /**
     * takes in any state representation and returns List/Array of psu used
     */
    /*
    public static void stateToPSU(boolean [] state){
        for(int i = 0; i < state.length; i++ ){
            if(state[i] == true){

            }
        }
    } */

    /**
     * creates the random state representation for search algorithms
     * takes in the number of Psus 
     */
    public static boolean [] createRandomState(int psuNumber){

        boolean [] state = new boolean [psuNumber];
    
        for(int i = 0; i < state.length; i++ ){
            //creates a random number between 0 and 1, if its greater than 0.5 --> false
            //smaller than 0.5 --> true
            state[i] = (Math.random() < 0.5);
            //for Testing:
            //System.out.print(state[i]);
        }
        return(state);


    }
}