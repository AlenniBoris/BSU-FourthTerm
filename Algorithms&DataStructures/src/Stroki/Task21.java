package Stroki;

import java.io.*;
public class Task21 implements Runnable{
    static int l,c;
    static String[] S;

    static void fillParams(BufferedReader sc) throws IOException{
        String[] sizes = sc.readLine().split(" ");
        l = Integer.parseInt(sizes[0]);
        c = Integer.parseInt(sizes[1]);
        S = sc.readLine().split("");
    }

    static int getLength(String[] array){
        int r = 0;
        for (String s : array) {
            if (s != null) {
                ++r;
            }
        }
        return r;
    }

    static String getResult(){
        if (l < 2*c){
            return "Epic fail";
        }

        String[] A = new String[c];
        String[] B = new String[c];
        int index = 0;
        int temp = index;

        for (int i = 0; i < l; ++i) {
            if (index <= c-1){
                String symbol = S[i];


                if (A[index] == null){
                    A[index] = symbol;
                    continue;
                }
                else{
                    if (!A[index].equals(symbol)){
                        B[c-1-index] = symbol;
                        ++index;
                        continue;
                    }
                    else {
                        if (temp + 1 <= c - 1){
                            A[temp + 1] = symbol;
                            ++temp;
                            continue;
                        }else continue;
                    }
                }
            }
            else continue;
        }

        if (getLength(A) + getLength(B) != 2*c){
            return "Epic fail";
        }

        String[] finalStr = new String[2*c];
        for (int i = 0; i < c; i++) {
            finalStr[i] = A[i];
            finalStr[i + c] = B[i];
        }

        StringBuilder result = new StringBuilder();
        for (String s : finalStr) {
            result.append(s);
        }

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Task21(), "", 64*1024*1024).start();
    }

    @Override
    public void run() {
        try{
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
            PrintStream out = new PrintStream(new BufferedOutputStream(System.out));

            fillParams(sc);
            sc.close();

            out.println(getResult());

            out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}