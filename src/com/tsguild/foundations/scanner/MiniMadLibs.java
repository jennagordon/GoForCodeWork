package com.tsguild.foundations.scanner;

import java.util.Scanner;

public class MiniMadLibs {
    public static void main(String[] args) {

        Scanner inputReader = new Scanner(System.in);

        String noun1, noun2, noun3, noun4, noun5;
        String adj1, adj2;
        float number;
        String verb1, verb2;

        System.out.println("I need a noun: ");
        noun1 = inputReader.nextLine();
        System.out.println("Now an adj: ");
        adj1 = inputReader.nextLine();
        System.out.println("Another noun: ");
        noun2 = inputReader.nextLine();
        System.out.println("And a number");
        number = inputReader.nextFloat();
        System.out.println("Another adj: ");
        adj2 = inputReader.next();
        System.out.println("A plural noun: ");
        noun3 = inputReader.next();
        System.out.println("Another one: ");
        noun4 = inputReader.next();
        System.out.println("One more: ");
        noun5 = inputReader.next();
        System.out.println("A verb (present tense) ");
        verb1 = inputReader.next();
        System.out.println("Same verb (past tense) ");
        verb2 = inputReader.next();

        System.out.println(noun1 + ": the " + adj1 + " frontier. These are the voyages of the starship " + noun2 + ". Its " + number + "-year mission: to explore strange " + adj2 + " " + noun3 + ", to seek out " + adj2 + " " + noun4 + " and " + adj2 + " " + noun5 + ", to boldly " + verb1 + " where no one has " + verb2 + " before.");
    }
}
