import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.util.*;



public class Interface {

    // create the elements of the Layout as "global", so that the Buttons can have access to them
    static JLabel WAREHOUSE_LABEL = new JLabel();
    static JLabel orderLabel = new JLabel();
    static String [] algorithmChoices = {"Hill-Climbing", "First-Choice Hill-Climbing", "Random-Restart Hill-Climbing", "Local-Beam Search", "Simulated Annealing"};
    static JComboBox cBox = new JComboBox<String>(algorithmChoices);
    static JLabel ausgabe = new JLabel();

    //Ausrichtung: nicht so wichtig
    public final static boolean RIGHT_TO_LEFT = false;
    public static void addComponentsToPane(Container pane) throws IOException { 
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
                String warehousePath = WAREHOUSE_LABEL.getText();
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

                    try{
                        Preprocessing.prepare(o);
                    }catch (IOException ioex){

                    }catch (Exception ex ){

                    }
                    
                    printSolution(o);
                }
            }
        });

        //makes a pop-up window to select a file
        JFileChooser chooser = new JFileChooser();

        // Action for the warehouse button:  opens dialog to select a file, print path
        JButton warehouseSelector = new JButton("Select");
        warehouseSelector.addActionListener(new ActionListener(){
        
           public void actionPerformed(ActionEvent e) {

                int retVal = chooser.showOpenDialog(pane);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    File selectedfile = chooser.getSelectedFile();
                    String path = selectedfile.getAbsolutePath();
                    WAREHOUSE_LABEL.setText(path);

                }
           }
        }
        );
        
        // Action for the order button: opens dialog to select a file, print path
        JButton orderSelector = new JButton("Select");
        orderSelector.addActionListener(new ActionListener(){
       
           public void actionPerformed(ActionEvent e) {

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
        pane.add(orderSelector); //Button
        pane.add(new JLabel("Select Algorithm", JLabel.CENTER));
        pane.add(cBox);
        pane.add(runButton);
        pane.add(new JLabel("Solution: "));

        ausgabe.setPreferredSize(new Dimension(900, 1500)); //width and height
        ausgabe.setBorder(new LineBorder(Color.BLACK));
        ausgabe.setBackground(Color.WHITE);
        ausgabe.setOpaque(true);
        ausgabe.setVerticalAlignment(SwingConstants.TOP);
        //scrollen
        JScrollPane scroller = new JScrollPane(ausgabe, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(new Dimension(900,900));
        pane.add(scroller);

        pane.add(WAREHOUSE_LABEL);
        pane.add(orderLabel);
    } 

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() throws IOException {
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
        frame.setSize(1000,1000);//width and height
        frame.setVisible(true);
    }
    /**
     * prints the solution to the screen of the GUI into ausgabe label
     * shows amount of Psus used and which items are stored in each PSU
     */
    public static void printSolution(Order o){ 
        HashMap<Integer,String[]> map = o.getSolution();
        Set psu = map.keySet();
        int psuNr = psu.size();
        String solution = "";
        for(Map.Entry<Integer,String[]> entry: map.entrySet()) {
            solution += entry.getKey() + " : " + Arrays.toString(entry.getValue());
            solution += System.lineSeparator() + "<br>";
        }

        ausgabe.setText("<html> Amount of PSUs used: " + psuNr + "<br>" + solution + "</html>");
    }
        
    public static void main(String[] args) throws IOException{
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()  {
                try{
                    createAndShowGUI();
                }catch (IOException e){

                }
                
            }
        });
    
    }
}

