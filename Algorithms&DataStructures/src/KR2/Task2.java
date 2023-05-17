package KR2;

import java.io.*;
import java.util.Scanner;

public class Task2 {

    static int[] indexesArray;
    static int counter = 0;
    static boolean resultOfUnion = false;

    static int repr(int n){
        if (n == indexesArray[n]){
            return n;
        }
        indexesArray[n] = repr(indexesArray[n]);
        return indexesArray[n];
    }

    static boolean union(int x, int y){
        x = repr(x);
        y = repr(y);
        if (x != y){
            indexesArray[y] = x;
            resultOfUnion = true;
        }
        return resultOfUnion;
    }

    static void makeUnions(int[] valuesArray, int n, int m){
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (valuesArray[i*m + j] != 1){
                    if (j < m-1 && valuesArray[i*m + j + 1] == 0){
                        if (union(i*m + j, i*m + j + 1)){
                            --counter;
                        }
                    }
                    if (valuesArray[ ((i+1)*m + j)%(n*m) ] == 0){
                        if (union(i*m + j, ((i+1)*m + j)%(n*m))){
                            --counter;
                        }
                    }
                }
            }
        }
    }

    static int[] fillArrayForDSU(int n, int m){
        int[] array = new int[n*m];
        for (int i = 0; i < n*m; i++) {
            array[i] = i;
        }
        return array;
    }

    static int[] matrixFromFile(int n, int m, Scanner sc) throws IOException {
        int[] array = new int[n*m];
        for (int i = 0; i < n; ++i) {
            String[] str = sc.nextLine().split(" ");
            for (int j = 0; j < m; ++j) {
                    array[i*n + j] = Integer.parseInt(str[j]);
            }
        }
        return array;
    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new FileInputStream(new File("in.txt")));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("out.txt"))));

        int n = sc.nextInt();
        int m = sc.nextInt();


        int[] valuesArray = matrixFromFile(n, m, sc);


        indexesArray = fillArrayForDSU(n, m);

//        makeUnions(valuesArray, n, m);

        for (int i = 0; i < n*m; i++) {
            out.print(valuesArray[i] + " ");
        }
        out.println();
        for (int i = 0; i < n*m; i++) {
            out.print(indexesArray[i] + " ");
        }
        out.println();
//        out.println(counter);


        out.close();
    }
}