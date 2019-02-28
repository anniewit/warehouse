import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class HillClimbing {

    /**
    * getIdentifiers has access to psus HashMap and gets the key of each value
    * @ param   warehouse       in which PSUs are saved, together with their ID
    *           keys            the identifieres themselves
    * @ return  identifieres    an Array that saves the identifieres
    */
    public Integer[] getIdentifiers() throws Exception{
        /**
        * Calling the Preprocessing class to access the warehouse, etc.
        */
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

    /**
    * randomState creates a boolean starting state that has random boolean values
    * @ param   identifieres    the IDs of the PSUs
    *           random          a class provided by Java to set values at random
    *           i               a counter, to iterate through the identifiersArray
    * @ return  randomStartingState     the boolean array
    */
    public boolean[] randomState() throws Exception{
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

    /**
    * backToItems takes state as booleanArray and turns it into an set
    *
    * @ new param   integers        list of items in relevant PSUs
    * @ return      possibleState   a set of items in numbers
    */
    public Set<Integer> backToItems(boolean[] someState)throws Exception{
        PreprocessingUpdate p = new PreprocessingUpdate();
        HashMap<Integer,ArrayList<Integer>> warehouse = p.getStateSpace();
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
        return possibleState;

    }
    /**
    * check_validity determines wether the state is valid
    * a state is valid if it covers all the items in order,
    * it is valid when the order and the set have the same length
    *
    * @ param   possibleState   HashSet with items of a specific state
    *           orderlist       contains all items
    * @ return   boolean         true == state is valid
    */
    public boolean check_validity(boolean[] someState)throws Exception{
        PreprocessingUpdate p = new PreprocessingUpdate();
        Set<Integer> possibleState = this.backToItems(someState);
        ArrayList<Integer> orderlist = p.gettingTheOrder();
        //wenn Anzahl der relevanten items gleich dem der Anzahl in order dann ist state valid:
        if(possibleState.size() == orderlist.size()){
            return true;
        }else{
            return false;
        }
    }
    /**
    * countPsus counts the number of PSUs used by a specific state
    *
    * @ param   someState   state using PSUs for which values are true
    * @ return  countPsus   integer with number of PSUs
    */
    public Integer countPsus(boolean[] someState)throws Exception{
        int countPsus = 0;
        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                countPsus++;
            }
        }
        return countPsus;
    }

    /**
    * neighbour creates a neighbour by changing one value of the current state
    *
    * @ param
    * @ return  someState   neighbour of the currentState
    */
    public boolean[] neighbour(int n)throws Exception{
        boolean[] someState = randomState();
        if(someState[n]==true){
            someState[n]=false;
        }else{
            someState[n]=true;
        }
        return someState;
    }

    /**
    * bestState does the hillClimbing
    *
    * @ param   firstNeighbour
    *           secondNeighbour     neighbours
    *           highestNeighbour    neighbour with highest value
    *           validStates         all positions of neighbours with valid states
    *           x                   iterates through validStates
    * @ return  current_value
    *           neighbour_value     returns highest value
    */
    public Integer bestState(boolean[] currentState)throws Exception{

        int stateLength = currentState.length;
        int current_value = this.countPsus(currentState);

        boolean[] firstNeighbour = new boolean[stateLength];
        boolean[] secondNeighbour = new boolean[stateLength];
        boolean[] highestNeighbour = new boolean[stateLength];

        int neighbour_value = 0;
        int position;
        int x = 0;

        ArrayList<Integer> validStates = new ArrayList<Integer>();

        for(int i = 0; i < stateLength; i++){
            firstNeighbour = this.neighbour(i);
            if(this.check_validity(firstNeighbour)==true){
                validStates.add(i);
            }
        }
        while(x < validStates.size()){
            //position des randomStartingState boolean wird hochgezählt
            //die beiden nächsten neighbours werden immer verglichen:
            position = validStates.get(x);
            firstNeighbour = this.neighbour(position);
            int neighbourOne = this.countPsus(firstNeighbour);
            x++;
            position = validStates.get(x);
            secondNeighbour = this.neighbour(x);
            int neighbourTwo = this.countPsus(secondNeighbour);
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
            return currentState;
        }else{
            System.arraycopy(highestNeighbour,0,currentState,0,stateLength);
            return currentState;
        }
    }


    public static void main(String[] args){
        HillClimbing hillClimbing = new HillClimbing();
        boolean[] currentState = this.randomState();
        while(!break){
            hillClimbing.bestState(currentState);
            //TODO
            //if(termination condition met){
            //    break;
            //}
        }
    }







}
