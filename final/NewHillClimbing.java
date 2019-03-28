import java.io.*;
import java.util.*;
import java.nio.*;

public class NewHillClimbing {
    public static boolean[] hillClimbing(Order o) throws Exception {

        //start with a random state
        boolean[] currentState = StateMethods.createRandomState(o);

        //schleife
        int i = 0;
        while(i < Integer.MAX_VALUE){
            //neighborhood of current state erstellen
            List<boolean[]>  allNeighbours = new ArrayList<>(StateMethods.createNeighbours(currentState));

            //find best neighbour of neighbourhood
            boolean[] bestNeighbour = bestState(o, allNeighbours);

            //vergleich best neighbour mit current neighbour
            int curVal = StateMethods.evaluate(o, currentState);
            int neighVal = StateMethods.evaluate(o, bestNeighbour);
            if(curVal < neighVal){
                return(currentState);
            }
            //current state ersetzen 
            currentState = Arrays.copyOf(bestNeighbour, bestNeighbour.length);
           // System.out.println(" Current Value = " + curVal + " Best Neighbour Value: " + neighVal);
            i++;
        }
        //sonst nach maximaler Anzahl Iterationen current State zurück geben
        return currentState;
        

    }
    /**
     * returns best state of a given neighbourhood
     *  = the state that minimizes objective function
     *  In case First-Choice Hill Climbing is selected, the first value that improves evaluation function is returned
     */
    public static boolean[] bestState(Order o, List<boolean[]> neighbours) throws Exception{
        String selAlg = o.getAlg();
        //initialise with first state from neighborhood
        boolean[] bestNeighbour = neighbours.get(0);

        //go through all neighbours, update bestNeighbour if another state is better
        for(int i = 1; i < neighbours.size(); i++){
            if(StateMethods.evaluate(o, bestNeighbour) > StateMethods.evaluate(o, neighbours.get(i))){
                bestNeighbour = neighbours.get(i);
                //in case of First Choice HC, return the state that improves the evaluation function 
                if(selAlg == "First-Choice Hill-Climbing"){
                    return bestNeighbour;
                }
            }
        }
        return bestNeighbour; 
    }
}

