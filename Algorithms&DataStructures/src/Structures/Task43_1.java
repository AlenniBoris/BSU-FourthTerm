package Structures;

import java.io.*;

public class Task43_1 {
    static int n;
    static int[] frequences;

    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream("huffman.in")));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("huffman.out"))));

        n = Integer.parseInt(sc.readLine());

        String[] str = sc.readLine().split(" ");
        frequences = new int[n];
        for (int i = 0; i < n; i++) {
            frequences[i] = Integer.parseInt(str[i]);
        }

        
    }
}
