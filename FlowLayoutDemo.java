import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.util.*;



public class FlowLayoutDemo {

    // create the elements of the Layout as "global", so that the Buttons can have access to them
    static JLabel warehouseLabel = new JLabel();
    static JLabel orderLabel = new JLabel();
    static String [] algorithmChoices = {"Hill-Climbing", "First-Choice Hill-Climbing", "Random-Restart Hill-Climbing", "Local-Beam Search", "Simulated Annealing"};
    static JComboBox cBox = new JComboBox<String>(algorithmChoices);


    public final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener(){
        
            //@Override
            public void actionPerformed(ActionEvent e) {

                String selectedAlg = (String)cBox.getSelectedItem();
                String warehouseFile = warehouseLabel.getText();
                String orderFile = orderLabel.getText();
                orderLabel.setText(selectedAlg);
/* 
                //for Testing:
                System.out.println(selectedAlg);
                System.out.println(warehouseFile);
                System.out.println(orderFile); */

                //for next steps
                prepareSearch(warehouseFile, orderFile, selectedAlg);
            }
        });


        JFileChooser chooser = new JFileChooser();

        // Action for the warehouse button:  opens dialog to select a file, print path
        JButton warehouseSelector = new JButton("Select: ");
        //JLabel warehouseLabel = new JLabel();
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
        // JLabel orderLabel = new JLabel();
        JButton orderSelector = new JButton("Select: ");
        orderSelector.addActionListener(new ActionListener(){
       
           //@Override
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


       //create Layout and Elements
        pane.setLayout(new FlowLayout());
            
        //fügt Labels (Schilder) und buttons hinzu
        pane.add(new JLabel("Warehouse Information:", JLabel.CENTER));
        pane.add(warehouseSelector); //Button
        
        pane.add(new JLabel("Order:", JLabel.CENTER));
        pane.add(orderSelector);
        pane.add(new JLabel("Select Algorithm", JLabel.CENTER));
        pane.add(cBox);
        pane.add(runButton);
        
       pane.add(new JLabel("Solution: "));

       JLabel ausgabe = new JLabel("...");
       ausgabe.setPreferredSize(new Dimension(600, 600));
       ausgabe.setBorder(new LineBorder(Color.BLACK));
       ausgabe.setBackground(Color.WHITE);
       ausgabe.setOpaque(true);
       pane.add(ausgabe);

       pane.add(warehouseLabel);
       pane.add(orderLabel);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("FlowLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
    
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
       // frame.add(ausgabePanel);


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

