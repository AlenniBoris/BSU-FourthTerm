package Graphs;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Task0_11 {
    static int n,m;

    static long[][] flow;
    static long[][] capacity;
    static int[] parent;
    static boolean[] visited;
    static void fill(BufferedReader sc) throws IOException{
        String[] str = sc.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        visited = new boolean[n];
        for (int i = 0; i < n; ++i){
            visited[i] = false;
        }

        flow = new long[n][n];

        capacity = new long[n][n];
        for (int i = 0; i < n; i++) {
            String[] arr = sc.readLine().split(" ");
            capacity[Integer.parseInt(arr[0]) - 1][Integer.parseInt(arr[1]) - 1] += Integer.parseInt(arr[2]);
        }

        parent = new int[n];
    }

    static long getResult(int s, int t) {
        while (true) {
            Queue<Integer> Q = new ArrayDeque<Integer>();
            Q.add(s);


            visited[s] = true;

            boolean check = false;
            int current;
            while (!Q.isEmpty()) {
                current = Q.peek();
                if (current == t) {
                    check = true;
                    break;
                }
                Q.remove();
                for (int i = 0; i < n; ++i) {
                    if (!visited[i] && capacity[current][i] > flow[current][i]) {
                        visited[i] = true;
                        Q.add(i);
                        parent[i] = current;
                    }
                }
            }
            if (!check)
                break;

            long temp = capacity[parent[t]][t] - flow[parent[t]][t];
            for (int i = t; i != s; i = parent[i])
                temp = Math.min(temp, (capacity[parent[i]][i] - flow[parent[i]][i]));

            for (int i = t; i != s; i = parent[i]) {
                flow[parent[i]][i] += temp;
                flow[i][parent[i]] -= temp;
            }
        }

        long result = 0;
        for (int i = 0; i < n; ++i)
            result += flow[s][i];
        return result;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(new BufferedOutputStream(System.out));

        fill(sc);
        sc.close();
        out.print(getResult(0, n-1));
        out.close();
    }
}
