package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;

public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        String yearString;
        int year;
        String name;

        System.out.println("Hey, let's play a game! What's your name?");
        name = inputReader.nextLine();

        System.out.println("Ok," + name +", when were you born?");
        yearString = inputReader.nextLine();
        year = Integer.parseInt(yearString);

        System.out.println("Well " + name + " ...");

        if(year < 2005) {
            System.out.println("Did you know that Pixar's 'Up' came out half a decade ago?");
        }
        if(year < 1995) {
            System.out.println("And that the first Harry Potter came out over 15 years ago!");
        }
        if(year < 1985) {
            System.out.println("Also, Space Jam came out not last decade, but the one before THAT.");
        }
        if(year < 1975) {
            System.out.println("The original Jurassic Park release is closer to the lunar landing, than today!");
        }
        if(year < 1965) {
            System.out.println("Fun Fact: MASH has been around for almost half a century!");
        }
    }
}
