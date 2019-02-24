// hill climbing
// muss übergeben bekommen: int array mit order (order[]), 2D Array mit PSUs und ites in psu,
//hill climbing typ als int(default = 0, first choice = -1, restart = [1,unendlich) )


public class hillClimbing {

    public static void main(String[] args) {

      //1. initializations
      //1.1 value of current state --> start with worst case
      int c_value = number_PSUs;
      //1.2 represenation of current state (all booleans default to value "false")
      boolean[] current_state = new boolean[number_PSUs]; //!! number of PSUs has to be input
      //1.3  value of neighbour state
      int n_value = 0;
      //1.4 marker to remember the best neighbour --> if current state is best state,
      //    then the marker has an invalid value
      int marker = current_state.length;
      //1.5 marker to mark the value of best neighbour
      int m_value = 0;
      //1.6 represenation of neighbour state (all booleans default to value "false")
      boolean[] neighbour_state = new boolean[number_PSUs];
      //1.7 one way flag which tells us when we are done
      boolean continue = true;


      wile(continue){
        //2. go through neighbours and remember the one with the best value
        //2.1 go through neighbours and check if valid (= all states which can be reached by changing one value of array)
        for(int n = 0; n < neighbour_state.length; n++){
          //2.2 change one value of state
          neighbour_state[n] != current_state[n];
          //2.3 check if neighbour is valid
          if(check_validity(neighbour_state[])){
          //2.4 if neighbour valid --> calculate value of neighbour
            for(int v = 0; v < neighbour_state.length; v++){
              //2.4.1 objective function = number of used PSUs = sum of values of array of state
                if(current_state[v] == true){
                n_value++;
              }
            }
            //2.5 check if this neighbor has a better value than the current state and all previos neighbours
            if(n_value < c_value && n_value < m_value){
              //2.5.2 if value of neighbour is lower (less PSUs used) -->remember this neighbour
              marker = n;
              m_value = n_value;
            }
          //2.6 revert change and try next neighbour
          neighbour_state[n] = current_state[n];
        }
      }
      //3. if the best neighbor has a better value than the current states
      //3.1 our new state is the neighbor with highest value
      if(n_value < c_value){
        c_value = n_ value;
        for(int b = 0; b < neighbour_state.length; b++){
          current_state[b] = neighbour_state[b];
          }
        //3.2 else we have reached lokal optimum and can return state and value
        }else{
          return(current_state[], c_value);
          continue = false;
        }
      }
    }
  }





/*        do
      neighbor ← neighbor of current with highest value if value(neighbor) ≤ value(current)
      return current current←neighbor
      until termination condition is met
    }
  } */

  public static boolean check_validity{
    //1. initializations
    int item = 0;

    // check for each item in the order, if it is included in items_carried
    for(int i = 0; i < order.length; i++){
      item = order[i];
      for(int b = 0; b < current_state.length; b++){
        // if PSU is used, look if the item is in this PSU
        if(current_state[b] == 1){
          for(int p = 0; p < psus[].length; p++){
            
          }
        }

      }
    }


  }
/* 

  First Choice:
  n = new Random().nextInt(neighbour_state.length); */