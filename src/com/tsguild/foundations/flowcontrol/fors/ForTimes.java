package com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

public class ForTimes {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        int number;


        System.out.println("Which times table shall I recite?");
        String numberString = inputReader.nextLine();
        number = Integer.parseInt(numberString);

        for(int i = 1; i < 16; i++) {
            System.out.println(i + " * " + number + " is: " + (i * number));
        }

    }
}
