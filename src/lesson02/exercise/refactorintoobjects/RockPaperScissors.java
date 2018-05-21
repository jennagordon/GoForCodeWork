package lesson02.exercise.refactorintoobjects;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public void playGame() {
        Scanner inputReader = new Scanner(System.in);
        int numberOfGames;
        int compChoice;
        boolean playAgain = true;



        do {
            int userWin = 0;
            int compWin = 0;
            int tie = 0;

            System.out.println("How many rounds do you want to play?");
            String numberOfGamesString = inputReader.nextLine();
            numberOfGames = Integer.parseInt(numberOfGamesString);

            if (numberOfGames > 10 || numberOfGames < 1) {
                System.out.println("Not a valid amount of rounds. Goodbye!");
                break;
            } else {
                for (int i = 0; i < numberOfGames; i++) {
                    System.out.println("Pick rock, paper, or scissors");
                    String choiceString = inputReader.nextLine();

                    if (choiceString.equals("rock")) {
                        // rock(1); to use METHOD

                        compChoice = compRockPaperScissor();
                        // System.out.println(compChoice);
                        if (compChoice == 1) {
                            System.out.println("We've Tied!");
                            tie++;
                        }
                        if (compChoice == 2) {
                            System.out.println("You lose: paper beats rock");
                            compWin++;
                        }
                        if (compChoice == 3) {
                            System.out.println("You win: rock beat scissors");
                            userWin++;
                        }
                    }


                    if (choiceString.equals("paper")) {
                        //paper(2); to use METHOD

                        compChoice = compRockPaperScissor();
                        //System.out.println(compChoice);
                        if (compChoice == 2) {
                            System.out.println("We've Tied!");
                            tie++;
                        }
                        if (compChoice == 1) {
                            System.out.println("You Win: paper beats rock");
                            userWin++;

                        }
                        if (compChoice == 3) {
                            System.out.println("You Lose: Scissors beats paper");
                            compWin++;
                        }

                    }

                    if (choiceString.equals("scissors")) {
                        // scissors(3); For Method

                        compChoice = compRockPaperScissor();
                        // System.out.println(compChoice);
                        if (compChoice == 3) {
                            System.out.println("We've Tied!");
                            tie++;
                        }
                        if (compChoice == 1) {
                            System.out.println("You lose: rock beats scissors");
                            compWin++;
                        }
                        if (compChoice == 2) {
                            System.out.println("You win: scissors beats paper");
                            userWin++;

                        }
                    }
                }


                System.out.println("Games tied: " + tie);
                System.out.println("Games User Won: " + userWin);
                System.out.println("Games Computer Won: " + compWin);


                if (userWin > compWin) {
                    System.out.println("You are the winner!!!!");
                } else if (userWin == compWin) {
                    System.out.println("You have tied with the computer!");
                } else {
                    System.out.println("Sorry! You lost. The computer won...");
                }



                System.out.println("Want to play again?");
                String playAgainString = inputReader.nextLine();

                if (playAgainString.equals("yes")) {
                    playAgain = true;

                } else {
                    playAgain = false;
                    System.out.println("Thanks for playing!");
                }
            }



        } while (playAgain);

    }



    public static int compRockPaperScissor () {
        Random randomizer = new Random();
        int compChoice = randomizer.nextInt(3) + 1;

        return compChoice;
    }
}



