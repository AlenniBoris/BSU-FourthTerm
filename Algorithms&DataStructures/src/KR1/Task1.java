package KR1;


import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Task1 {
    static int N;
    static long[] array;

    static void fillArray(BufferedReader sc, int N) throws IOException{
        array = new long[N];
        String[] line = sc.readLine().split(" ");
        for (int i = 0; i < line.length; i++) {
            array[i] = Long.parseLong(line[i]);
        }
        sc.close();
    }

    static long getResult(){
        long[][] dp = new long[N][3];

        for (int i = 0; i < 3; i++) {
            dp[0][i] = Long.MIN_VALUE;
        }

        dp[0][(int) (((array[0] % 3) + 3)%3)] = array[0];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Long.MIN_VALUE;
            dp[i][1] = Long.MIN_VALUE;
            dp[i][2] = Long.MIN_VALUE;
            if (i >= 2) {
                for (int j = 0; j < 3; j++) {

                    if (dp[i - 2][j] != Long.MIN_VALUE) {
                        long sum = dp[i - 2][j] + array[i];
                        if (dp[i][(int) (((sum % 3) + 3)%3)] == Long.MIN_VALUE ||
                            dp[i][(int) (((sum % 3) + 3)%3)] < sum){
                            dp[i][(int) (((sum % 3) + 3)%3)] = sum;
                        }
                    }

                }
            }
            if (i >= 5) {
                for (int j = 0; j < 3; j++) {

                    if (dp[i - 5][j] != Long.MIN_VALUE) {
                        long sum = dp[i - 5][j] + array[i];
                        if (dp[i][(int) (((sum % 3) + 3)%3)] == Long.MIN_VALUE ||
                                dp[i][(int) (((sum % 3) + 3)%3)] < sum){
                            dp[i][(int) (((sum % 3) + 3)%3)] = sum;
                        }
                    }

                }
            }
        }

        return dp[N-1][0] != Long.MIN_VALUE ? dp[N-1][0] : -2023;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));

        N = Integer.parseInt(sc.readLine());
        fillArray(sc, N);
        out.println(getResult());
        out.close();
    }
}