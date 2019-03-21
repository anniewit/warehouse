import java.io.*;
import java.util.*;
import java.util.Scanner;


public class PreprocessingUpdate {

    private static String orderPath = ".\\order11.txt";
    private static String warehousePath = ".\\problem1.txt";


/*    public static static void main(String[] args) {
        String orderPath = ".\\order11.txt";
        String warehousePath = ".\\problem1.txt";
        HashMap map = generateHashMap(warehousePath, orderPath);


        System.out.println(Arrays.asList(map));

             // print for testing
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
               System.out.print(", ");
            }
            System.out.print(arr[i]);
        }

    }
    */

    public static void main(String[] args)throws Exception{
        valid();
    }



    public static BufferedReader read(File file) throws FileNotFoundException {
        //BufferedReader ließt Warenhaus ein:
        //File file = new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br;
    }

    public static HashMap<Integer,String> warehouseReader()throws Exception, FileNotFoundException{
            //BufferedReader ließt Warenhaus ein:
            //warehousePath = ".\\problem1.txt";
            File whFile = new File(warehousePath);
            BufferedReader br = read(whFile);
            //BufferedReader br = new BufferedReader(new FileReader(whFile));

            //nur Items werden eingelesen

            String[] items = br.readLine().split(" ");
            //Items werden zu Zahlen gemappt
            HashMap<Integer,String> itemsInMap = new HashMap<Integer,String>();
            //Items bekommen einen Identifier:
            //i = itemPostition


            for(int i = 0; i <= items.length - 1; i++){
                itemsInMap.put(i, items[i]);
            }
        return(itemsInMap);

    }

    public static HashSet<String> orderReader() throws Exception, FileNotFoundException{
            //Order wird durch Scanner eingelesen:
            File ordFile =  new File(orderPath);
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


    public static ArrayList<Integer> gettingTheOrder() throws Exception{
        HashMap<Integer,String> itemsInMap = warehouseReader();
        HashSet order = orderReader();

        //ArrayList für Keys of items
        ArrayList<Integer> orderlist = new ArrayList<Integer>();


        //Items in HashSet werden mit items im Warenhaus verglichen
        //Keys der Items in dem Order werden in ArrayList gespeichert
        for(Map.Entry<Integer,String> entry : itemsInMap.entrySet()){
            if(order.contains(entry.getValue()))
                orderlist.add(entry.getKey());
        }
        System.out.println(orderlist);
        return orderlist;
    }


    public static HashMap<Integer,ArrayList<Integer>> gettingPSUs() throws Exception, FileNotFoundException{
        int id = 1000;//Einen identifier für die PSUs
        File whFile = new File(warehousePath);
        BufferedReader br = read(whFile);
        HashMap<Integer, String> itemsInMap = warehouseReader();
        //Initalisierung der ArrayList<Integer>, darin werden die items gespeichert
        ArrayList<Integer> list = new ArrayList<Integer>();
        //Initialisierung einer neuen HashMap,die die identifieres als keys und die items in listen als values hat.
        HashMap<Integer,ArrayList<Integer>> psus = new HashMap<>();
        br.readLine(); // this will read the first line
        String line=null;
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

    public static HashMap<Integer,ArrayList<Integer>> getStateSpace() throws Exception{

        HashMap<Integer,String> itemsInMap = warehouseReader();
        ArrayList<Integer> orderlist = gettingTheOrder();
        HashMap<Integer,ArrayList<Integer>> psus = gettingPSUs();

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
        return relevantPSUs;

    }
    //gets the key of the Hashmap --> the PSU identifiers
    public static Integer[] getIdentifier()throws Exception{

        HashMap psuMap = getStateSpace();
        Set<Integer> keys = psuMap.keySet();
        Integer[] identifiers = keys.toArray(new Integer[keys.size()]);

        return identifiers;

    }

    public static boolean valid()throws Exception{
        ArrayList<Integer> orderlist = gettingTheOrder();
        HashMap<Integer,ArrayList<Integer>> reducedWH = getStateSpace();
        Integer[] psuIDs = getIdentifier();
        Set items = new HashSet();
        int psuNumber = psuIDs.length;
        boolean[] someState = new boolean[psuNumber];


       for(int i = 0; i < someState.length; i++ ){
       //creates a random number between 0 and 1, if its greater than 0.5 --> false
        //smaller than 0.5 --> true
          someState[i] = (Math.random() < 0.5);

        }
    //------------------------------------------------------------------------------------------------
    // DAS IST WICHTIG!! BITTE KOPIEREN!
    //------------------------------------------------------------------------------------------------

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
          System.out.println(items);
        //wenn Anzahl der relevanten items gleich dem der Anzahl in order dann ist state valid:

        if(items.size() == orderlist.size()){
            return true;
        }else{
            return false;
        }
    }


//------------------------------------------------------------------------------------------
/*
    /**
     * evaluation function that should be minimized: number of Psus used,
     * penalty if state is not valid (doesn't carry all items ordered): setting to max amount of psus on eval function
     * --> this way an invalid state will always be worse or equal, so we never choose an invalid state
     */
     // public static static int evaluate(Order o, boolean[] someState) throws Exception{
     //     int eval = countPsus(someState);
     //     if(!valid(o, someState)){
     //         eval = o.getPsuIDs().length;
     //     }
     //     return eval;
     // }

     /* valid() determines wether the state is valid
     * a state is valid if it covers all the items in order,
     * it is valid when the order and the set have the same length
     *
     * @ param   possibleState   HashSet with items of a specific state
     *           orderlist       contains all items
     * @ return   boolean         true == state is valid
     */
     //  public static static boolean valid(Order o, boolean[] someState)throws Exception{
     //     Set<Integer> itemList = backToItems(o, someState);
     //     ArrayList<Integer> orderlist = o.getOrderList();
     //     //wenn Anzahl der relevanten items gleich dem der Anzahl in order dann ist state valid:
     //     if(itemList.size() == orderlist.size()){
     //         return true;
     //     }else{
     //         return false;
     //     }
     // }
     /**
     * countPsus counts the number of PSUs used by a specific state
     * @ param   someState  state using PSUs for which values are true
     * @ return  psuCount   number of PSUs activated in a state (how many PSUS are currectly selected for order)
     */
     // public static static Integer countPsus(boolean[] someState)throws Exception{
     //     int psuCount = 0;
     //     //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
     //     for(int x = 0; x < someState.length; x++){
     //         if(someState[x] == true){
     //             psuCount++;
     //         }
     //     }
     //     return psuCount;
     // }

     /**
      * Neighbourhood: alle states, die von dem State erreicht werden können,
      * wenn jeweils der Wert von nur 1 Psu an oder ausgeschaltet wird
      * alle states werden in arraylist gespeichert     *
      */
     // public static static ArrayList<boolean[]> createNeighbours(boolean[] someState){
     //
     //     ArrayList<boolean[]> hood = new ArrayList<boolean[]>();
     //
     //     for(int n = 0; n < someState.length; n++){
     //         if(someState[n]==true){
     //             someState[n]=false;
     //         }else{
     //             someState[n]=true;
     //         }
     //
     //         boolean[] newState = Arrays.copyOf(someState, someState.length);
     //         hood.add(newState);
     //     }
     //
     //     return hood;
     // }


     /**
      * creates the random state representation for search algorithms
      * takes in the number of Psus in order to determine size of array
      */
     // public static static boolean [] createRandomState(Order o){
     //
     //     int psuNumber = o.getPsuIDs().length;
     //     boolean [] state = new boolean [psuNumber];
     //
     //     for(int i = 0; i < state.length; i++ ){
     //         //creates a random number between 0 and 1, if its greater than 0.5 --> false
     //         //smaller than 0.5 --> true
     //         state[i] = (Math.random() < 0.5);
     //
     //     }
     //     return(state);
     // }


 /**
     * backToItems takes state (selection of psus) as booleanArray and turns it into an set of items (their reference numbers) stored in the selected psus
     *
     * @ new param   items        list of items in relevant PSUs
     * @ return      itemList   a set of items in numbers
     */
     // public static static Set<Integer> backToItems(boolean[] someState)throws Exception{
     //
     //     HashMap<Integer,ArrayList<Integer>> reducedWH = o.getMap();
     //     Integer[] psuIDs = o.getPsuIDs();
     //     Set items = new HashSet();
     //
     //     //Für jede Position des booleanArray wird überprüft ob boolean gleich true ist
     //     for(int x = 0; x < someState.length; x++){
     //         if(someState[x] == true){
     //             items.add(reducedWH.get(psuIDs[x]));
     //         }
     //     }
     //
     //     return items;
     //
     // }


}
