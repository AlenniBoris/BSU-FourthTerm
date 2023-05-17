import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int[] delete(int[] array,int value)
    {
        array[value] = array[array.length - 1];

        return Arrays.copyOf(array, array.length - 1);
    }
    static int[] RandomAccess(int length){
        int[] result = new int[length];
        int[] unusedIndexes = new int[length - 1];
        for (int i = 0; i < length; i++) {
            result[i] = 0;
        }
        for (int i = 0; i < length - 1; i++) {
            unusedIndexes[i] = i+1;
        }
        int currentIndex = 0;
        for (int i = 0; i < length - 1; i++) {
            int r = (int)(Math.random() * unusedIndexes.length);
            int nextIndex = unusedIndexes[r];
            result[currentIndex] = nextIndex;
            currentIndex = nextIndex;
            unusedIndexes = delete(unusedIndexes, r);
        }
        return result;
    }

    static double Latency(int size, int iterations){
        int[] array = RandomAccess(size/4);
        int pointer = 0;
        Instant start = Instant.now();
        for (int i = 0; i < iterations; i++) {
            pointer = array[pointer];
        }
        Duration finish = Duration.between(start, Instant.now());

        return (double) finish.toNanos()/ (double)iterations;
    }

    public static void main(String[] args) throws IOException {
        PrintStream out = new PrintStream("src\\data.txt");
        for (double i = 5000; i <= 20000000; i *= 1.2) {
            out.println((int)i + " " + Latency((int)i, 100000000));
        }
        out.close();
    }
}