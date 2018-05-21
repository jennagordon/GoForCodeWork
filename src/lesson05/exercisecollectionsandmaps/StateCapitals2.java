package lesson05.exercisecollectionsandmaps;

import lesson04.userioclasslab.UserIO;
import lesson04.userioclasslab.UserIOConsoleImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StateCapitals2 {
    public static void main(String[] args) {

        Map<String, Capital> stateCapitals = new HashMap<>();
        UserIO userConsole = new UserIOConsoleImpl();

        Capital alabama = new Capital("Montgomery", 205000, 156);
        Capital alaska = new Capital("Juneau", 31000, 3255);
        Capital arizona = new Capital("Phoenix", 1445000, 517);
        Capital arkansas = new Capital("Little Rock", 193000, 116);

        stateCapitals.put("alabama", alabama);
        stateCapitals.put("alaska", alaska);
        stateCapitals.put("arizona", arizona);
        stateCapitals.put("arkansas", arkansas);

        Set <String> states = stateCapitals.keySet();

        userConsole.print("STATE/CAPITAL PAIRS:");
        userConsole.print("============");
        for (String key: states) {
            userConsole.print(key + " - " +
                    stateCapitals.get(key).getName() +
                    " | Pop: " + String.valueOf(stateCapitals.get(key).getPopulation()) +
                    " | Pop: " + String.valueOf(stateCapitals.get(key).getSquareMileage()));
        }

        int lowerLimit = userConsole.readInt("Please enter the lower limit for capital city population:");

        for(String key : states) {
            if(stateCapitals.get(key).getPopulation() > lowerLimit) {
                userConsole.print(key + " - " +
                        stateCapitals.get(key).getName() +
                        " | Pop: " + String.valueOf(stateCapitals.get(key).getPopulation()) +
                        " | Pop: " + String.valueOf(stateCapitals.get(key).getSquareMileage()));
            }
        }
    }
}
