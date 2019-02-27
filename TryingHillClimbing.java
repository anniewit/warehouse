import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class TryingHillClimbing {


    public static void main(String[] args) {
    }

    public static boolean [] hillClimbing(HashMap psuMap, int orderSize){
       
        //get amount of PSUs in order to generate random state
        Integer [] identifier = StateMethods.getIdentifier(psuMap);

        boolean [] randomStartingState = StateMethods.createRandomState(identifier.length);

        boolean[] firstNeighbour = new boolean[randomStartingState.length];
        boolean[] secondNeighbour = new boolean[randomStartingState.length];
        boolean[] highestNeighbour = new boolean[randomStartingState.length];
        int neighbour_value;
        //der current_value ist die Anzahl der PSUs vom startingState
        int current_value = StateMethods.countPsus(randomStartingState, orderSize, psuMap);

        int i = 0;
        while( i < (randomStartingState.length - 1)){
            //position des randomStartingState boolean wird hochgezählt
            //die beiden nächsten neighbours werden immer verglichen:
            firstNeighbour = StateMethods.createNeighbours(randomStartingState,i);
            int neighbourOne = StateMethods.countPsus(firstNeighbour, orderSize, psuMap);
            i++;
            secondNeighbour = StateMethods.createNeighbours(randomStartingState,i);
            int neighbourTwo = StateMethods.countPsus(secondNeighbour, orderSize, psuMap);
            //Der neighbour, dessen PSUzahl am kleinsten ist wird gespeichert
            if(neighbourOne >= neighbourTwo){
                highestNeighbour = firstNeighbour;
                neighbour_value = neighbourOne;
            }else{
                highestNeighbour = secondNeighbour;
                neighbour_value = neighbourTwo;
            }
        }
        //wenn der highestNeighbour größer ist, dann überschreibe randomStartingState.
        if(neighbour_value <= current_value){
            //TODO
        }else{
            randomStartingState = highestNeighbour;
        }





    }
}
