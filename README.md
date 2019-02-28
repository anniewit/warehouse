## Planning module for an intelligent warehouse

### General Idea:
The warehouse consists of multiple Portable Storage units (PSUs), in which the items are stored.
This programm calculates which PSUs to send to the packaging station using different local search algorithms.
Warehouse, order and the algorithm can be specified by the user. 

#### Interface
The interface class takes in the input of the user (a warehouse file, an order file and a selected algorithm) and hands this to the next processing stage.
//(Interface is supposed to be self-explaining)

#### Preprocessing
In the preprocessing step, the files inserted by the user are parsed. 
From the warehouse file PSUs are assigned to identifiers and each item is assigned a number. 
The order is then translated into the numbers previously assigned to the items of the warehouse. //(In case an order cannot be satisfied by the warehouse, an error message is shown.)

Consequently the warehouse is converted into a Hashmap, that contains the **Psu identifiers** as *Keys* and the **items** stored in the respective PSU as *Values*. Therefore the *Values* of the Hashmap are Array Lists. 
Following the Hashmap is iterated and the items that were not ordered are deleted as they don't play a role in the following search and deleting them will reduce the number of steps required for the search.
Further the PSUs that become empty when deleting the not required items are also removed.
The result is a reduced Hashmap that maps the ordered items to PSU identifiers.

#### Local Search Algorithms
Hillclimbing 
Hillclimbing requires the identifiers of the ordered items generated in the preprocessing step. An array of these identifiers will be turned into a random boolean array, this will be the starting state on which the algorithm will operate upon. A state uses only the PSUs whose identifier has the same position as a true value in the boolean array. The goal is to maximize the objective function of the hillclimbing algorithm. This is to minimize the PSUs or the truth values while still returning the full order. 
Using the starting state as defined above, neighbours can now be created. The neighborhood of a state is defined as these states that differ from the starting state by one value in the boolean array. Before getting each neighbour's value, meaning how good it succeeds in maximizing the objective function, the neighbours are checked for validity. If a neighbour cannot cover all items in the order it is not longer considered for further processing. All others are compared to each other, saving the neighbour with the best value.
Next this value is compared to the value of the starting state. If the starting state's value is higher the state is returned and the algorithm ends if the neighbour's value is better the neighbour becomes the new starting state and the whole process starts from the beginning. 
First-Choice Hillclimbing 
Is a simplyfied way of doing Hillclimbing. The starting state is also initialized as a random boolean array and the neighbourhood is the same as well. The only difference is that First-Choice takes the first neighbour whose value is better than the one of the starting state. In order to do that, after validation, one neighbour is declared and immediately compared to the starting state. Everything else happens as described above. 
Random restart Hillclimbing 
Another variation of the hillclimbing algorithm that does several hillclimbing searches consecutively. Out of all these hillclimbing runs the best state is choosen. By calling the Hillclimbing method several times and comparing their return value random restart can be accomplished. 

##### Objective Function
##### Neighborhood

#### Postprocessing
