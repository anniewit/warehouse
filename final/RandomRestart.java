import java.io.*;
import java.util.*;
import java.nio.*;

public class RandomRestart {
    /**
     * uses Random-Restart Hill climbing to search for an optimal state
    * @ param   n               saves the number of HillClimbing runtimes
    *           state_value     gets the value return of one HillClimbing runtime
    *           state_values    saves all these values in an array
    * @ return  currentState    selection of PSUs to transport order
    */ 
    public static boolean [] randomRestart(Order o) throws Exception, NullPointerException{
        
        int n = o.getRep();
        int[] state_values = new int[n];
        boolean[] bestState = new boolean[n];

        // n mal hill climbing durchführen und davon den state mit dem geringsten value zurückgeben
        for(int i = 0; i < n; i++){
            boolean[] currentState = MyHillClimbing.hillClimbing(o);
            state_values[i] = StateMethods.countPsus(currentState);
            // check whether next hillClimbing value is better, if yes save at position 0
            if(state_values[i] > state_values[0]){
                state_values[0] = state_values[i];
                System.arraycopy(currentState,0,bestState,0,currentState.length);
            }

        }
        return bestState;
        // highestValue = state_values[0];

    }

}
