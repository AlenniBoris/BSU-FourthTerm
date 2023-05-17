package Graphs;

import java.io.*;

public class Task0_10 {
    static int n, m;
    static int[] us, vs, ws;
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt")));

        String[] sizes = sc.readLine().split(" ");
        n = Integer.parseInt(sizes[0]);
        m = Integer.parseInt(sizes[1]);

        us = new int[n];
        vs = new int[n];
        ws = new int[n];
    }
}
