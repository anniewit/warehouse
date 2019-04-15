import java.io.*;
import java.util.*;


public class StateMethods{

   /**
    * evaluation function that should be minimized: number of Psus used 
    * penalty if state is not valid (doesn't carry all items ordered): setting to max amount of psus + 1
    * --> this way an invalid state will always be worse, so we never choose an invalid state
    *
    * @ return int evaluation of state
    */    
    public static int evaluate(Order o, boolean[] neighbour) throws Exception{
        int eval = countPsus(neighbour);
        if(!valid(o, neighbour)){
            eval = o.getPsuIDs().length + 1; //damit auch alle psus losschicken besser ist
        }
      /*   int missing = missingItems(o, neighbour);
        eval = eval * (missing */
        return eval;
    }

   /**
    * valid() determines wether a given state is valid
    * a state is valid if it covers all the items in order,
    * it is valid when the order and the set have the same length
    *
    * @ param   itemList        HashSet of items of a specific state that can be transported by selected PSUs
    *           orderlist       contains all items
    * @ return  boolean         true == state is valid
    */
    public static boolean valid(Order o, boolean[] neighbour)throws Exception{
        Set<Integer> itemList = backToItems(o, neighbour);
        ArrayList<Integer> orderlist = o.getOrderList();
        //wenn Anzahl der transportierten items gleich der order Anzahl ist, dann ist state valid:
        if(itemList.size() == orderlist.size()){
            return true;
        }else{
            return false;
        }
    } 
/*     public static int missingItems(Order o, boolean[] neighbour)throws Exception{
        Set<Integer> transportedItems = backToItems(o, neighbour);

        ArrayList<Integer> orderlist = o.getOrderList();
        int missingItems = 0;

        for(int i = 0; i < orderlist.size(); i++){
            if(!transportedItems.contains(orderlist.get(i))){
                missingItems++;
            }
        }

        return missingItems;
    } */


    /**
    * countPsus counts the number of PSUs used by a specific state
    * @ param   neighbour  state using PSUs for which values are true
    * @ return  psuCount   number of PSUs activated in a state (how many PSUS are currectly selected for order)
    */
    public static Integer countPsus(boolean[] neighbour)throws Exception{
        int psuCount = 0;
        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < neighbour.length; x++){
            if(neighbour[x] == true){
                psuCount++;
            }
        }
        return psuCount;
    }

    /**
     * Neighbourhood: alle states, die von dem State erreicht werden können,
     * wenn jeweils der Wert von nur 1 Psu an oder ausgeschaltet wird
     * alle states werden in arraylist gespeichert und zurückgegeben     
     */ 
    public static ArrayList<boolean[]> createNeighbours(boolean[] startState){

        ArrayList<boolean[]> hood = new ArrayList<boolean[]>();

        for(int n = 0; n < startState.length; n++){

            boolean[] neighbour = Arrays.copyOf(startState, startState.length);
            if(neighbour[n]==true){
                neighbour[n]=false;
            }else{
                neighbour[n]=true;
            }

            boolean[] newState = Arrays.copyOf(neighbour, neighbour.length);
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
            //creates a random number between 0 and 1, if its greater than 0.5 --> false, smaller than 0.5 --> true
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

/*         //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < neighbour.length; x++){
            if(neighbour[x] == true){
                //alle items der Psus in ein Set
                Set psuContent = new HashSet(Arrays.asList(reducedWH.get(psuIDs[x])));
                items.add(psuContent); //wird referenz oder inhalt eingefügt 
                    //werden einzelne zahlen oder array eingefügt?
            }
        } */
        //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
        for(int x = 0; x < someState.length; x++){
            if(someState[x] == true){
                    int currentPsuNum = psuIDs[x];
                    ArrayList currentPsusContent = new ArrayList<>(reducedWH.get(currentPsuNum));
                    //Set mySet = new HashSet(Arrays.asList())
                    for(int i=0; i<currentPsusContent.size(); i++){
                        items.add(currentPsusContent.get(i));
                    }
               }
           }

        return items;

    }

}