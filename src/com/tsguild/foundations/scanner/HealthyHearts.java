package com.tsguild.foundations.scanner;

import java.util.Scanner;

public class HealthyHearts {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        float age;

        System.out.println("What's your age?");
        age = inputReader.nextInt();

        float max = 220- age;
        float targetMin = max * .5f;
        float targetMax = max * .85f;

        System.out.println("Your maximum heart rate should be " + max + " beats per minute");
        System.out.println("Your target HR Zone is " + targetMin + " - " + targetMax + " beats per minute");
    }
}
