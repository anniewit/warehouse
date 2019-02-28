import java.io.*;
import java.util.*;

/**
 * Object that stores input information from user and Zwischenschritte and gives access from several files
 */
public class Order {
    
    private  String orderPath;
    private  String warehousePath;
    private String alg;
    private int repetitions;
    private HashMap reduced; //mapping of PSU identifiers to items, after deleting unused items and Psus
    private int stateSize = reduced.size();
    private Integer[] psuIDs; //holds names of PSUs
    private ArrayList orderedItems; //holds identifiers of items that were ordered

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
    public void setOrder(ArrayList itemlist){
        this.orderedItems = itemlist;
    }
    public ArrayList getOrder(){
        return(orderedItems);
    }

}
