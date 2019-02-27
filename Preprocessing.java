import java.io.*;
import java.util.*;
import java.util.Scanner;


public class Preprocessing {
    

    public static void main(String[] args) {
        String orderPath = ".\\order11.txt";
        String warehousePath = ".\\problem1.txt";
        HashMap map = generateHashMap(warehousePath, orderPath);

        System.out.println(Arrays.asList(map)); 

/*             // print for testing
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
               System.out.print(", ");
            }
            System.out.print(arr[i]);
        } */

    }

/*     public static void prepare(String warehousePath, String orderPath, String selectedAlg, int repetitions){
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



    public static String[] warehouseReader(String warehousePath)throws Exception{
        try {
            //BufferedReader ließt Warenhaus ein:
            File whFile = new File(warehousePath);
            BufferedReader br = new BufferedReader(new FileReader(whFile));

            //nur Items werden eingelesen
            String[] st = br.readLine().split(" ");

            return(st);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static HashSet orderReader(String orderPath) throws Exception{
        try{
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

            return(order);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static HashMap generateHashMap(String warehousePath, String orderPath){
        String [] warehouseInput = warehouseReader(warehousePath);
        HashSet order = orderReader(orderPath);

        //ArrayList für Keys of items
        ArrayList<Integer> orderlist = new ArrayList<Integer>();

        //Items werden zu Zahlen gemappt
        Hashtable<Integer,String> ht = new Hashtable<Integer,String>();
        //Items bekommen einen Identifier:
        for(int z = 0; z <= warehouseInput.length - 1; z++){
            ht.put(z, warehouseInput[z]);
        }

        //Items in HashSet werden mit items im Warenhaus verglichen
        //Keys der Items in dem Order werden in ArrayList gespeichert
        for(Map.Entry<Integer,String> m : ht.entrySet()){
            if(order.contains(m.getValue()))
                orderlist.add(m.getKey());
        }
        String line; //Eine Zeile in dem Warenhaus
        int id = 1000;//Einen identifier für die PSUs

        //Initalisierung der ArrayList<Integer>, darin werden die items gespeichert
        ArrayList<Integer> list = new ArrayList<Integer>();
        //Initialisierung einer neuen HashMap,die die identifieres als keys und die items in listen als values hat.
        HashMap<Integer,ArrayList<Integer>> psus = new HashMap<>();

        //Diese Schleife durchläuft das Warenhaus Zeile für Zeile
        while((line = br.readLine()) != null){
        //Dann wird auf die items in der HashMap durch Map.Entry zugegriffen
            for(Map.Entry<Integer,String> m : ht.entrySet()){
                //Falls item der Zeile in der HashMap ist wird dieses in ArrayList gespeichert
                if(line.contains(m.getValue())){
                    list.add(m.getKey());
                }
            //eine neue Liste wird angefertigt und in der HashMap gespeichert
            psus.put(id, new ArrayList<Integer>(list));
            }
        //die identifieres werden hochgezählt
        id++;
        //die liste wird gelöscht um neue Elemente zu speichern
        list.clear();
        }
        
        HashMap reducedPsus = selectRelevantPSUs(psus, orderlist);
        
        return reducedPsus;
    }

    public static HashMap selectRelevantPSUs(HashMap psus, ArrayList orderlist){
        
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
        return(relevantPSUs);
    }

}