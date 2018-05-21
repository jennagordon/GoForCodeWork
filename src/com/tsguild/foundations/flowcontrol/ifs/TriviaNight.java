package com.tsguild.foundations.flowcontrol.ifs;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

public class TriviaNight {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        int correct = 0;
        int answer;

        System.out.println("FIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language");
        System.out.println("1) Source code \t" + "2) Assembly Language");
        System.out.println("3) C# \t" + "4) Machine Code");

        System.out.println("YOUR ANSWER: ");
        String answerString = inputReader.nextLine();
        answer = Integer.parseInt(answerString);

        if(answer == 4) {
            correct++;
        }

        System.out.println("SECOND QUESTION!");
        System.out.println("Website Security CAPTCHA Forms Are Descended From the Work of?");
        System.out.println("1) Grace Hopper \t" + "2) Alan Turing");
        System.out.println("3) Charles Babbage \t" + "4) Larry Page");

        System.out.println("YOUR ANSWER: ");
        answerString = inputReader.nextLine();
        answer = Integer.parseInt(answerString);

        if(answer == 2) {
            correct++;
        }

        System.out.println("THIRD QUESTION!");
        System.out.println("Which of THese Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.println("1) Serenity \t" + "2) The Battlestar Galactica");
        System.out.println("3) The USS Enterprise \t" + "4) The Millennium Falcon");

        System.out.println("YOUR ANSWER ");
        answerString = inputReader.nextLine();
        answer = Integer.parseInt(answerString);

        if(answer == 3) {
            correct++;
        }
        if(correct != 0) {
        System.out.println("Nice job - you got " + correct + " correct!");
        } else {
            System.out.println("YOU STINK!");
        }
    }
}
