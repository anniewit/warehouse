import java.io.*;
import java.util.*;

public class LocalBeam{
  

  /**
   *  selects k best neigbours of all current states (difference to parallel hill climbing)
   *  returns best state (that minimizes objective function)
   */
  public static boolean[] localBeamSearch(Order o) throws Exception {

    int numOfBeams = o.getRep();
    int stateSize = o.getSize();
    
    //creating List of current random states
    List<boolean[]> currStates = new ArrayList<boolean[]>(); // current states that were beaming on
    for(int i = 0; i < numOfBeams; i++){
        boolean[] newRandomState = Arrays.copyOf(StateMethods.createRandomState(o), stateSize);
        currStates.add(newRandomState);
    }

    int step = 0;
    while(step < Integer.MAX_VALUE){
        //create neighbourhood for all current random states
        ArrayList<boolean[]> allNeighbours = new ArrayList<boolean[]>();
        for(int i = 0; i < currStates.size(); i++){
            List neighbours = new ArrayList(StateMethods.createNeighbours(currStates.get(i)));
            allNeighbours.addAll(neighbours);
        }

        //get k best neighbours, save into extra list, remove them from allneighbours
        List<boolean[]> nextStates = new ArrayList<boolean[]>();
        for(int i = 0; i < numOfBeams; i++){
            boolean[] bestNeighbour = Arrays.copyOf(bestState(o, allNeighbours), stateSize) ;
            nextStates.add(bestNeighbour);
            allNeighbours.remove(bestNeighbour);
        }

        //if best neighbour is not better than best current state, return k current
        boolean[] bestCurr = bestState(o, currStates);
        boolean[] bestNeigh = nextStates.get(0); //0, because first entry in list is the smallest
        System.out.println("best N: " + StateMethods.evaluate(o, bestNeigh)+ " best C: " + StateMethods.evaluate(o, bestCurr)); //test: compare with current
        if(StateMethods.evaluate(o, bestCurr) < StateMethods.evaluate(o, bestNeigh)){
            return bestCurr;
        }

        //else List of nextstates becomes currstates
        Collections.copy(currStates, nextStates);
        step++;
    } 
    return currStates.get(0);
    
  }

  /**
   * takes in a neighbourhood and returns the state that minimizes the objective function 
   * if first state is better than all other states/neighbours
   * return first state
   * else take better state as current
   * @param allStates neighbourhood
   * @return state that minimizes objective function
   */
  public static boolean[] bestState(Order o, List<boolean[]> allStates) throws Exception{
    boolean [] currElement = allStates.get(0);
    int currValue = StateMethods.evaluate(o, currElement);
    boolean [] nextElement;
    int nextValue;

    //searches for best element in the complete neighbour list
    for(int pos = 1; pos < allStates.size(); pos ++){
      nextElement = allStates.get(pos);
      nextValue = StateMethods.evaluate(o, nextElement);
     
        if(nextValue < currValue){
            currElement = Arrays.copyOf(nextElement, nextElement.length);
            currValue = nextValue;
        }
    }
    return(currElement);

  }
}