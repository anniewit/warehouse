import java.io.*;
import java.util.*;


public class UsingHashtableUpdate
{
    public static void main(String[] args)throws Exception
    {

        //Items des Warenhauses werden durch BufferedReader eingelesen
        File file = new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String[] st = br.readLine().split(" ");

/*
  ArrayList<String> list = new ArrayList<String>();
  list.add("glamorous-webcam");
  list.add("glamorous-camera-battery");
  list.add("glamorous-laptop-battery");
  list.add("glamorous-phone-battery");
  Iterator itr = list.iterator();
  while(itr.hasNext()){
      System.out.println(itr.next());
  }

*/
        //Items werden zu Zahlen gemappt
        Hashtable<Integer,String> ht = new Hashtable<Integer,String>();

        for(int z = 0; z <= st.length - 1; z++){
            ht.put(z, st[z]);
        }
        //System.out.println(ht);

//------------------------------------------------------------------------------

        //Order wird durch Scanner eingelesen:
        File file2 =
        new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\order12.txt");
        Scanner sc = new Scanner(file2);

        //ArrayList für Keys of items
        ArrayList<Integer> list = new ArrayList<Integer>();
        //HashSet für eingelesene order
        Set<String> order = new HashSet<>();

        //Eingelesene items werden dem HashSet zugefügt
        while(sc.hasNext()){
            order.add(sc.next().trim());
        }
        //System.out.println(order);

        //Items in HashSet werden mit items im Warenhaus verglichen
        //Keys der Items in dem Order werden in ArrayList gespeichert
        for(Map.Entry<Integer,String> m : ht.entrySet()){
            if(order.contains(m.getValue()))
                list.add(m.getKey());
        }
        //ArrayList wird ausgegeben
        Iterator itr = list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
