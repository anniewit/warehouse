import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class TryingHillClimbing {



    public int countPsus(boolean[] someState){
        int countPsus = 0;
        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                //neues HashSet wird erstellt um relevante items zu speichern,
                //HashSet, weil keine doppelten Werte möglich sind
                Set<Integer> possibleState = new LinkedHashSet<>();
                possibleState.add(warehouse.get(identifieres[x]));
                countPsus++;
            }
        }
        //wenn Anzahl der relevanten items gleich dem der Anzahl in order dann ist state valid:
        //wäre schön wenn man das auseinander ziehen könnte aber dann muss man nochmals durchiterieren.
        if(possibleState.size() == orderlist.length()){
            return countPsus;
        }else{
            break; //TODO
        }
    }

    //neighbour wird erstellt, das heißt ein value des booleanArrays wird verändert
    public boolean[] neighbour(boolean[] someState, int n){
        if(someState[n]==true){
            someState[n]=false;
        }else{
            someState[n]=true;
        }
        neighbour = someState;
        return neighbour;
    }



    public static void main(String[] args) {
        boolean[] firstNeighbour = new boolean[randomStartingState.length];
        boolean[] secondNeighbour = new boolean[randomStartingState.length];
        boolean[] highestNeighbour = new boolean[randomStartingState.length];
        int neighbour_value;
        //der current_value ist die Anzahl der PSUs vom startingState
        int current_value = this.countPsus(randomStartingState);

        while(i < (randomStartingState.length - 1)){
            //position des randomStartingState boolean wird hochgezählt
            //die beiden nächsten neighbours werden immer verglichen:
            firstNeighbour = this.neighbour(randomStartingState,i);
            int neighbourOne = this.countPsus(firstNeighbour);
            i++;
            secondNeighbour = this.neighbour(randomStartingState,i);
            int neighbourTwo = this.countPsus(secondNeighbour);
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
