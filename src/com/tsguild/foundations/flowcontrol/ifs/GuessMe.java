package com.tsguild.foundations.flowcontrol.ifs;


import java.util.Scanner;

public class GuessMe {
    public static void main(String[] args) {
        Scanner inputReading = new Scanner(System.in);
        int number = 42;

        System.out.println("I've chosen a number. Betcha can't guess it!");
        System.out.println("Your guess: ");
        String guessString = inputReading.nextLine();
        int guess = Integer.parseInt(guessString);

        if (guess > number) {
            System.out.println("Too bad, way too high. I chose " + number);
        } else if (guess < number) {
            System.out.println("Ha, nice try - tool low! I chose " + number);
        } else {
            System.out.println("Wow, nice guess! That was it!");
        }

    }
}
