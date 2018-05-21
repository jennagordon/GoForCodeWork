package InterestCalculator;

import java.util.Scanner;

public class InterestCalc {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        double annualInterest;
        double principal;
        int years;
        double interest = 0;


        System.out.println("What is your annual interest rate?");
        String annualInterestString = inputReader.nextLine();
        annualInterest = Double.parseDouble(annualInterestString);

        System.out.println("What is your initial amount of principal?");
        String principalString = inputReader.nextLine();
        principal = Double.parseDouble(principalString);

        System.out.println("How many years should the money stay in the fund?");
        String yearsString = inputReader.nextLine();
        years = Integer.parseInt(yearsString);

        for (int i = 1; i <= years; i++) {
            System.out.println("Year: " + i);
            System.out.println("Current principal is " + principal);
            double yearBeginningPrincipal = principal;


            for (int j = 0; j < 4; j++) {

                principal = (principal * (1 + ((annualInterest / 4) / 100)));

            }
            interest = principal - yearBeginningPrincipal;
            System.out.println(interest);


        }


    }
}
