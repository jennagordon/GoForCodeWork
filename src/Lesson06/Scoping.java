package Lesson06;

public class Scoping {
    public static void main(String[] args) {
        int age = 42;
        System.out.println(age);

        for(int i = 0; i <5; i++) {
            System.out.println(i);
            System.out.println(age);
        }

        if (age < 18) {
            int yearsToWait = age - 18;
            System.out.println(yearsToWait);
        }

    }
}
