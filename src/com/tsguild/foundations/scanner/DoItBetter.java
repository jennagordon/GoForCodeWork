package com.tsguild.foundations.scanner;

import java.util.Scanner;

public class DoItBetter {
    public static void main(String[] args) {

        Scanner inputResult = new Scanner(System.in);

        int miles, hotdogs, languages;

        System.out.println("How many miles can you run?");
        miles = inputResult.nextInt();
        System.out.println("That's all? I can run " + (miles * 2 + 1) + " miles!");

        System.out.println("How many hotdogs can you eat?");
        hotdogs = inputResult.nextInt();
        System.out.println("That's all? I can eat " + (hotdogs * 2 + 1)+ " hotdogs!");

        System.out.println("How many languages do you know?");
        languages = inputResult.nextInt();
        System.out.println("That's all? I know " + (languages * 2 + 1) + " languages!");
    }
}
