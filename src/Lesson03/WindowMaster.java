package Lesson03;

import java.util.Scanner;

public class WindowMaster {
    public static void main(String [] args) {
        float height;
        float width;

        float areaOfWindow;
        float cost;
        float perimeterOfWindow;

        Scanner myScanner = new Scanner(System.in);

        height = readValue("Please enter the window height:");
        width = readValue("Please enter the window width:");

        areaOfWindow = height * width;
        perimeterOfWindow = 2 * (height + width);

        cost = ((3.50f * areaOfWindow) + (2.50f * perimeterOfWindow));

        System.out.println("Window height = " + height);
        System.out.println("Window width = " + width);
        System.out.println("Window area = " + areaOfWindow);
        System.out.println("Window perimeter = " + perimeterOfWindow);
        System.out.println("Total cost = " + cost);

    }

    public static float readValue(String prompt) {
        Scanner myScanner = new Scanner(System.in);
        System.out.println(prompt);
        String input = myScanner.nextLine();
        float floatVal = Float.parseFloat(input);
        return floatVal;
    }
}
