import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.nio.*;

public class RandomRestart {

    public static void main(String[] args){
        Interface Interface = new Interface();
        n = Interface.getInput();
        int[] state_values = new [n];

        for(int i = 1; i <= n; i++){
            HillClimbing hillClimbing = new HillClimbing();
            boolean[] highestNeighbour = hillClimbing.bestState();
            int state_value = hillClimbing.countPsus(highestNeighbour);
            state_values[0] = state_value;
            if(state_value[i] > state_values[0]){
                state_values[0] = state_value[i];
            }

        }
        // highestValue = state_values[0];




    }

}
