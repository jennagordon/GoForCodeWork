package Lesson06;

import java.util.Scanner;

public class VoteExample {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int age = 0;
        boolean oldEnoughToVote;

        for (int i = 0; i < 2; i++) {

            age = promptUserForAge();
            oldEnoughToVote = isOldEnoughToVote(age);


            if (oldEnoughToVote) {
                System.out.println("You are old enough to vote");
            } else {
                System.out.println("You are not old enough to vote, sorry");
            }
        }

    }

    public static int promptUserForAge() {
        System.out.println("How old are you?");
        String userAgeString = scanner.nextLine();
        int userAge = Integer.parseInt(userAgeString);
        return userAge;
    }

    public static boolean isOldEnoughToVote(int userAge) {
        if(userAge >= 18) {
            return true;
        } else {
            return false;
        }
    }
}
