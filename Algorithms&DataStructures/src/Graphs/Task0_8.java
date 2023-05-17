package Graphs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Task0_8 {
    static int[] bfs(int start, int N, int[][] matrix){
        Queue<Integer> queue = new LinkedList<>();

        boolean visited[] = new boolean[N];
        int[] order = new int[N];

        int index = 1;

        for (int i = 0; i < N; i++) {
            visited[i] = false;
            order[i] = 0;
        }

        visited[start] = true;
        queue.add(start);
        order[start] = index;
        ++index;

        for (int i = 0; i < N; i++) {
            if (queue.isEmpty()){
                if (!visited[i]){
                    queue.add(i);
                }
                visited[i] = true;
            }
            if (!queue.isEmpty()){
                while (!queue.isEmpty()){
                    int v = queue.remove();
                    order[i] = index;
                    ++index;
                    for (int j = 0; j < N; j++) {
                        if (matrix[i][j] == 1 && !visited[j]){
                            queue.add(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }

        return order;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("output.txt"))));

        int N = Integer.parseInt(sc.readLine());

        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] string = sc.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(string[j]);
            }
        }
        sc.close();

        int[] order = bfs(0, N, matrix);

        for (int i = 0; i < order.length; i++) {
            out.print(order[i]  +" ");
        }
        out.close();
    }
}
