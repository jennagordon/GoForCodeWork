package interestcalculatorbigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class InterestCalculator {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

       // double annualInterest;
       // double principal;
        int years;
      //  double interest = 0;


        System.out.println("What is your annual interest rate?");
        String annualInterestString = inputReader.nextLine();
        BigDecimal annualInterest = new BigDecimal(annualInterestString);
        annualInterest = annualInterest.setScale(2, RoundingMode.HALF_UP);

        System.out.println("What is your initial amount of principal?");
        String principalString = inputReader.nextLine();
        BigDecimal principal = new BigDecimal(principalString);
        principal = principal.setScale(2, RoundingMode.HALF_UP);

        System.out.println("How many years should the money stay in the fund?");
        String yearsString = inputReader.nextLine();
        years = Integer.parseInt(yearsString);

        for (int i = 1; i <= years; i++) {
            System.out.println("Year: " + i);
            System.out.println("Current principal is " + principal);
            BigDecimal yearBeginningPrincipal = principal;


            for (int j = 0; j < 4; j++) {

                principal = (principal.multiply (((annualInterest.divide(new BigDecimal(4))).divide(new BigDecimal( 100)))).add(new BigDecimal(1)));

            }
            BigDecimal interest = principal.subtract(yearBeginningPrincipal);
            System.out.println(interest);


        }


    }
}
