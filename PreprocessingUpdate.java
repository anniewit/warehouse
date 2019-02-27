import java.io.*;
import java.util.*;
import java.util.Scanner;


public class PreprocessingUpdate {

    private static String orderPath = ".\\order11.txt";
    private static String warehousePath = ".\\problem1.txt";


/*    public static void main(String[] args) {
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

    public static void prepare(String warehousePath, String orderPath, String selectedAlg, int repetitions){
        HashMap reduced = generateHashMap(warehousePath, orderPath);
        HashSet orderParsed = orderReader(orderPath); //could also be int, maybe better
        int orderSize = orderParsed.size();
        switch (selectedAlg){
            case "Hill-Climbing": TryingHillClimbing.hillClimbing(reduced, orderSize);
                break;
            case "First-Choice Hill-Climbing": //firstChoiceHillClimbing(reduced, orderSize);
                break;
            case "Random-Restart Hill-Climbing": //randomRestart(reduced, orderSize, repetitions);
                break;
            case "Simulated Annealing": SimulatedAnnealing.simulatedAnnealing(reduced, orderSize);
                break;
            case "Local-Beam Search": LocalBeam.local_beam(reduced, orderSize, repetitions);
                break;
        }
    } */

    public BufferedReader read(File file) throws FileNotFoundException {
        //BufferedReader ließt Warenhaus ein:
        //File file = new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br;
    }

    private HashMap<Integer,String> warehouseReader()throws Exception, FileNotFoundException{
            //BufferedReader ließt Warenhaus ein:
            //warehousePath = ".\\problem1.txt";
            File whFile = new File(warehousePath);
            BufferedReader br = this.read(whFile);
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

    private HashSet<String> orderReader() throws Exception, FileNotFoundException{
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


    private ArrayList<Integer> gettingTheOrder() throws Exception{
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
        return orderlist;
    }


    public HashMap<Integer,ArrayList<Integer>> gettingPSUs() throws Exception, FileNotFoundException{
        String line; //Eine Zeile in dem Warenhaus
        int id = 1000;//Einen identifier für die PSUs
        File whFile = new File(warehousePath);
        BufferedReader br = read(whFile);
        HashMap<Integer, String> itemsInMap = warehouseReader();
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

    public static void main(String[] args) throws Exception{

        Preprocessing access = new Preprocessing();

        HashMap<Integer,String> itemsInMap = access.warehouseReader();
        ArrayList<Integer> orderlist = access.gettingTheOrder();
        HashMap<Integer,ArrayList<Integer>> psus = access.gettingPSUs();

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

        System.out.println(relevantPSUs);


    }

}
