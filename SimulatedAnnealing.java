import java.io.*;
import java.util.*;

public class SimulatedAnnealing{

 /**
  * fängt mit random state an, muss daher keinen current state gegeben bekommen
  * braucht aber die Anzahl der Psus (psuNumber)
  * Sollte vielleicht die temp. übergeben bekommen?
  * current State = boolean array!!
  */
  public boolean [] simulatedAnnealing(HashMap psuMap, int orderSize){

    //get amount of PSUs in order to generate random state
    Integer [] identifier = StateMethods.getIdentifier(psuMap);
    int psuNumber = identifier.length;

    boolean [] currentState = StateMethods.createRandomState(psuNumber);

    //temperature variable that controls search progress
    int temp = 100;

    boolean [] finalState = currentState;
    //create neighbours of current state
    ArrayList neighbours = new ArrayList<int[]>();
    neighbours = StateMethods.createNeighbours(currentState, psuNumber);
    //evaluate current state
    int stateValue = StateMethods.countPsus(currentState, orderSize, psuMap);
    //controls whether current value is already optimal
    if(stateValue == StateMethods.optimum()){
        return finalState;
    }

    for (int t = 1; t < temp; t++) {

      t = t - 5;
      //if temperature reaches 0 before we have found the optimal state, we return the current state as the best one
      if(t == 0){
        finalState = currentState;
        return finalState;
      }

      boolean [] newState = neighbours.getHead();
      //controls whether the neighbour list is empty
      if(newState == null){
        finalState = currentState;
        return finalState;
      }
      //evaluates next neighbour
      int newValue = StateMethods.countPsus(newState, orderSize, psuMap); //used to be evaluates
      //controls whether neighbour state is optimal
      if(newValue == StateMethods.optimum()){
        finalState = newState;
        return finalState;
      }
      //declare necessary variables for downstep
      int deltaValue = newValue - stateValue;
      Random downstep = new Random();
      double probability = downstep.nextDouble();
      //replaces current state with neighbour when evaluation value is better
      if(deltaValue > 0){
        stateValue = newValue;
        currentState = newState;
      }
      //if not, do it either way with a specific probability
      else if(probability <= Math.exp(deltaValue / t)){
        currentState = newState;
        stateValue = newValue;
      }
      //remove head of the neigbour list to get next neigbour
      neigbour.removeHead();

    }

    
  }
}
