package exerciseenums;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("What is the day of the week?");
        String userDayString = inputReader.nextLine();
        String upper = userDayString.toUpperCase();
        DaysOfTheWeek userDay = DaysOfTheWeek.valueOf(upper);

        if (checkForDay(userDay) != 10) {
        System.out.println("It is " + checkForDay(userDay) + " days until Friday!!");
        } else {
            System.out.println("You have entered an invalid day!");
        }
    }

    public static Integer checkForDay(DaysOfTheWeek userDay) {

        int result = 10;
        switch (userDay) {
            case MONDAY:
                DaysOfTheWeek.valueOf("MONDAY");
                result = 4;
                break;
            case TUESDAY:
                DaysOfTheWeek.valueOf("TUESDAY");
                result = 3;
                break;
            case WEDNESDAY:
                DaysOfTheWeek.valueOf("WEDNESDAY");
                result = 2;
                break;
            case THURSDAY:
                DaysOfTheWeek.valueOf("THURSDAY");
                result = 1;
                break;
            case FRIDAY:
                DaysOfTheWeek.valueOf("FRIDAY");
                result = 0;
                break;
            default:
                System.out.println("UNKNOWN DAY");
                break;

        }
        return result;
    }
}


