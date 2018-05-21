package com.tsguild.foundations.basics.methods;

import java.util.Random;



public class BarelyControlledChaos {

    static Random gen = new Random();

    public static void main(String[] args) {


        String color = color(); // call color method here
        String animal = animal(); // call animal method again here
        String colorAgain = color(); // call color method again here
        int weight = number(5, 200); // call number method,
        // with a range between 5 - 200
        int distance = number(10, 20); // call number method,
        // with a range between 10 - 20 asdfasdf
        int number = number(10000, 20000); // call number method,
        // with a range between 10000 - 20000
        int time = number(2, 6); // call number method,
        // with a range between 2 - 6


        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ",  " + weight + "lb" + " miniature " + animal
           + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over "
                + number + " " + colorAgain + " poppies for nearly "
                + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, "
                + "let me tell you!");

    }


    public static String color() {
        int num = gen.nextInt(5) + 1;
        String colorR = "";

        if (num == 1) {
            colorR = "green";
        }
        if (num == 2) {
            colorR = "pink";
        }
        if (num == 3) {
            colorR = "blue";
        }
        if (num == 4) {
            colorR = "purple";
        }
        if (num == 5) {
            colorR = "black";
        }
        return colorR;

    }

    public static String animal() {
        int num = gen.nextInt(7) + 1;
        String animalR = "";

        if (num == 1) {
            animalR = "elephant";
        }
        if (num == 2) {
            animalR = "walrus";
        }
        if (num == 3) {
            animalR = "bunny";
        }
        if (num == 4) {
            animalR = "cheetah";
        }
        if (num == 5) {
            animalR = "owl";
        }
        if (num == 6) {
            animalR = "skunk";
        }
        if (num == 7) {
            animalR = "puppy";
        }
        return animalR;
    }
    public static int number(int min, int max) {
        int numR = max - min;
        int rando = gen.nextInt(numR) + min;

        return rando;


    }
}
