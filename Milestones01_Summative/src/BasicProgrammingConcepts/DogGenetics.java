package BasicProgrammingConcepts;

import java.util.Random;
import java.util.Scanner;

public class DogGenetics {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        Random numberGenerator = new Random();

        String dogName;

        System.out.println("What is your dog's name?");
        dogName = inputReader.nextLine();

        System.out.println("Well then, I have this highly reliable report on " + dogName + "'s prestigious background right here");
        System.out.println(dogName + " is:");

        int num1 = numberGenerator.nextInt(100);
        int num2 = numberGenerator.nextInt(100-num1);
        int num3 = numberGenerator.nextInt(100-(num1 + num2));
        int num4 = numberGenerator.nextInt(100-(num1 + num2 + num3));
        int num5 = 100 - num1 - num2 - num3 - num4;


        System.out.println(num1 + "% Lab");
        System.out.println(num2 + "% Chihuahua");
        System.out.println(num3 + "% Husky");
        System.out.println(num4 + "% German Shepard");
        System.out.println(num5 + "% Jack Russel Terrior");

        System.out.println("Wow, that's QUITE the dog!");

    }
}
