package Lesson04;

import com.sun.org.apache.xpath.internal.SourceTree;

public class SwitchStatement {
    public static void main(String[] args) {
        int day = 4;
        String dayName = "";

        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
                default:
                    dayName = "Invalid day";

        }
        System.out.println(dayName);

        int dayNum = 4;
        String dayType = "";

        switch (dayNum) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                dayType = "Weekday";
                break;
            case 6:
            case 7:
                dayType = "Weekend";
                break;
                default:
                    dayType = "Invalid Day";
        }
        System.out.println(dayType);
    }
}
