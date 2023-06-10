package perebor;

import java.io.*;
import java.util.*;

public class Task_2 implements Runnable{
    static String[] str;
    static int n,m,k, record, startIndex, free;
    static ArrayList<Integer> rooks, field, surrounded;

    static ArrayList<ArrayList<Integer>> combinations;

    static void fill(BufferedReader sc, PrintStream out) throws IOException{
        str = sc.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);

        free = n*m - k;

        if (free == 0){
            out.println(1);
            out.close();
            System.exit(0);
        }

        field = new ArrayList<>();
        for (int i = 0; i < n*m; i++) {
            field.add(0);
        }
        startIndex = 0;
        for (int i = 0; i < k; i++) {
            int temp = Integer.parseInt(sc.readLine());

            if (startIndex == temp){
                startIndex++;
            }

            field.set(temp - 1, -1);
        }

        lookForSurrounded(field);

        record = 50;

        combinations = new ArrayList<>();

        rooks = new ArrayList<>();
    }

    static void lookForSurrounded(ArrayList<Integer> field){
        surrounded = new ArrayList<>();
        for (int i = 0; i < n*m; i++) {
            int tempFree = 0;
            if (field.get(i) != -1){
                tempFree = countFreeCells(field, i, false);
            }
            if (tempFree == 1){
                surrounded.add(i);
            }
        }
        for (int i = 0; i < surrounded.size(); i++) {
            field.set(surrounded.get(i), -1);
        }
        free -= surrounded.size();
    }

    static void tempPrint(){
        System.out.println(field);
        System.out.println(surrounded);
        System.out.println("free cells = " + free);
        System.out.println("record = " + record);
        System.out.println("rooks = " + rooks);
        System.out.println("combinations = " + combinations);
        System.out.println();
    }

    static void printRes(PrintStream out){
        if (combinations.isEmpty()){
            out.println();
            out.print(1);
            for (int i = 0; i < surrounded.size(); i++) {
                if (i == surrounded.size() - 1){
                    out.print((surrounded.get(i) + 1));
                }
                else{
                    out.print((surrounded.get(i) + 1) + " ");
                }
            }
            System.exit(0);
        }
        if (!surrounded.isEmpty()){
            for (int i = 0; i < combinations.size(); i++) {
                for (int j = 0; j < surrounded.size(); j++) {
                    combinations.get(i).add(surrounded.get(j));
                }
                Collections.sort(combinations.get(i));
            }
        }

        out.println(combinations.size());
        record += surrounded.size();
        for (int i = 0; i < combinations.size(); i++) {
            for (int j = 0; j < record; j++) {
                if (j == record - 1){
                    out.print((combinations.get(i).get(j) + 1));
                }else{
                    out.print((combinations.get(i).get(j) + 1) + " ");
                }
            }
            out.println();
        }
    }

    static void getResult(){
        findCombinations(field, rooks, startIndex, free);
    }

    static void findCombinations(ArrayList<Integer> field, ArrayList<Integer> rooks, int index, int freeCells){
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = index; i < field.size(); i++) {
            if (field.get(i) != -1){
                queue.add(i);
            }
        }

        int countFree = 0;
        int cf = freeCells;

        while (!queue.isEmpty()){
            freeCells = cf;

            int pos = queue.remove();
            rooks.add(pos);

            if (rooks.size() <= record){
                ArrayList<Integer> tempList = new ArrayList<>(field);

                countFree = countFreeCells(tempList, pos, true);

                if (countFree == 0){
                    rooks.remove(rooks.size() - 1);
                }else{
                    freeCells -= countFree;

                    if (freeCells != 0){
                        if (rooks.size() == record){
                            freeCells += countFree;
                            rooks.remove(rooks.size() - 1);
                        }else{
                            if (!queue.isEmpty()){
                                findCombinations(tempList, rooks, pos + 1, freeCells);
                            }
                            else{
                                freeCells += countFree;
                                rooks.remove(rooks.size() - 1);
                            }
                        }
                    }
                    else{
                        if (rooks.size() < record){
                            freeCells += countFree;
                            combinations.clear();
                            ArrayList<Integer> temp = new ArrayList<>(rooks);
                            combinations.add(temp);
                            record = temp.size();
                            rooks.remove(rooks.size() - 1);
                        }
                        else if(rooks.size() == record){
                            freeCells += countFree;
                            ArrayList<Integer> temp = new ArrayList<>(rooks);
                            combinations.add(temp);
                            rooks.remove(rooks.size() - 1);
                        }
                        else{
                            freeCells += record;
                            rooks.remove(rooks.size() - 1);
                        }
                    }

                }
            }
            else{
                freeCells += countFree;
                rooks.remove(rooks.size() - 1);
            }
        }
        try{
            freeCells = cf;
            rooks.remove(rooks.size() - 1);
        }catch (IndexOutOfBoundsException e){}
    }

    static int countFreeCells(ArrayList<Integer> field, int index, boolean need){
        ArrayList<Integer> countList = new ArrayList<>(field);
        int numberFree = 0;

        for (int i = index; (countList.get(i) == 1 || countList.get(i) == 0);) { // down
            if (countList.get(i) == 0){
                numberFree++;
            }
            countList.set(i, 1);
            i+= n;
            if (i >= n*m){
                break;
            }
        }

        for (int i = index; (countList.get(i) == 1 || countList.get(i) == 0);) { // up
            if (countList.get(i) == 0){
                numberFree++;
            }
            countList.set(i, 1);
            i -= n;
            if (i < 0){
                break;
            }
        }

        for (int i = index; (countList.get(i) == 1 || countList.get(i) == 0) ; ) {  // right
            if (countList.get(i) == 0){
                numberFree++;
            }
            countList.set(i, 1);
            i++;
            if (i % m == 0){
                break;
            }
        }

        for (int i = index; (countList.get(i) == 1 || countList.get(i) == 0); ) { // left
            if (i % m == 0){
                if (countList.get(i) == 0){
                    numberFree++;
                }
                countList.set(i, 1);
                break;
            }
            if (countList.get(i) == 0){
                numberFree++;
            }
            countList.set(i, 1);
            i--;
        }

        if (need){
            Collections.copy(field, countList);
        }
        return numberFree;
    }
    public static void main(String[] args) throws IOException{
        new Thread(null,  new Task_2(), "task", 64*1024*1024).start();
    }

    @Override
    public void run() {
        try{
            BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("output.txt"))));

            fill(sc, out);
            sc.close();

            getResult();

            printRes(out);

            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}