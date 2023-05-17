package KR1;

import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Task3 {
    static int coef = 1000000007;
    static int k;
    static long lambda;
    static int N;
    static long[] B;

    static long getResult(int N, long[] dp){

        if (N < 0){
            return 0;
        }
        if (N == 0){
            return lambda % coef;
        }
        if (N % 2 == 1){
            if (dp[N] == Long.MIN_VALUE){
                dp[N] = (getResult(N-1, dp) % coef + N % coef) % coef;
            }
            return dp[N];
        }
        if (N % 2 == 0 && N >= 2){
            if (dp[N] == Long.MIN_VALUE){
                long sum = 0;
                for (int i = 0; i < k; i++) {
                    sum %= coef;
                    sum += ( (B[i] % coef) * (getResult((N%coef)/2 - i, dp) % coef) ) % coef;
                }
                dp[N] = sum;
            }
        }
        return dp[N];
    }
    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));

        String[] line = sc.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        k = Integer.parseInt(line[1]);
        lambda = Integer.parseInt(line[2]);

        B = new long[k];
        String[] numbers = sc.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            B[i] = Long.parseLong(numbers[i]);
        }

        long[] dp = new long[N+2];
        for (int i = 0; i < N+2; i++) {
            dp[i] = (Long.MIN_VALUE);
        }

        out.println(getResult(N, dp));
        out.close();
    }
}
