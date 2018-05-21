package com.tsguild.foundations.random;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Random;

public class Opinionator {
    public static void main(String[] args) {
        Random randomizer = new Random();
        System.out.println("I can't decide what animal I like the best.");
        System.out.println("I know! Random can decide FOR ME!");

        int x = randomizer.nextInt(5);

        System.out.println("The number we chose was: " + x);

        if(x == 0) {
            System.out.println("Llamas are the best!");
        } else if (x == 1) {
            System.out.println("Dodos are the best!");
        } else if (x == 2) {
            System.out.println("Woolly Mammoths are DEFINITELY the best!");
        } else if (x == 3) {
            System.out.println("Sharks are the greatest, they have their own week");
        } else if (x == 4) {
            System.out.println("Cockatoos are just so awesomme!");
        } else if (x == 5) {
            System.out.println("Have you ever met a Mole-Rate? They're GREAT!");
        }

        System.out.println("Thanks Random, maybe YOU'RE the best!");
    }
}
