import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class HillClimbing {


    public Integer[] getIdentifiers(){
        //Identifiers der PSUs werden in ein Array (identifieres) gespeichert
        PreprocessingUpdate p = new PreprocessingUpdate();
        HashMap<Integer,ArrayList<Integer>> warehouse = p.getStateSpace();
        Set<Integer> keys = warehouse.keySet();
        Integer[] identifieres = keys.toArray(new Integer[keys.size()]);
        //für die Ausgabe:
        //for(int i = 0; i <= identifieres.length - 1; i++){
        //    System.out.println(identifieres[i]);
        //}
        return identifieres;
    }


    public boolean[] randomState() {
        Integer[] identifieres = getIdentifiers();
        //Ein booleanArray wird mit Länge Anzahl der PSUs erstellt
        boolean[] randomStartingState = new boolean[identifieres.length];
        //Dieser wird mit random boolean values gefüllt:
        Random random = new Random();
        for(int i = 0; i <= identifieres.length - 1; i++){
            randomStartingState[i] = random.nextBoolean();
            //booleanArray wird ausgegeben:
            //System.out.println(randomStartingState[i]);
        }
        return randomStartingState;
    }
    public boolean check_validity(boolean[] someState){
        PreprocessingUpdate p = new PreprocessingUpdate();
        HashMap<Integer,ArrayList<Integer>> warehouse = p.getStateSpace();
        ArrayList<Integer> orderlist = p.gettingTheOrder();
        Integer[] identifieres = this.getIdentifiers();
        ArrayList<Integer> integers = new ArrayList<Integer>();


        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                //neues HashSet wird erstellt um relevante items zu speichern,
                //HashSet, weil keine doppelten Werte möglich sind
                integers = warehouse.get(identifieres[x]);
            }
        }
        Set<Integer> possibleState = new LinkedHashSet<Integer>(integers);
        //wenn Anzahl der relevanten items gleich dem der Anzahl in order dann ist state valid:
        //wäre schön wenn man das auseinander ziehen könnte aber dann muss man nochmals durchiterieren.
        if(possibleState.size() == orderlist.size()){
            return true;
        }else{
            return false;
        }
    }

    public Integer countPsus(boolean[] someState){
        int countPsus = 0;
        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                countPsus++;
            }
        }
        return countPsus;
    }

    //neighbour wird erstellt, das heißt ein value des booleanArrays wird verändert
    public boolean[] neighbour(int n){
        boolean[] someState = randomState();
        if(someState[n]==true){
            someState[n]=false;
        }else{
            someState[n]=true;
        }
        return someState;
    }




    public Integer bestState(){
        int x = 0;
        HillClimbing hillClimbing = new HillClimbing();
        int stateLength = hillClimbing.randomState().length;
        boolean[] firstNeighbour = new boolean[stateLength];
        boolean[] secondNeighbour = new boolean[stateLength];
        boolean[] highestNeighbour = new boolean[stateLength];
        int neighbour_value;
        //der current_value ist die Anzahl der PSUs vom startingState
        boolean[] currentState = hillClimbing.randomState();
        int current_value = hillClimbing.countPsus(currentState);
        int position;

        ArrayList<Integer> validStates = new ArrayList<Integer>;

        for(int i = 0; i < stateLength; i++){
            firstNeighbour = hillClimbing.neighbour(i);
            if(hillClimbing.check_validity(firstNeighbour)==true){
                validStates.add(i);
            }

        }
        while(x < validStates.size()){
            //position des randomStartingState boolean wird hochgezählt
            //die beiden nächsten neighbours werden immer verglichen:
            position = validStates.get(x)
            firstNeighbour = hillClimbing.neighbour(position);
            int neighbourONe = hillClimbing.countPsus(firstNeighbour);
            x++;
            position = validStates.get(x)
            secondNeighbour = hillClimbing.neighbour(i);
            int neighbourTwo = hillClimbing.countPsus(secondNeighbour);
            //Der neighbour, dessen PSUzahl am kleinsten ist wird gespeichert
            if(neighbourOne >= neighbourTwo){
                //highestNeighbour = firstNeighbour;
                System.arraycopy(firstNeighbour,0,highestNeighbour,0,stateLength);
                neighbour_value = neighbourOne;
            }else{
                //highestNeighbour = secondNeighbour;
                System.arraycopy(secondNeighbour,0,highestNeighbour,0,stateLength);
                neighbour_value = neighbourTwo;
            }
        }
        //wenn der highestNeighbour größer ist, dann überschreibe randomStartingState.
        if(neighbour_value <= current_value){
            System.out.println(current_value + "It was the starting state! Yah!!");
            return current_value;
        }else{
            System.out.println(neighbour_value + "It was the neighbour!");
            return neighbour_value;
            //randomStartingState = highestNeighbour;
        }
    }




}
