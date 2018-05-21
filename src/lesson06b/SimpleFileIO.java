package lesson06b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class SimpleFileIO {
    public static void main(String[] args) throws Exception{

        PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));

        out.println("This is line 1");
        out.println("This is line 2");

        out.flush();
        out.close();

        Scanner scanner = new Scanner(new BufferedReader(new FileReader("OutFile.txt")));

        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            System.out.println(currentLine);
        }

        scanner.close();

    }
}
