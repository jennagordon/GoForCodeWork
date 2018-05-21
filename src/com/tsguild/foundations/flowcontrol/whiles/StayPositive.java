package com.tsguild.foundations.flowcontrol.whiles;

import java.util.Scanner;

public class StayPositive {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        String numberString;
        int row = 0;

        System.out.println("What number should I count down from?");
        numberString = inputReader.nextLine();
        int number = Integer.parseInt(numberString);

        while (number > 0){
            System.out.print(number + " ");
            number--;

            row++;

            if(row > 9){
                System.out.println();
                row = 0;
            }
        }

    }
}
