import java.io.*;
import java.util.*;
import java.util.Scanner;

/**
 * Object that stores input information from user and Zwischenschritte and gives access from several files
 */
public class Order {
    
    private  String orderPath;
    private  String warehousePath;
    private String alg;
    private int repetitions;
    private HashMap reduced;

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
    public String getAlg(String name){
        return(this.alg);
    }
    public void setMap(HashMap map){
        this.reduced = map;
    }
    public HashMap getMap(){
        return reduced;
    }
    


}
