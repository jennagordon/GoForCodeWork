package com.tsguild.foundations.flowcontrol.whiles;

public class LovesMe {
    public static void main(String[] args) {
        int petals = 34;

        System.out.println("Well here goes nothing...");

        while (petals > 0) {
            if (petals == 1) {
                System.out.print("I KNEW IT! ");
            }
            if (petals % 2 == 0){
                System.out.println("It LOVES Me!");
            } else {
                System.out.println("It loves me NOT!");
            }
            petals--;
        }

    }
}
