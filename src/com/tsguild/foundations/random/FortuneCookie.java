package com.tsguild.foundations.random;

import java.util.Random;

public class FortuneCookie {
    public static void main(String[] args) {
        Random randomize = new Random();

        int number = randomize.nextInt(10) + 1;

        if(number == 1) {
            System.out.println("Those aren't the droids you're looking for.");
        }
        if(number == 2) {
            System.out.println("Never go in against a Sicilian when death is on the line!");
        }
        if(number == 3) {
            System.out.println("Goonies never say die.");
        }
        if(number == 4) {
            System.out.println("With great power there must also come - great responsibility");
        }
        if(number == 5) {
            System.out.println("Never argue with the data");
        }
        if(number == 6) {
            System.out.println("Try not. Do, or do not. There is no try.");
        }
        if(number == 7) {
            System.out.println("You are a leaf on the wind, watch how you soar.");
        }
        if(number == 8) {
            System.out.println("Do absolutely nothing, and it will be everything that you thought it could be.");
        }
        if(number == 9) {
            System.out.println("Kneel before Zod.");
        }
        if(number == 10) {
            System.out.println("Make it so.");
        }

    }
}
