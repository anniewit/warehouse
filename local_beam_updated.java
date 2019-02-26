//selects k best neigbours of all current states(difference to parallel hill climbing)

public List<int[]> local_beam(int numOfBeams){
    //creating List of current random states
    int[] initState = stateMethods.generateInitialState();
    List<int[]> current = new ArrayList<>();
  	current.add(initState);

  	for(int randomState = 1; randomState < numOfBeams; randomState ++){

      int[] newRandomState = stateMethods.generateRandom();
      current.add(newRandomState);
  	}

  	List<int[]> currentNeighbours = new ArrayList<>();
  	List<int[]> neighbours = new ArrayList<>();
  	neighbours = stateMethods.createNeighbours(initState);

  	int[] currentElement;
    //we do this search 100 times at the most
  	for(int iteration = 0; iteration < 100; iteration ++){
      //creating the list of all neighbours
      for(int position = 0; position < numOfBeams; position ++){
  			currentElement = current.get(position);
  			currentNeighbours = stateMethods.createNeighbours(currentElement);
  			neighbours.addAll(currentNeighbours);
  		}

      //searching for the best neighbour in our current neigbour list
  		int[] bestElement;
  		int bestValue;
  		List<int[]> bestNeighbours = new ArrayList<>();
  		int[] nextElement;
  		int nextValue;
      int positionToRemove;
      //fills our best k neighbours and guarantees that it is no longer than k elements
  		for(int position = 0; position < numOfBeams; position ++){
  			bestElement = neighbours.get(0);
  			bestValue = stateMethods.evaluate(bestElement);
        positionToRemove = 0;

        //searches for best element in the complete neighbour list, gives it back to line 37 and deletes it after that
  			for(int pos = 0; pos < neighbours.size(); pos ++){
  				nextElement = neighbours.get(pos);
          nextValue = stateMethods.evaluate(nextElement);

          if(nextValue > bestValue){
            bestElement = nextElement;
            bestValue = nextValue;
            positionToRemove = pos;
          }
  			}
        //creating our List of best neighbours, which is numOfBeams long and delete the values in this List from our List of all neighbours
        bestNeighbours.add(bestElement);
        neighbours.remove(positionToRemove);

  		}
      //computing the sum of all current values
      int allCurrentValues = 0;
      int allBestNeighbourValues = 0;
      int[] element;
      int elementValue;

      for(int position = 1; position < current.size(); position ++){
        element = current.get(position - 1);
        elementValue = stateMethods.evaluate(element);
        allCurrentValues = allCurrentValues + elementValue;
      }

      //computing the sum of all best neighbour values
      for(int position = 1; position < bestNeighbours.size(); position ++){
        element = bestNeighbours.get(position - 1);
        elementValue = stateMethods.evaluate(element);
        allBestNeighbourValues = allBestNeighbourValues + elementValue;
      }

      //if the best neighbour sum is better than our current sum, we use the best neighbour list as the new current list
      if(allBestNeighbourValues > allCurrentValues){
        current = bestNeighbours;
      }

      //if the best neighbour sum does not improve our current sum, we return the current list as the best list we have
      else{
        return current;
      }

  	}
    return current;
  }
