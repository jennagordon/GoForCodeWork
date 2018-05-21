package lesson04.userioclasslab;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    private Scanner inputReader = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        // display prompt
        print(prompt);
        // read in user input as a String
        String numberString = inputReader.nextLine();
        // convert the input to a double
        double number = Double.parseDouble(numberString);
        // return that double
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
       boolean invalidRange = false;
       double number = 0;
        do {
            print (prompt);

            // read in the user input as String
            String numberString = inputReader.nextLine();
            // parse to double
            number = Double.parseDouble(numberString);

            // if user input is between min and max
            if (number >= min && number <= max) {
                invalidRange = false;
            } else {
               print("Invalid range");
            }

        } while (invalidRange);
        // return number
        return number;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);

        String numberString = inputReader.nextLine();
        float number = Float.parseFloat(numberString);

        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        boolean invalidRange = false;
        float number = 0;
        do {
            print (prompt);

            // read in the user input as String
            String numberString = inputReader.nextLine();
            // parse to double
            number = Float.parseFloat(numberString);

            // if user input is between min and max
            if (number >= min && number <= max) {
                invalidRange = false;
            } else {
                print("Invalid range");
            }

        } while (invalidRange);
        // return number
        return number;
    }

    @Override
    public int readInt(String prompt) {
       int number = 0;
       boolean invalidInput = false;
       do {
           try {
               // display the prompt
               print(prompt);
               // read in the user input as a String
               String numberString = inputReader.nextLine();
               // convert String to an int
               number = Integer.parseInt(numberString);
               invalidInput = false;

           } catch (NumberFormatException e) {
               print("Please enter a valid integer");
               invalidInput = true;
           }
       } while (invalidInput);
       // return that int
       return number;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        boolean invalidRange = false;
        int number = 0;
        do {
            print (prompt);

            // read in the user input as String
            String numberString = inputReader.nextLine();
            // parse to double
            number = Integer.parseInt(numberString);

            // if user input is between min and max
            if (number >= min && number <= max) {
                invalidRange = false;
            } else {
                print("Invalid range");
            }

        } while (invalidRange);
        // return number
        return number;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);

        String numberString = inputReader.nextLine();
        long number = Long.parseLong(numberString);

        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean invalidRange = false;
        long number = 0;
        do {
            print (prompt);

            // read in the user input as String
            String numberString = inputReader.nextLine();
            // parse to double
            number = Long.parseLong(numberString);

            // if user input is between min and max
            if (number >= min && number <= max) {
                invalidRange = false;
            } else {
                print("Invalid range");
            }

        } while (invalidRange);
        // return number
        return number;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);

        String userString = inputReader.nextLine();

        return userString;
    }
}
