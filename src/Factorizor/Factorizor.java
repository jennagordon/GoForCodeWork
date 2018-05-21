package Factorizor;

import java.util.Scanner;

public class Factorizor {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        int userNumber;

        System.out.println("What number would you like to factor?");
        String userNumberString = inputReader.nextLine();
        userNumber = Integer.parseInt(userNumberString);

        System.out.println("Your number is: " + userNumberString);

        int sum = 0;
        int factorCount = 0;

        for (int i = 1; i < userNumber; i++) {
            int calc = userNumber % i;

            if(calc == 0) {
                System.out.println(i);
                factorCount++;
                sum += i;
            }
        }
        System.out.println("Your total number of factors is: " + factorCount);

        if (factorCount == 1) {
            System.out.println("Your number is PRIME");
        } else if (sum == userNumber){
            System.out.println("Your number is perfect");
            System.out.println("Your number is NOT prime");
        } else {
            System.out.println("Your number is not perfect");
            System.out.println("Your number is NOT prime");
        }
    }



}
