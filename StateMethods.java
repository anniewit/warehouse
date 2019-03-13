import java.io.*;
import java.util.*;


public class StateMethods{

   /**
    * evaluation function that should be minimized: number of Psus used, 
    * penalty if state is not valid (doesn't carry all items ordered): setting to max amount of psus on eval function
    * --> this way an invalid state will always be worse or equal, so we never choose an invalid state
    */    
    public static int evaluate(Order o, boolean[] someState) throws Exception{
        int eval = countPsus(someState);
        if(!valid(o, someState)){
            eval = o.getPsuIDs().length;
        }
        return eval;
    }

    /* valid() determines wether the state is valid
    * a state is valid if it covers all the items in order,
    * it is valid when the order and the set have the same length
    *
    * @ param   possibleState   HashSet with items of a specific state
    *           orderlist       contains all items
    * @ return   boolean         true == state is valid
    */
    public static boolean valid(Order o, boolean[] someState)throws Exception{
        Set<Integer> itemList = backToItems(o, someState);
        ArrayList<Integer> orderlist = o.getOrderList();
        //wenn Anzahl der relevanten items gleich dem der Anzahl in order dann ist state valid:
        if(itemList.size() == orderlist.size()){
            return true;
        }else{
            return false;
        }
    }
    /**
    * countPsus counts the number of PSUs used by a specific state
    * @ param   someState  state using PSUs for which values are true
    * @ return  psuCount   number of PSUs activated in a state (how many PSUS are currectly selected for order)
    */
    public static Integer countPsus(boolean[] someState)throws Exception{
        int psuCount = 0;
        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                psuCount++;
            }
        }
        return psuCount;
    }

    /**
     * Neighbourhood: alle states, die von dem State erreicht werden können,
     * wenn jeweils der Wert von nur 1 Psu an oder ausgeschaltet wird
     * alle states werden in arraylist gespeichert     * 
     */ 
    public static ArrayList<boolean[]> createNeighbours(boolean[] someState){

        ArrayList<boolean[]> hood = new ArrayList<boolean[]>();

        for(int n = 0; n < someState.length; n++){
            if(someState[n]==true){
                someState[n]=false;
            }else{
                someState[n]=true;
            }

            boolean[] newState = Arrays.copyOf(someState, someState.length);
            hood.add(newState);
        }  
      
        return hood;
    }


    /**
     * creates the random state representation for search algorithms
     * takes in the number of Psus in order to determine size of array
     */
    public static boolean [] createRandomState(Order o){

        int psuNumber = o.getPsuIDs().length;
        boolean [] state = new boolean [psuNumber];
    
        for(int i = 0; i < state.length; i++ ){
            //creates a random number between 0 and 1, if its greater than 0.5 --> false
            //smaller than 0.5 --> true
            state[i] = (Math.random() < 0.5);

        }
        return(state);
    }

/**
    * backToItems takes state (selection of psus) as booleanArray and turns it into an set of items (their reference numbers) stored in the selected psus
    *
    * @ new param   items        list of items in relevant PSUs
    * @ return      itemList   a set of items in numbers
    */
    public static Set<Integer> backToItems(Order o, boolean[] someState)throws Exception{

        HashMap<Integer,ArrayList<Integer>> reducedWH = o.getMap();
        Integer[] psuIDs = o.getPsuIDs();
        Set items = new HashSet();

        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                items.add(reducedWH.get(psuIDs[x]));
            }
        }

        return items;

    }


    public  void main(String[] args){
    }
}