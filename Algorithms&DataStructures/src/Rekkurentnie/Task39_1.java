package Rekkurentnie;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Task39_1 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(new File("in.txt"))));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("out.txt"))));

        String numberLine = sc.nextLine().replaceAll(" ", "");
        if (numberLine.isEmpty()){
            out.println("No solution");
            out.close();
            System.exit(0);
        }

        String alphabetLine = sc.nextLine().replaceAll(" ", "");;
        if (alphabetLine.isEmpty()){
            out.println("0");
            out.close();
            System.exit(0);
        }

        int number = Integer.parseInt(numberLine);
        int alphabetSize = Integer.parseInt(alphabetLine);

        if (alphabetSize <= 0){
            out.println("0");
            out.close();
            System.exit(0);
        }

        List<String[]> letters = new ArrayList<>();

        letters.add(new String[]{"O", "Q", "Z"}); //0
        letters.add(new String[]{"I", "J"});      //1
        letters.add(new String[]{"A", "B", "C"}); //2
        letters.add(new String[]{"D", "E", "F"}); //3
        letters.add(new String[]{"G", "H"});      //4
        letters.add(new String[]{"K", "L"});      //5
        letters.add(new String[]{"M", "N"});      //6
        letters.add(new String[]{"P", "R", "S"}); //7
        letters.add(new String[]{"T", "U", "V"}); //8
        letters.add(new String[]{"W", "X", "Y"}); //9

        List<String> alphabet = new ArrayList<>(alphabetSize);
        while(sc.hasNextLine()){
            alphabet.add(sc.nextLine());
        }

        List<String> result = new ArrayList<>();

        while (number != 0){
            String[] numberLetters = letters.get(number%10);

            for (String numberLetter : numberLetters) {
                if (alphabet.contains(numberLetter)) {
                    result.add(numberLetter);
                    break;
                }
            }

            number /= 10;
        }


        if (result.isEmpty()){
            out.println("No solution");
        } else {
            out.println(String.valueOf(result.size()));
            result.sort(Comparator.reverseOrder());
            out.print(result.toString().replaceAll("[^A-Z\\s]",""));
        }
        
        sc.close();
        out.close();
        System.exit(0);
    }
}
