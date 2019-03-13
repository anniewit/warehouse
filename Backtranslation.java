import java.io.*;
import java.util.*;


public class Backtranslation {
    /**
     * Backtranslation after solution state has been found
     * new Hashmap(final psus) is created: for each activated PSU, 
     * adds the identifier of the psu and its items (as numbers) into Hashmap
     * @param o
     * @param state
     */
    public static void stateToMap(Order o, boolean[] state){
        HashMap<Integer,ArrayList<Integer>> reducedWH = o.getMap();
        Integer[] psuIDs = o.getPsuIDs();
        HashMap<Integer,ArrayList<Integer>> finalPSUs = new HashMap();

        for(int i = 0; i < state.length; i++){
            if(state[i] == true){
                finalPSUs.put(psuIDs[i],reducedWH.get(psuIDs[i]));
            }
        }

        itemNrToString(o,finalPSUs);
    }

    /**
     * Translates the item numbers that we have worked with until now back into the product names (string) of the items
     * for printing the solution back to the screen
     * @param o
     * @param itemNr
     * @return a map of the final PSUs, their identifiers mapped to the item names as string
     */
    public static void itemNrToString(Order o, HashMap<Integer, ArrayList<Integer>> solutionItemNrs){
      ///// HashMap<Integer,Integer[]> solutionItemNrs = o.getSolution();
        HashMap<Integer, String> itemNames = o.getNames();
        HashMap<Integer, String[]> solutionItemNames = new HashMap<Integer,String[]>();

        //for each PSU of the solution, take their identifier and the names of the items stored inside
        for(Map.Entry<Integer, ArrayList<Integer>> entry : solutionItemNrs.entrySet()) {
            Integer identifier = entry.getKey();
            ArrayList<Integer> itemsNrs = entry.getValue();
            //retrieve the names for the itemsNrs --> Arraylist of numbers into String [] of product names
            String [] names = new String [itemsNrs.size()];
            for(int i=0; i < itemsNrs.size(); i++){
                names[i] = itemNames.get(itemsNrs.get(i));
            }

            solutionItemNames.put(identifier, names);
        }

        o.setSolution(solutionItemNames);
    }
}