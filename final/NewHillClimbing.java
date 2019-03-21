import java.io.*;
import java.util.*;
import java.nio.*;

public class NewHillClimbing {
    public static boolean[] hillClimbing(Order o) throws Exception {

        //Random state
        boolean[] currentState = StateMethods.createRandomState(o);

        //schleife
        int i = 0;
        while(i < 100){
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
            System.out.println(" Current Value = " + curVal + " Best Neighbour Value: " + neighVal);
            i++;
        }
        //sonst nach maximaler Anzahl Iterationen current State zurück geben
        return currentState;
        

    }
    public static boolean[] bestState(Order o, List<boolean[]> neighbours) throws Exception{
        boolean[] bestNeighbour = neighbours.get(0);

        //go through all neighbours, update bestNeighbour if another state is better
        for(int i = 1; i < neighbours.size(); i++){
            if(StateMethods.evaluate(o, bestNeighbour) > StateMethods.evaluate(o, neighbours.get(i))){
                bestNeighbour = neighbours.get(i);
            }
        }
        return bestNeighbour; 
    }
}

