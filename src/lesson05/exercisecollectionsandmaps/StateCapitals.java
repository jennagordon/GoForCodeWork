package lesson05.exercisecollectionsandmaps;

import com.sun.org.apache.xpath.internal.SourceTree;
import lesson04.userioclasslab.UserIO;
import lesson04.userioclasslab.UserIOConsoleImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StateCapitals {


    public static void main(String[] args) {
        UserIO userConsole = new UserIOConsoleImpl();

        Map<String, String> capitals = new HashMap<>();

        capitals.put("Alabama", "Montgomery");
        capitals.put("Alaska", "Juneau");
        capitals.put("Arizona", "Phoenix");
        capitals.put("Arkansas", "Little Rock");

        Set<String> states = capitals.keySet();

        userConsole.print("STATES");
        userConsole.print("=======");
        for(String state: states) {
            userConsole.print(state);
        }

        userConsole.print("  ");
        userConsole.print("CAPITALS");
        userConsole.print("=======");
        for (String state: states) {
            userConsole.print(capitals.get(state));
        }

        userConsole.print("  ");
        userConsole.print("STATE/CAPITAL PAIRS:");
        userConsole.print("=================");
        for (String state: states) {
            userConsole.print(state + " - " + capitals.get(state));
        }
    }

}
