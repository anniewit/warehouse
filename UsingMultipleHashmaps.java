import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class UsingMultipleHashmaps
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
        System.out.println(psus);



    //und das gehört zum try dazu..
    } catch (FileNotFoundException e){
        e.printStackTrace();
    }




  }
}
