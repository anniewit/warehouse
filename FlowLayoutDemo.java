import java.awt.*;
import javax.swing.*;

public class FlowLayoutDemo {
    public final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                ComponentOrientation.RIGHT_TO_LEFT);
        }



        JButton runButton = new JButton("Run");
        //runButton.setSize(200,50); (funktioniert nicht)

        String [] algorithmChoices = {"Hill-Climbing", "First-Choice Hill-Climbing", "Random-Restart Hill-Climbing", "Local-Beam Search", "Simulated Annealing"};

        pane.setLayout(new FlowLayout());

        //fügt Labels (Schilder) und buttons hinzu
        pane.add(new JLabel("Warehouse Information:", JLabel.CENTER));
        pane.add(new JButton("Insert"), BorderLayout.NORTH);
        pane.add(new JLabel("Order:", JLabel.CENTER), BorderLayout.NORTH);
        pane.add(new JButton("Insert"), BorderLayout.NORTH);
        pane.add(new JLabel("Select Algorithm", JLabel.CENTER), BorderLayout.NORTH);
        pane.add(new JComboBox<String>(algorithmChoices), BorderLayout.NORTH);
        pane.add(runButton, BorderLayout.NORTH);

        // ausgabefeld
        JPanel ausgabe = new JPanel(); 
        ausgabe.add(new JSeparator());
        ausgabe.add(new JLabel("Solution:" ), BorderLayout.SOUTH);


        //ausgabefeld hinzufügen
        pane.add(ausgabe,BorderLayout.SOUTH);
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

       // JPanel eingabePanel = new JPanel();
      //  JPanel ausgabePanel = new JPanel();
      //  ausgabePanel.setPreferredSize(new Dimension(500,600));

        //frame.add(eingabePanel);
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //ausgabe panel, flow layout
       // frame.add(ausgabePanel);


        //Display the window.
        frame.pack();
        frame.setSize(1000,500);//400 width and 500 height
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
