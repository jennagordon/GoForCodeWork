package com.tsguild.foundations.scanner;

import java.util.Scanner;

public class AllTheTrivia {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        String answer1, answer2, answer3, answer4;

        System.out.println("1,024 Gigabytes is equal to one what?");
        answer1 = inputReader.nextLine();
        System.out.println("In our solar system which is the only planet that roates clockwise?");
        answer2 = inputReader.nextLine();
        System.out.println("The largest volcano ever discoverd in our solar system is located on which planet?");
        answer3 = inputReader.nextLine();
        System.out.println("What is the most abundant element in the earth's atmosphere?");
        answer4 = inputReader.nextLine();

        System.out.println("Wow, 1,024 Gigabytes is a " + answer3 + "!");
        System.out.println("I didn't know that the largest ever volcano was discovered on" + answer1 + "!");
        System.out.println("That's amazing that " + answer2 + " is the most abundant element in the atmosphere...");
        System.out.println(answer4 + " is the only planet that rotates clockwise, neat!");
    }
}
