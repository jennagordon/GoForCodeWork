package lesson04.userioclasslab;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        UserIO userIOConsole = new UserIOConsoleImpl();
        String userChoice;

        do {
            userChoice = userIOConsole.readString("What operation would you like to execute (add, subtract, multiply, or division) or would you like to exit?");


            if (userChoice.equalsIgnoreCase("exit")) {
                userIOConsole.print("Thank you for playing!");
                break;
            }
            int a = userIOConsole.readInt("What is the first number?");
            int b = userIOConsole.readInt("What is the second number?");


            if (userChoice.equalsIgnoreCase("add")) {
                userIOConsole.print(a + " + " + b + " = " + SimpleCalculator.add(a, b));
            } else if (userChoice.equalsIgnoreCase("subtract")) {
                userIOConsole.print(a + " - " + b + " = " + SimpleCalculator.subtract(a, b));
            } else if (userChoice.equalsIgnoreCase("multiply")) {
                userIOConsole.print(a + " X " + b + " = " + SimpleCalculator.multiply(a, b));
            } else if (userChoice.equalsIgnoreCase("divide")) {
                userIOConsole.print(a + " / " + b + " = " + SimpleCalculator.divide(a, b));
            }

        } while (true);
    }
}

