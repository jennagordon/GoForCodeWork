package com.tsguild.foundations.variables;

import java.util.Scanner;

public class BiggerBetterAdder {
    public static void main(String[] args) {
        int num1, num2, num3, sum;
        num1 = 3;
        num2 = 15;
        num3 = 22;
        sum = num1 + num2 + num3;

        Scanner inputReader = new Scanner(System.in);

        System.out.println(num1 + " " + num2 + " " + num3);
        System.out.println("Your sum is " + sum);
        System.out.println("Your sum is " + sum);

        System.out.println("Please enter the first number ");
        num1 = inputReader.nextInt();

        System.out.println("Please enter the second number ");
        num2 = inputReader.nextInt();

        System.out.println("Please enter the third number ");
        num3 = inputReader.nextInt();

        sum = num1 + num2 + num3;

        System.out.println(num1 + " " + num2 + " " + num3);
        System.out.println("Your sum is " + sum);
        System.out.println("Your sum is " + sum);


    }
}
