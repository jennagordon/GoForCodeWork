package com.tsguild.foundations.random;

import java.util.Random;

public class GuessMeMore {
    public static void main(String[] args) {
        Random randomize = new Random();
        boolean flip = randomize.nextBoolean();

        System.out.println("Ready, Set, Flip....!!");

        if(flip){
            System.out.println("You got tails!");
        } else {
            System.out.println("You got heads!");
        }
    }
}
