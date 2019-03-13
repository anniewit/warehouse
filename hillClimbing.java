import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class HillClimbing {

    public static boolean [] hillClimbing(Order o) throws Exception{ //?

       // HillClimbing hillClimbing = new HillClimbing();
        boolean[] currentState = StateMethods.createRandomState(o);
        System.out.println(Arrays.toString(currentState));
        int m = 0;
        int maxIterations = 10; //arbitrary
        boolean[] neighbour;    
        while(m <= maxIterations){
            neighbour = bestState(o, currentState);
            System.out.println("Neighbour " + Arrays.toString(neighbour)); //
            System.out.println(StateMethods.countPsus(neighbour));
            System.out.println(StateMethods.countPsus(currentState));
            
            if(StateMethods.countPsus(neighbour) >= StateMethods.countPsus(currentState)){
                return(currentState);
            } else {
                currentState = neighbour;
            }
            m++;
            System.out.println(m); //
        }
        System.out.println(Arrays.toString(currentState)); //
        return currentState;
    }

    /**
    * bestState does the hillClimbing
    *
    * @ param   firstNeighbour
    *           secondNeighbour     neighbours
    *           lowestNeighbour    neighbour with highest value
    *           validStates         all positions of neighbours with valid states
    *           x                   iterates through validStates
    * @ return  current_value
    *           neighbour_value     returns highest value
    */
    public static boolean[] bestState(Order o, boolean[] currentState) throws Exception{

        int stateLength = currentState.length;
        int current_value = StateMethods.countPsus(currentState);

        boolean[] firstNeighbour = new boolean[stateLength];
        boolean[] secondNeighbour = new boolean[stateLength];
        boolean[] lowestNeighbour = new boolean[stateLength];

        int neighbour_value = 0;
        int position;
        int x = 0;

        ArrayList<Integer> validStates = new ArrayList<Integer>();

        for(int i = 0; i < stateLength; i++){
            firstNeighbour = neighbour(o,i);
            if(StateMethods.check_validity(o, firstNeighbour) == true){
                validStates.add(i);
            }
        }
        while(x < validStates.size() - 1){
            //position des randomStartingState boolean wird hochgezählt
            //die beiden nächsten neighbours werden immer verglichen:
            position = validStates.get(x);
            firstNeighbour = neighbour(o,position);
            int neighbourOne = StateMethods.countPsus(firstNeighbour);
            x++;
            position = validStates.get(x);
            secondNeighbour = neighbour(o,x);
            int neighbourTwo = StateMethods.countPsus(secondNeighbour);
            //Der neighbour, dessen PSUzahl am kleinsten ist wird gespeichert
            if(neighbourOne >= neighbourTwo){
                //lowestNeighbour = firstNeighbour;
                System.arraycopy(firstNeighbour,0,lowestNeighbour,0,stateLength);
                neighbour_value = neighbourOne;
            }else{
                //lowestNeighbour = secondNeighbour;
                System.arraycopy(secondNeighbour,0,lowestNeighbour,0,stateLength);
                neighbour_value = neighbourTwo;
            }
        }
        //wenn der lowestNeighbour niedriger ist, dann überschreibe randomStartingState.
        if(neighbour_value <= current_value){
            return currentState;
        }else{
            System.arraycopy(lowestNeighbour,0,currentState,0,stateLength);
            return currentState;
        }
    }

    /**
    * neighbour creates a neighbour by changing one value of the current state
    *
    * @ param
    * @ return  someState   neighbour of the currentState
    */
    public static boolean[] neighbour(Order o, int n) {
        boolean[] someState = StateMethods.createRandomState(o);
        if(someState[n]==true){
            someState[n]=false;
        }else{
            someState[n]=true;
        }
        return someState;
    }









}
