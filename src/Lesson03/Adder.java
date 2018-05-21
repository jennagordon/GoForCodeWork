package Lesson03;

import java.util.Scanner;

public class Adder {
    public static void main(String [] args) {
        int sum = 0;
        int operand1 = 0;
        int operand2 = 0;

       Scanner myScanner = new Scanner(System.in);
       String stringOperand1 = "";
       String stringOperand2 = "";

       System.out.println("Please enter the first number to be added:");
       stringOperand1 = myScanner.nextLine();

       System.out.println("Please enter the second number to be added:");
       stringOperand2 = myScanner.nextLine();

       operand1 = Integer.parseInt(stringOperand1);
       operand2 = Integer.parseInt(stringOperand2);

       sum = operand1 + operand2;

       System.out.println("Sum is: " + sum);
    }
}
