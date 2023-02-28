package Rekkurentnie;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Task6 {

    static int[] fillArray(Scanner sc, int n){
        int[] array = new int[n];
        String[] a = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(a[i]);
        }
        return array;
    }

    static int getLength(int[] array, int n){
        if (array.length <= 1){
            return 1;
        }

        int[] indexes = new int[n+1];

        int left = 0;

        for (int i = 0; i < n; i++) {
            int begin = 1;
            int end = left;
            while(begin <= end){
                int mid = (int)Math.ceil((double) (begin + end) /2);
                if (array[indexes[mid]] < array[i]){
                    begin = mid + 1;
                }
                else
                    end = mid - 1;
            }

            if (begin > left){
                left = begin;
            }

            indexes[begin] = i;
        }

        return left;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(new File("input.txt"))));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("output.txt"))));


        int n = Integer.parseInt(sc.nextLine());
        int[] array = fillArray(sc, n);

        out.println(getLength(array, n));
        out.close();
    }
}
