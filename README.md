# warehouse
los gehts
lol

__________________________________________________________________________________________________________________________
Hashtable Using BufferedReader:

import java.io.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class ReadFromFile2 {

  public static void main(String[] args)throws Exception {
  
  //Hier wird das File eingelesen:
  File file = new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt"); 
  
  BufferedReader br = new BufferedReader(new FileReader(file));
  
  //Die erste Zeile des Files wird gelesen und geteilt:
  String[] st = br.readLine().split(" ");
  
  //Das ist der Hashtable für die erste Zeile des Files. Jedes Item bekommt eine eigene Nummer:
  Hashtable ht = new Hashtable();

    for(int z = 0; z <= st.length -1; z++){
      ht.put(z, st[z]);
    }
    System.out.println(ht);

  __________________________________________________________________________________________________________________________
  Scanner:
  
import java.io.*;
import java.util.*;

public class ReadFromFileUsingScanner
{
  public static void main(String[] args) throws Exception
  {
  
  //Hier wird das File eingelesen:
  File file =
      new File("C:\\Users\\Julia Stocker\\Javalernen\\AI Programming Project\\problem1.txt");
      
  //Das ist der Scanner:
  Scanner sc = new Scanner(file);
  
  //Array Liste: speichert alle Items UND PSUs Items!! durch Komma getrennt in eine Liste.. Problem: Mischmasch
  ArrayList<String> list = new ArrayList<>();
  while(sc.hasNext()){
      list.add(sc.next());
  }
  sc.close();
  System.out.println(list);
-------------------------------------------------------------------------------------------------------------------------
  Scanner mit while Schleife und Regexfunktion.. another hopeless try out. 
  
  while (sc.hasNextLine()){
      String fulltext = sc.nextLine();
      String parts[] = fulltext.split("\\r?\\n");
      for(String s:parts)
      System.out.println(s);
    }
  
  
