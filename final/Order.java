import java.io.*;
import java.util.*;

/**
 * Object that stores input information from user and Zwischenschritte and gives access from several files
 */
public class Order {
    
    private String orderPath;       //Path inserted by user as order file
    private String warehousePath;   //Path inserted by user as warehouse file
    private String alg;             //algorithm selected by user
    private int repetitions;        //number of repetitions for local beam serach/parallel hill-climbing
    private HashMap<Integer,ArrayList<Integer>> reduced; //mapping of PSU identifiers to items, after deleting items and Psus that are not part of order
    private int stateSize;          //size of state representation --> number of all PSUs that contain ordered items 
    private Integer[] psuIDs;       //holds names/itendifiers of PSUs
    private ArrayList orderedItems; //holds identifiers of items that were ordered Integers
    private HashMap<Integer,String> taggedItems;    //contains the names of all items as string mapped to their identifiers (0,1,2,..)
    private HashMap<Integer, String[]> finalPsus;   //holds the final psu identifiers and their (ordered) items


    public Order (String whPath, String ordPath, String selAlg, int rep){
        
        this.orderPath = ordPath;
        this.warehousePath = whPath;
        this.alg = selAlg;
        this.repetitions = rep;

    } 

    public String getWhPath(){
        return(this.warehousePath);
    }
    public String getOrdPath(){
        return(this.orderPath);
    }
    public String getAlg(){
        return(this.alg);
    }
    public int getRep(){
        return(this.repetitions);
    }
    public void setMap(HashMap map){
        this.reduced = map;
    }
    public HashMap getMap(){
        return reduced;
    }
    public void setPsuIDs(Integer[] identifier){
        this.psuIDs = identifier;
    }
    public Integer [] getPsuIDs(){
        return(this.psuIDs);
    }
    public void setOrderList(ArrayList itemlist){
        this.orderedItems = itemlist;
    }
    public ArrayList getOrderList(){
        return(orderedItems);
    }
    public void setSolution(HashMap<Integer,String[]> solution){
        this.finalPsus = solution;
    }
    public HashMap getSolution(){
        return(this.finalPsus);
    }
    public void setSize(){
        this.stateSize = this.reduced.size();
    }
    public int getSize(){
        return this.stateSize;
    }
    public void setNames(HashMap<Integer,String> itemsInMap){
        this.taggedItems = itemsInMap;
    }
    public HashMap<Integer,String> getNames(){
        return this.taggedItems;
    }

}
