import java.awt.*;
import javax.swing.*;

public class GridLayoutDemo {
    public final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(
                ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton runButton = new JButton("Run");
        runButton.setSize(200,50);

        String [] algorithmChoices = {"Hill-Climbing", "First-Choice Hill-Climbing", "Random-Restart Hill-Climbing", "Local-Beam Search", "Simulated Annealing"};

        pane.setLayout(new GridLayout(0,2,50,25));

        pane.add(new JLabel("Warehouse Information:", JLabel.CENTER));
        pane.add(new JButton("Insert"));
        pane.add(new JLabel("Order:", JLabel.CENTER));
        pane.add(new JButton("Insert"));
        pane.add(new JLabel("Select Algorithm", JLabel.CENTER));
        pane.add(new JComboBox<String>(algorithmChoices));
        pane.add(runButton);
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
        JFrame frame = new JFrame("GridLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

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
