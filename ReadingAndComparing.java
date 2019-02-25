import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class ReadingAndComparing
{
  public static void main(String[] args) throws Exception
  {

    //TRY: fragt mich nicht warum man das braucht, ich hab keine Ahnung..
    try {
        //BufferedReader ließt Warenhaus ein:
        File file = new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        //nur Items werden eingelesen
        String[] st = br.readLine().split(" ");

        //Items werden zu Zahlen gemappt
        Hashtable<Integer,String> ht = new Hashtable<Integer,String>();
        //Items bekommen einen Identifier:
        for(int z = 0; z <= st.length - 1; z++){
            ht.put(z, st[z]);
        }
        //------------------------------------------------------------------------------------------------------
        //Order wird durch Scanner eingelesen:
        File file2 =
        new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\order12.txt");
        Scanner sc = new Scanner(file2);

        //ArrayList für Keys of items
        ArrayList<Integer> orderlist = new ArrayList<Integer>();
        //HashSet für eingelesene order
        Set<String> order = new HashSet<>();

        //Eingelesene items werden dem HashSet zugefügt
        while(sc.hasNext()){
            order.add(sc.next().trim());
        }
        //System.out.println(order);
        //--------------------------------------------------------------------------------------------------------
        //Items in HashSet werden mit items im Warenhaus verglichen
        //Keys der Items in dem Order werden in ArrayList gespeichert
        for(Map.Entry<Integer,String> m : ht.entrySet()){
            if(order.contains(m.getValue()))
                orderlist.add(m.getKey());
        }
        /*
        //ArrayList wird ausgegeben
        Iterator itr = orderlist.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        */
        //----------------------------------------------------------------------------------------------------------

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

        //die HashMap wird ausgegeben:
        //System.out.println(psus);

    //Neue HashMap für relevante PSUs erstellen:
    HashMap<Integer,ArrayList<Integer>> warehouse = new HashMap<>();

    //Order wird mit den PSU-items vergliechen und in Hashmap gespeichert:
    for(Map.Entry<Integer,ArrayList<Integer>> entry: psus.entrySet()) {
        ArrayList<Integer> items = entry.getValue();
        items.retainAll(orderlist);
        //PSUs werden gelöscht, die keine items haben.
        if(!items.isEmpty()){
            warehouse.put(entry.getKey(), items);
        }
    }
    //HashMap wird ausgegeben
    //System.out.println(warehouse);

    //------------------------------------------------------------------------------------
    //Identifiers der PSUs werden in ein Array (identifieres) gespeichert
    Set<Integer> keys = warehouse.keySet();
    Integer[] identifieres = keys.toArray(new Integer[keys.size()]);
    //für die Ausgabe:
    //for(int i = 0; i <= identifieres.length - 1; i++){
    //    System.out.println(identifieres[i]);
    //}

    //---------------------------------------------------------------------------------------
    //Ein booleanArray wird mit Länge Anzahl der PSUs erstellt
    boolean[] randomStartingState = new boolean[identifieres.length];
    //Dieser wird mit random boolean values gefüllt:
    Random random = new Random();
    for(int i = 0; i <= identifieres.length - 1; i++){
        randomStartingState[i] = random.nextBoolean();
        //booleanArray wird ausgegeben:
        System.out.println(randomStartingState[i]);
    }




    //und das gehört zum try dazu..
    } catch (FileNotFoundException e){
        e.printStackTrace();
    }




  }
}
