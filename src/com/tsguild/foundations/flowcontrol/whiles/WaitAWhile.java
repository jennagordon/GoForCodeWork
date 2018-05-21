package com.tsguild.foundations.flowcontrol.whiles;

public class WaitAWhile {
    public static void main(String[] args) {

        int timeNow = 5;
        int bedTime = 10;

        while(timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I'll stay up a liiiiittle longer....");
            timeNow++;
        }

        System.out.println("Oh, It's  " + timeNow + " o'clock.");
        System.out.println("Guess I should got to bed ...");
    }
}
