package com.tsguild.foundations.scanner;

import java.util.Scanner;

public class PassingTheTuringTest {
    public static void main(String[] args) {
        String name, color, fruit;
        int number;

        Scanner inputReader = new Scanner(System.in);

        System.out.println("Hello there!");
        System.out.println("What's your name?");
        name = inputReader.nextLine();

        System.out.println("Hi, " + name +"!" + " What's your favorite color?");
        color = inputReader.nextLine();

        System.out.println("Huh, " + color +"? Mine's Electric Green.");
        System.out.println("I really like limes. They're my favorite food, too. What's YOUR favorite fruit, "
        + name + "?");
        fruit = inputReader.nextLine();

        System.out.println("Really? " + fruit + "? That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number?");
        number = inputReader.nextInt();

        int sum = number * -7;

        System.out.println(number + " is a cool number. Mine's 7.");
        System.out.println("Did you know " + number + " * -7 is " + sum + "? That's a cool number too!");
        System.out.println("Well, thanks for talking to me, " + name);
    }
}
