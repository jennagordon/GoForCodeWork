package lesson02.exercise.refactorintoobjects;

import java.util.Random;
import java.util.Scanner;

public class LuckySevens {
    public void rollDice() {
        int bet, availableFunds, maxValue;
        int rollCount = 0;
        int rollCountMax = 0;
        bet = 0;


        Scanner inputReader = new Scanner(System.in);
        Random rGen = new Random();

        do {
            System.out.println("What is your bet?");
            String betString = inputReader.nextLine();
            bet = Integer.parseInt(betString);

            if (bet <= 0){
                System.out.println("Value must be greater than zero!");
            }else {
                break;
            }

        } while (true);

        availableFunds = bet;
        maxValue = bet;

        while (availableFunds > 0) {

            int dice1 = rGen.nextInt(6) + 1;
            int dice2 = rGen.nextInt(6) + 1;
            int sum = dice1 + dice2;
            rollCount++;

            //if both dice equal 7 then user gets $4 if not they lose $1.
            if (sum == 7){
                availableFunds = availableFunds + 4;
                if (availableFunds > maxValue) {
                    maxValue = availableFunds;
                    rollCountMax = rollCount;
                }
            } else {
                availableFunds--;
            }

        }
        System.out.println("Game Over");
        System.out.println("You should have quit after " + rollCountMax + " when you had $" + maxValue);

    }
}
