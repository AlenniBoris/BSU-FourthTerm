package Graphs;

import java.io.*;

public class Task0_5 implements Runnable{

    static int n, m, q;
    static int[] mas;

    static int repr(int n){
        if (mas[n] < 0){
            return n;
        }
        mas[n] = repr(mas[n]);
        return mas[n];
    }

    static void union(int x, int y){
        int x1 = repr(x);
        int y1 = repr(y);
        if (x1 != y1){
            if (Math.abs(mas[x1]) < Math.abs(mas[y1])){
                int temp = x1;
                x1 = y1;
                y1 = temp;
            }
        }
        mas[x1] = (mas[y1] + mas[x1]);
        mas[y1] = x1;
    }

    static int checkComp(){
        int count = 1;
        int temp = mas[0];
        for (int i = 1; i < mas.length; i++) {
            if (temp != mas[i]){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException{
        new Thread(null, new Task0_5(), "", 64*1024*1024).start();
    }


    @Override
    public void run() {
        try {
            BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("output.txt")));


            String[] arr = sc.readLine().split(" ");
            n = Integer.parseInt(arr[0]);
            m = Integer.parseInt(arr[1]);
            q = Integer.parseInt(arr[2]);

            int[][] paths = new int[2][m+1];
            int[] destroys = new int[q];

            paths[0][0] = Integer.MIN_VALUE;
            paths[1][0] = Integer.MIN_VALUE;
            for (int i = 1; i <= m; i++) {
                String[] temp = sc.readLine().split(" ");
                paths[0][i] = Integer.parseInt(temp[0]);//from
                paths[1][i] = Integer.parseInt(temp[1]);//to
            }

            for (int i = 0; i < q; i++) {
                destroys[i] = Integer.parseInt(sc.readLine());
            }

            mas = new int[n];
            for (int i = 0; i < n; i++) {
                mas[i] = -1;
            }

            StringBuilder res = new StringBuilder();

            if (q > m){
                for (int i = 0; i < q; i++) {
                    out.print(0);
                }
                out.close();
                System.exit(0);
            }else{


                for (int i = q-1; i > -1; --i) {
                    int x = paths[0][destroys[i]];
                    int y = paths[1][destroys[i]];
                    union(x - 1, y - 1);
                    if (checkComp() != 1){
                        res.append("0");
                    }else res.append("1");
                }
            }

            out.print(res.reverse());
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
