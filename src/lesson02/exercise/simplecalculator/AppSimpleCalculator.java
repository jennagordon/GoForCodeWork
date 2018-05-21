package lesson02.exercise.simplecalculator;

import lesson02.exercise.refactorintoobjects.InterestCalculator;

import java.util.Scanner;

public class AppSimpleCalculator {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        do {
            System.out.println("What operation would you like to execute (add, subtract, multiply, or division) or would you like to exit?");
            String userChoice = inputReader.nextLine();

            if (userChoice.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for playing!");
                break;
            }
            System.out.println("What is the first number?");
            int a = Integer.parseInt(inputReader.nextLine());
            System.out.println("What is the second number?");
            int b = Integer.parseInt(inputReader.nextLine());

            if (userChoice.equalsIgnoreCase("add")) {
                System.out.println(a + " + " + b + " = " + SimpleCalculator.add(a, b));
            } else if (userChoice.equalsIgnoreCase("subtract")) {
                System.out.println(a + " - " + b + " = " + SimpleCalculator.subtract(a, b));
            } else if (userChoice.equalsIgnoreCase("multiply")) {
                System.out.println(a + " X " + b + " = " + SimpleCalculator.multiply(a, b));
            } else if (userChoice.equalsIgnoreCase("divide")) {
                System.out.println(a + " / " + b + " = " + SimpleCalculator.divide(a, b));
            }

        } while (true);
    }
}
