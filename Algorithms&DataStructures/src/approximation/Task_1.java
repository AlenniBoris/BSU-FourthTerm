package approximation;

import java.io.*;

public class Task_1 {
    static int n, m;

    static class Part{
        private int income;
        private int processing;
        private int outcome;

        public Part(int income, int processing, int outcome) {
            this.income = income;
            this.processing = processing;
            this.outcome = outcome;
        }
    }

    static void fill(BufferedReader sc) throws IOException{
        String[] parms = sc.readLine().split(" ");
        n = Integer.parseInt(parms[0]);
        m = Integer.parseInt(parms[1]);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("output.txt"))));
    }
}
