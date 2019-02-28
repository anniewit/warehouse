import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import sun.print.resources.serviceui_de;

import java.io.*;
import java.util.*;



public class Interface {

    // create the elements of the Layout as "global", so that the Buttons can have access to them
    static JLabel warehouseLabel = new JLabel();
    static JLabel orderLabel = new JLabel();
    static String [] algorithmChoices = {"Hill-Climbing", "First-Choice Hill-Climbing", "Random-Restart Hill-Climbing", "Local-Beam Search", "Simulated Annealing"};
    static JComboBox cBox = new JComboBox<String>(algorithmChoices);

    //Ausrichtung: nicht so wichtig
    public final static boolean RIGHT_TO_LEFT = false;
    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                ComponentOrientation.RIGHT_TO_LEFT);
        }

        // function for run-button: calls method and hands over files and which algorithm
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e) {
                //collect necessary info for starting search (both selected files and the selected algorithm)
                String selectedAlg = (String)cBox.getSelectedItem();
                String warehousePath = warehouseLabel.getText();
                String orderPath = orderLabel.getText();
                int repetition = -1;

                //opens pop-up to specify the repetitions for Local beam search and RR Hill-climbing
                if (selectedAlg == "Local-Beam Search" || selectedAlg == "Random-Restart Hill-Climbing"){
                    // opens pop up, gets input from user
                    String input = JOptionPane.showInputDialog("Please specify the number of times the search should run: ");
                    
                    // if input is not an int, show error message
                    try{
                        repetition = Integer.parseInt(input);
                        
                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Please only enter an integer greater than 1.");
                    } 
                    if (repetition < 1){
                        JOptionPane.showMessageDialog(null, "Please enter a number greater than 1.");
                    }
                    
                }

                //check if both files were inserted
                if (warehousePath == "" || orderPath == ""){
                    JOptionPane.showMessageDialog(null, "Please enter an order and warehouse file.");
                } else{

                    Order o = new Order(warehousePath, orderPath, selectedAlg, repetition);
                    PreprocessingUpdate.prepare(o);

                }
 
/*                 //for Testing:
                System.out.println(selectedAlg);
                System.out.println(warehousePath);
                System.out.println(orderPath); 
                System.out.println(repetition);
 */

            }
        });

        //makes a pop-up window to select a file
        JFileChooser chooser = new JFileChooser();

        // Action for the warehouse button:  opens dialog to select a file, print path
        JButton warehouseSelector = new JButton("Select");
        warehouseSelector.addActionListener(new ActionListener(){
        
           public void actionPerformed(ActionEvent e) {
                chooser.showDialog(pane, "Select as Warehouse File");

                int retVal = chooser.showOpenDialog(pane);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    File selectedfile = chooser.getSelectedFile();
                    String path = selectedfile.getAbsolutePath();
                    warehouseLabel.setText(path);

                }
           }
        }
       );
        
        // Action for the order button: opens dialog to select a file, print path
        JButton orderSelector = new JButton("Select");
        orderSelector.addActionListener(new ActionListener(){
       
           public void actionPerformed(ActionEvent e) {
               chooser.showDialog(pane, "Select as Order File");

               int retVal = chooser.showOpenDialog(pane);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    File selectedfile = chooser.getSelectedFile();
                    String path = selectedfile.getAbsolutePath();
                    orderLabel.setText(path);
                }
           }

       });

       //specified parameter input for Local Beam Search and Random-Restart Hill-Climbing
       //JTextField nrInput = new JTextField(20);
      // nrInput.setEditable(true);

       //create Layout and Elements
        pane.setLayout(new FlowLayout());
            
        //fügt Labels (Schilder) und buttons hinzu
        pane.add(new JLabel("Warehouse Information:", JLabel.CENTER));
        pane.add(warehouseSelector); //Button
        pane.add(new JLabel("Order:", JLabel.CENTER));
        pane.add(orderSelector); //Button
        pane.add(new JLabel("Select Algorithm", JLabel.CENTER));
        pane.add(cBox);
        pane.add(runButton);
        pane.add(new JLabel("Solution: "));

        //print out solution: muss noch angepasst werden
        JLabel ausgabe = new JLabel("...");
        ausgabe.setPreferredSize(new Dimension(900, 600)); //width and height
        ausgabe.setBorder(new LineBorder(Color.BLACK));
        ausgabe.setBackground(Color.WHITE);
        ausgabe.setOpaque(true);
        pane.add(ausgabe);

        pane.add(warehouseLabel);
        pane.add(orderLabel);
    } 
/*     public static showSolution(HashMap){

    } */

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Warehouse Planning");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
            
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setSize(1000,700);//width and height
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    
    }
}

