import java.io.*;
import java.util.*;


public class StateMethods{


    public  void main(String[] args){

        //nur zum Testen, kann aus anderer Datei aufgerufen werden
        //boolean [] randomState = createRandomState(25); //hier muss die Anzahl der Psus rein
        
    }
/*     public static void setOrderSize(int orderLength){
        orderSize = orderLength;
    } */

    // could also be in preprocessing
    public static Integer[] getIdentifier(HashMap psuMap){
        Set<Integer> keys = psuMap.keySet();
        Integer[] identifiers = keys.toArray(new Integer[keys.size()]);

        return(identifiers);
    }

    /**
     * Objective function for states
     * to check if a state is valid: for each selected PSU (true) 
     * add all its items into a Hashset (so that items dont appear multiple times)
     * then compare the size of Hashmap with length of order, to see if everything is contained
     */
    public static int countPsus(boolean[] someState, int orderSize, HashMap psuMap){
        int psuCount = 0;

        //neues HashSet wird erstellt um relevante items zu speichern,
        //HashSet, weil keine doppelten Werte möglich sind
        Set<Integer> possibleState = new LinkedHashSet<>();

        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                
                //die items aus der jeweiligen Psu werden zu possible state hinzugefügt
                //muss von object in integer umgewandelt werden
                possibleState.add((Integer)psuMap.get(getIdentifier(psuMap)[x])); //identifieres[x] 
                psuCount++;
            }
        }
        //wenn Anzahl der relevanten items gleich dem der Anzahl in order dann ist state valid:
        //wäre schön wenn man das auseinander ziehen könnte aber dann muss man nochmals durchiterieren.
        if(possibleState.size() == orderSize){
            return psuCount;
        }else{
             //TODO
        }
        //missing return statement
    }

    //neighbour wird erstellt, das heißt ein value des booleanArrays wird verändert
    public static boolean[] createNeighbours(boolean[] someState, int n){
        if(someState[n]==true){
            someState[n]=false;
        }else{
            someState[n]=true;
        }
        boolean [] neighbour = someState;
        return neighbour;
    }


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