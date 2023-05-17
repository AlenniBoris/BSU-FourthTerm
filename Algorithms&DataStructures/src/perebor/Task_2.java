package perebor;

import java.io.*;
import java.util.*;

public class Task_2 implements Runnable{
    static String[] str;
    static int n,m,k, record;
    static ArrayList<Integer> field, path;

    static ArrayList<ArrayList<Integer>> paths;

    static void fill(BufferedReader sc) throws IOException{
        str = sc.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        k = Integer.parseInt(str[2]);

        field = new ArrayList<>(n*m);
        for (int i = 0; i < n*m; i++) {
            field.add(0);
        }

        for (int i = 0; i < k; i++) {
            int temp = Integer.parseInt(sc.readLine());
            field.set(temp - 1, -1);
        }

        record = Integer.MAX_VALUE;

        path = new ArrayList<>();

        paths = new ArrayList<>();
    }

    static void getResult(){
        findPaths(field, path, 0);
    }

    static void printResult(PrintStream out){
        out.println(paths.size());
        for (ArrayList<Integer> integers : paths) {
            for (int j = 0; j < integers.size(); j++) {
                if (integers.get(j) == integers.get(integers.size() - 1)) {
                    out.println(integers.get(j) + 1);
                } else {
                    out.print(integers.get(j) + " ");
                }
            }
            out.println();
        }
    }

    static void findPaths(ArrayList<Integer> field, ArrayList<Integer> path, int start){
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = start; i < field.size(); i++) {
            if (field.get(i) != -1){
                queue.add(field.get(i));
            }
        }
        while(!queue.isEmpty()){
            int position = queue.remove();
            path.add(position);
            if (path.size() <= record){
                ArrayList<Integer> temp = doVisits(field, position);

                if (!temp.contains(0)){
                    if (path.size() > record){
                        path.remove(path.size() - 1);
                    } else if (path.size() == record) {
                        ArrayList<Integer> temp1 = new ArrayList<>(path);
                        record = temp1.size();
                        paths.add(temp1);
                        path.remove(path.size() - 1);
                    } else{
                        paths.clear();
                        ArrayList<Integer> temp1 = new ArrayList<>(path);
                        record = temp1.size();
                        paths.add(temp1);
                        path.remove(path.size() - 1);
                    }
                }
                else{
                    findPaths(temp, path, position+1);
                }
            }
            else {
                path.remove(path.size() - 1);
            }
            path.remove(path.size() - 1);
        }
    }

    static ArrayList<Integer> doVisits(ArrayList<Integer> list, int position){
        ArrayList<Integer> result = new ArrayList<>(list);

        int index = position;
        while((result.get(index) == 0 || result.get(index) == 1) && index >= 0){ //up
            result.set(index, 1);//visited
            index -= m;
            if (index < 0){
                break;
            }
        }

        index = position;
        while((result.get(index) == 0 || result.get(index) == 1) && index < n*m){ //down
            result.set(index, 1);//visited
            index += m;
            if (index >= n*m){
                break;
            }
        }

        index = position;
        while((result.get(index) == 0 || result.get(index) == 1)){ //left
            if (index % n == 0){
                result.set(index, 1);
                break;
            }
            result.set(index, 1);//visited
            --index;
        }

        index = position;
        while((result.get(index) == 0 || result.get(index) == 1)){ //right
            result.set(index, 1);//visited
            ++index;
            if (index % n == 0){
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException{
        new Thread(null,  new Task_2(), "task", 512*1024*1024).start();
    }

    @Override
    public void run() {
        try{
            BufferedReader sc = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("output.txt"))));

            fill(sc);
            sc.close();

            getResult();

            printResult(out);
            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
