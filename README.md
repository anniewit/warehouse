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
Consequently the warehouse is converted into a Hashmap, that contains the **Psu identifiers** as *Keys* and the **items** stored in the respective PSU as *Values*.
Following the Hashmap is iterated and the items that were not ordered are deleted as they don't play a role in the following search and deleting them will reduce the number of steps required for the search.
Further the PSUs that become empty when deleting the not required items are also removed.
The result is a reduced Hashmap that maps PSU identifiers to the ordered items.

#### Local Search Algorithms
##### Objective Function
##### Neighborhood

#### Postprocessing
