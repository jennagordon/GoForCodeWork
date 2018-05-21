package BasicProgrammingConcepts;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        int age;


        System.out.println("What's your age?");
        String ageString = inputReader.nextLine();
        age = Integer.parseInt(ageString);

        int max = 220 - age;
        double targetMin = max * .5;
        double targetMax = max * .85;

        System.out.println("Your maximum heart rate should be " + max + " beats per minute");
        System.out.println("Your target HR zone is " + targetMin + " - " + targetMax + " beats per minute" );

    }
}
