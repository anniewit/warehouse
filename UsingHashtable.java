import java.io.*;
import java.util.*;


public class UsingHashtable
{
    public static void main(String[] args)throws Exception
    {
        //File wird eingelesen via BufferedReader
        File file = new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        //Dies ist eine Übungsorder: ArrayList mit den ersten vier items im Warenhaus
        String[] st = br.readLine().split(" ");
        ArrayList<String> list = new ArrayList<String>();
        list.add("glamorous-webcam");
        list.add("glamorous-camera-battery");
        list.add("glamorous-laptop-battery");
        list.add("glamorous-phone-battery");
        //Hier wird die List ausgegeben:
        Iterator itr = list.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }


        //Erstellung des Hashtables mit <Keys, Values>
        Hashtable<Integer,String> ht = new Hashtable<Integer,String>();
        // Key = Position der Items im Warenhaus:
        for(int z = 0; z <= st.length -1; z++){
            ht.put(z, st[z]);
        }
        //Hashtable wird ausgegeben
        System.out.println(ht);

        // Keys von relevanten Items (die in der Order) werden ausgegeben
        for(Map.Entry<Integer,String> m : ht.entrySet()){
            if(list.contains(m.getValue()))
                System.out.println(m.getKey());
        }


    }
}
