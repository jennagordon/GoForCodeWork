package lesson05.exercisecollectionsandmaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExamples {
    public static void main(String[] args) {
        Map<String, Integer> populations = new HashMap<>();

        populations.put("USA", 313000000);
        populations.put("Canada", 340000000);
        populations.put("United Kingdom", 63000000);
        populations.put("Japan", 127000000);

        System.out.println("Map size is: " + populations.size());

        Set<String> keys = populations.keySet();

        for (String currentKey : keys) {
            Integer currentPopulation = populations.get(currentKey);
            System.out.println("The population of " + currentKey + " is " + currentPopulation);
        }
    }
}
