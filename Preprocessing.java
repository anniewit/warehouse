import java.io.*;
import java.util.*;
import java.util.Scanner;


public class Preprocessing {

    public static void prepare(Order o) throws IOException, Exception { // throws Exception, FileNotFoundException
        reducePsus(o);
 //       System.out.println(Arrays.asList(o.getMap())); // test
        String selectedAlg = o.getAlg();
        System.out.println(selectedAlg);
        o.setSize();

        int psuNr = o.getSize();    //testing
        getIdentifier(o); //extracts identifiers and sets them in order class
        boolean[] solution = new boolean[psuNr];

        switch (selectedAlg){
/*             case "Hill-Climbing": solution =  HillClimbing.hillClimbing(o);               
                break; */

            case "First-Choice Hill-Climbing": solution = FirstChoice.firstChoice(o);
                break;

                //Ist gerade nur zum kompilieren auskommentiert!, kommentar wegnehmen, zum testen
    /*        case "Random-Restart Hill-Climbing":  solution = randomRestart(o);
                break;
            case "Simulated Annealing": solution = SimulatedAnnealing.simulatedAnnealing(o);
                break;
            case "Local-Beam Search": solution = LocalBeam.local_beam(o);
                break;  */
        } 
        Backtranslation.stateToMap(o, solution);
    } 

    
    /**
     * Aus der gesamten state-space der psus und items werden alle nicht bestellten items und alle leeren psus gelöscht,
     * um suche zu beschleunigen
     * @param o enthält parameter der bestellung
     */
    public static void reducePsus(Order o) throws IOException  {//Exception throws FileNotFoundException
        
        //HashMap<Integer,String> itemsInMap = warehouseReader(o); oben schon
        readOrder(o); //damit getorder funktioniert
        ArrayList<Integer> orderlist = o.getOrderList();
        HashMap<Integer,ArrayList<Integer>> psus = gettingPSUs(o);

        //Neue HashMap für relevante PSUs erstellen:
        HashMap<Integer,ArrayList<Integer>> relevantPSUs = new HashMap<>();

        //Order wird mit den PSU-items verglichen und in Hashmap gespeichert:
        for(Map.Entry<Integer,ArrayList<Integer>> entry: psus.entrySet()) {
            ArrayList<Integer> items = entry.getValue();
            items.retainAll(orderlist);
            //PSUs werden gelöscht, die keine items haben.
            if(!items.isEmpty()){
                relevantPSUs.put(entry.getKey(), items);
            }
        }
        o.setMap(relevantPSUs);
} 
 
    public static BufferedReader read(File file) throws FileNotFoundException {
        //BufferedReader ließt Warenhaus ein
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br;
    }

    //BufferedReader ließt Warenhaus ein, mappt PSU identifier zu Nummern der Items
    private static HashMap<Integer,String> warehouseReader(Order o) throws IOException, FileNotFoundException{
                 
        //try{  
        String whPath = o.getWhPath();
        File whFile = new File(whPath);
        
           
        BufferedReader br = read(whFile);
        //nur Items werden eingelesen (erste Zeile)
        String[] items = br.readLine().split(" ");
        //Items werden zu Zahlen gemappt
        HashMap<Integer,String> itemsInMap = new HashMap<Integer,String>();

        //Items bekommen einen Identifier:
        for(int i = 0; i <= items.length - 1; i++){
            itemsInMap.put(i, items[i]);
            
        }
        o.setNames(itemsInMap);
        return(itemsInMap);
     /*    } catch(IOException e) {
          e.printStackTrace();
          return(null);
        } */
        

    }
    /**
     * Hashset enthält alle bestellten items
     * Set, damit mehrmals auftretende Items in der order zusammengefasst werden können
     */
    private static HashSet<String> orderReader(Order o) throws FileNotFoundException{
            
        //Order wird durch Scanner eingelesen:
        String ordPath = o.getOrdPath();
        File ordFile =  new File(ordPath);
        Scanner sc = new Scanner(ordFile);

        //HashSet für eingelesene order
        HashSet<String> order = new HashSet<>();

        //Eingelesene items werden dem HashSet zugefügt
        while(sc.hasNext()){
            order.add(sc.next().trim());
        }

        sc.close();

        return order;
    }

    /**
     * calls functions to translate order and warehouse from string to set (order) and hashmap (warehouse)
     * checks if ordered items are in warehouse and assigns the numbers to the ordered items
     * sets order as an arraylist in the order class
     */
    private static void readOrder(Order o) throws IOException {//
        HashMap<Integer,String> itemsInMap = warehouseReader(o);
        HashSet order = orderReader(o);

        //List für Keys of items
        ArrayList<Integer> orderlist = new ArrayList<Integer>();


        //Items in HashSet werden mit items im Warenhaus verglichen
        //Keys der Items in dem Order werden in ArrayList gespeichert
        for(Map.Entry<Integer,String> entry : itemsInMap.entrySet()){
            if(order.contains(entry.getValue()))
                orderlist.add(entry.getKey());
        }
        System.out.println(Arrays.toString(orderlist.toArray()));
        o.setOrderList(orderlist);
    } 

    /**
     * creates a hashmap of all psus, containing all psus mapped to the items stored in them
     */
    public static HashMap<Integer,ArrayList<Integer>> gettingPSUs(Order o) throws IOException, FileNotFoundException{
        String line; //Eine Zeile in dem Warenhaus
        int id = 100000;//start identifier für die PSUs
        File whFile = new File(o.getWhPath());
        BufferedReader br = read(whFile);
        HashMap<Integer, String> itemsInMap = warehouseReader(o);
        //Initalisierung der ArrayList<Integer>, darin werden die items gespeichert
        ArrayList<Integer> list = new ArrayList<Integer>();
        //Initialisierung einer neuen HashMap,die die identifieres als keys und die items in listen als values hat.
        HashMap<Integer,ArrayList<Integer>> psus = new HashMap<>();

        //Diese Schleife durchläuft das Warenhaus Zeile für Zeile
        while((line = br.readLine()) != null){
        //Dann wird auf die items in der HashMap durch Map.Entry zugegriffen
            for(Map.Entry<Integer,String> entry : itemsInMap.entrySet()){
                //Falls item der Zeile in der HashMap ist wird dieses in ArrayList gespeichert
                if(line.contains(entry.getValue())){
                    list.add(entry.getKey());
                }
            //eine neue Liste wird angefertigt und in der HashMap gespeichert
            psus.put(id, new ArrayList<Integer>(list));
            }
        //die identifieres werden hochgezählt
        id++;
        //die liste wird gelöscht um neue Elemente zu speichern
        list.clear();
        }

        return psus;
    } 

    //gets the key of the Hashmap --> the PSU identifiers 
    public static void getIdentifier(Order o){

        HashMap psuMap = o.getMap();
        Set<Integer> keys = psuMap.keySet();
        Integer[] identifiers = keys.toArray(new Integer[keys.size()]);

        o.setPsuIDs(identifiers);
        
    }




}
