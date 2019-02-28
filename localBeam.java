public int[] localBeam(List<int[]> current, int numOfBeams){


  	List<int[]> currentNeighbours = new ArrayList<int[]>();
  	List<int[]> allNeighbours = new ArrayList<int[]>();

  	int[] currentElement;

  	for(int iteration = 0; iteration < 100; iteration ++){


  		for(int position = 0; position < current.size(); position ++){
  			currentElement = current.get(position);
  			currentNeighbours = stateMethods.createNeighbours(currentElement,false);
  			allNeighbours.addAll(currentNeighbours);
  		}

      if(allNeighbours.isEmpty()){
        return theBestNeighbour(current);
      }


      //determine the numOfBeams best neighbours out of our complete neighbourhood
  		int[] bestElement;
  		int bestValue;
  		List<int[]> bestNeighbours = new ArrayList<int[]>();
  		int[] nextElement;
  		int nextValue;
      int positionToRemove;

  		for(int position = 0; position < numOfBeams; position ++){
        if(allNeighbours.isEmpty()){
          return theBestNeighbour(current);
        }
  			bestElement = allNeighbours.get(0);
  			bestValue = stateMethods.evaluate(bestElement);
        positionToRemove = 0;


  			for(int pos = 0; pos < allNeighbours.size(); pos ++){
  				nextElement = allNeighbours.get(pos);
          nextValue = stateMethods.evaluate(nextElement);

          if(nextValue > bestValue){
            bestElement = nextElement;
            bestValue = nextValue;
            positionToRemove = pos;
          }
  			}


        bestNeighbours.add(bestElement);
        allNeighbours.remove(positionToRemove);

        if(allNeighbours.isEmpty()){
          break;
        }

  		}

      allNeighbours.clear();

      //compare the overall value of our current list and the bestNeighbours list
      int allCurrentValues = 0;
      int allBestNeighbourValues = 0;
      int[] element;
      int elementValue;

      for(int position = 0; position < current.size(); position ++){
        element = current.get(position);
        elementValue = stateMethods.evaluate(element);
        allCurrentValues = allCurrentValues + elementValue;
      }


      for(int position = 0; position < bestNeighbours.size(); position ++){
        element = bestNeighbours.get(position);
        elementValue = stateMethods.evaluate(element);
        allBestNeighbourValues = allBestNeighbourValues + elementValue;
      }


      if(allBestNeighbourValues > allCurrentValues){
        current = bestNeighbours;
      }

      else{
        return theBestNeighbour(current);
      }

      bestNeighbours.clear();
  	}
    return theBestNeighbour(current);
  }
