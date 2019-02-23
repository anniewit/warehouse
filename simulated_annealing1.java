public int[] simulatedAnnealing(int[] currentState){
  //temperature variable that controls search progress
  int t = 100;

  finalState = currentState;
  //create neighbours of current state
  ArrayList int[] neigbours = stateMethods.createNeighbours(currentState);
  //evaluate current state
  int stateValue = stateMethods.evaluate(currentState);
  //controls whether current value is already optimal
  if(stateValue == stateMethods.optimum()){
      return finalState;
  }

  for (int t = 1; t < 100; t++) {

    t = t - 5;
    //if temperature reaches 0 before we have found the optimal state, we return the current state as the best one
    if(t == 0){
      finalState = currentState;
      return finalState;
    }

    int[] newState = neigbours.getHead();
    //controls whether the neighbour list is empty
    if(newState == null){
      finalState = currentState;
      return finalState;
    }
    //evaluates next neighbour
    int newValue = stateMethods.evaluate(newState);
    //controls whether neighbour state is optimal
    if(newValue == stateMethods.optimum()){
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
