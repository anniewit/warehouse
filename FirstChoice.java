import java.io.*;
import java.util.*;

/**
* This implements First-choice HillClimbing
* First-choice picks the first neighbour that improves the objective funtion.
*/
public class FirstChoice {

    public static boolean[] firstChoice(Order o) throws Exception{
        
        //random initialization
        boolean[] currentState = StateMethods.createRandomState(o);
        //create neighborhood of current state
          ArrayList<boolean[]> neighbours = new ArrayList<boolean[]> (StateMethods.createNeighbours(currentState)); //.clone();/
          System.out.println("1st Choice: "); 

        System.out.println(StateMethods.evaluate(o, currentState));
        System.out.println(Arrays.toString(currentState));

        //backup if other states are all worse --> works as "else"-statement/ dummy
        boolean[] altState = currentState;

        
        //2. for schleife, falls in neighbourhood kein passender State?
        for(int j = 0; j < currentState.length; j++){
            for(int i = 0; i < currentState.length; i++){
                boolean[] currNeighbour = neighbours.get(i); 
                    
                //change if current state is not valid or if neighbor is better --> has less psus and is valid      
                if(StateMethods.evaluate(o, currNeighbour) < StateMethods.evaluate(o, currentState)){
                    return currNeighbour;
                }
                currentState = currNeighbour;
            }
        }
        System.out.println(StateMethods.evaluate(o, currentState));
        System.out.println(Arrays.toString(currentState));
        return altState; //if no other state is better than the random state, return random state



}
}