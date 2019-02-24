import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class UsingArrayList
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
        //jetzt kommt etwas etwas kompleziertes, das man ganz sicher viel einfacherer machen kann..

        //Anzahl der Zeilen in dem Warenhausfile werden gezählt:
        /*Erstmal wird wieder File eingelesen, dann gezählt über Files.lines().count()
        **Gezählt wird in long, das muss in ein int umgewandelt werden -> lineCount Variable.
        */
        Path path = Paths.get("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt");
        long numbers = Files.lines(path).count();
        int lineCount = (int) numbers;

        /*Durch die Anzahl der Zeilen, kann man einen Array erstellen (String[] parts)
        **Dafür muss jede Zeile (String line) dem Array übergeben werden -> parts[i] = line;
        **Nun wird eine zwei dimensionale ArrayList erstellt um die PSUs zu speichern.
        **Dies sind zwei verschachtelte Listen mit einem outer und einem inner index.
        **um etwas der ArrayList hinzuzufügen benutzt man: ArrayList.get(outerIndex).add(new Integer(0));
        */
        String line;
        int i = 0;
        String[] parts = new String[lineCount + 1];

        int outerIndex = 0;
        int innerIndex = 0;
        //Initalisierung der ArrayList von ArrayList<Integer>:
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        //Initialisierung der ArrayList von Integer:
        list.add(new ArrayList<Integer>());

        /*Diese Schleife durchläuft das Warenhaus Zeile für Zeile und übergibt
        **die Information an den StringArray
        */
        while((line = br.readLine()) != null){
        parts[i] = line;
        //Dann wird auf die items in der HashMap durch Map.Entry zugegriffen
            for(Map.Entry<Integer,String> m : ht.entrySet()){
                //Falls item der Zeile in der HashMap ist wird dieses in ArrayList gespeichert
                if(parts[i].contains(m.getValue())){
                    list.get(outerIndex).add(innerIndex, m.getKey());
                //wenn nicht wird an der Stelle eine Null platziert.
                }else{
                    list.get(outerIndex).add(innerIndex, 0);

                }
            //Dies geschieht solange wie items in der HashMap sind,
            //dabei wird Position des innerIndex hochgezählt [[][][]...]
            innerIndex++;
            }
        //Danach geht's in die nächste Zeile des Warenhauses
        i++;
        //innerIndex fängt wieder bei Null an
        innerIndex = 0;
        //Dafür wird aber der outerIndex hochgezählt [[]...][[]..]...
        outerIndex++;
        //Um den outerIndex hochzuzählen muss man ein neues ArrayList in der ArrayList erstellen.
        list.add(new ArrayList<Integer>());

        }

        //Hier wird das Ganze ausgegeben
        Iterator itr = list.iterator();
        while(itr.hasNext()){
            /* mein Versuch die Nullen zu löschen:
            if(itr.next() == 0){
                itr.remove();
            }
            */
            System.out.println(itr.next());

        }
    //und das gehört zum try dazu..
    } catch (FileNotFoundException e){
        e.printStackTrace();
    }




  }
}
