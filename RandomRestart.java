import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class RandomRestart {

    public boolean [] randomRestart(Order o) throws Exception{
        /**
        * @ param   n               saves the number of HillClimbing runtimes
        *           state_value     gets the value return of one HillClimbing runtime
        *           state_values    saves all these values in an array
        */
        int n = Order.getRep();
        int[] state_values = new int[n];

        boolean[] currentState = HillClimbing.bestState(o, currentState);
        int state_value = StateMethods.countPsus(currentState);
        // at position zero state value of one hillClimbing search is saved.
        state_values[0] = state_value;

        for(int i = 1; i <= n; i++){
            boolean[] someNeighbour = HillClimbing.hillclimbing(o);
            state_values[i] = StateMethods.countPsus(someNeighbour);
            // check whether next hillClimbing value is better, if yes save at position 0
            if(state_values[i] > state_values[0]){
                state_values[0] = state_values[i];
                System.arraycopy(someNeighbour,0,currentState,0,currentState.length);
            }

        }
        return currentState;
        // highestValue = state_values[0];





    }

}
