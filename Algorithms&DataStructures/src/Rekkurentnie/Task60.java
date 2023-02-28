package Rekkurentnie;

import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Task60 {

    static int getResutl(int x, int y, int x1, int x2, int y1, int y2){
        int[][] field = new int[x][y];//это наш исходный прямоугольник

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (i == x1 - 1 && j == y1 - 1 || i == y1 - 1 && j == x1 - 1
                        || i == x2 - 1 && j == y2 - 1 || i == y2 - 1 && j == x2 - 1){
                    field[i][j] = 0; // это если у нас составляется полностью какой-то из прямоугольнико
                }
                else{
                    field[i][j] = (i + 1)*(j+1); // ставим в эту ячейку число , которое типо заполняется

                    int sum = 1;

                    for (int k = 0; k < i; ++k) { //цикла 2 тк надо учесть повороты
                        sum = field[k][j] + field[i - k - 1][j]; // мы перебираем все возможные арзрезы по вертикали и горизонтали
                        if (sum < field[i][j]){ // и так находим минимальную сумму остатков
                            field[i][j] = sum;
                        }
                    }

                    for (int k = 0; k < j; ++k) {
                        sum = field[i][k] + field[i][j - k - 1];
                        if (sum < field[i][j]){
                            field[i][j] = sum;
                        }
                    }
                }
            }
        }

        return field[x-1][y-1];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedInputStream(new FileInputStream(new File("input.txt"))));
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("output.txt"))));

        int x = sc.nextInt(), y = sc.nextInt(),
                x1 = sc.nextInt(), y1 = sc.nextInt(),
                x2 = sc.nextInt(), y2 = sc.nextInt();

        sc.close();

        out.print(getResutl(x,y,x1,x2,y1,y2));
        out.close();

    }
}
