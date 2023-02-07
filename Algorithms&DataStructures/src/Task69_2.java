import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task69_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = Integer.parseInt(sc.nextLine());
        ArrayList<Integer> mosquitoes = new ArrayList<>();
        for(String el : sc.nextLine().split(" ")){
            mosquitoes.add(Integer.parseInt(el));
        }

        int sum = 0;
        String resString;
        ArrayList<Integer> indexes = new ArrayList<>();

        if (mosquitoes.size() % 2 == 0){
            for (int i = 0; i < mosquitoes.size(); i += 2) {
                sum += mosquitoes.get(i);
                indexes.add(i);
            }
        }
        else if (mosquitoes.size() % 3 == 0){
            for (int i = 0; i < mosquitoes.size(); i += 3) {
                sum += mosquitoes.get(i);
                indexes.add(i);
            }
        }
        else
            resString = "-1";

        resString = String.valueOf(sum);

        System.out.println(resString);
        System.out.println(indexes.toString().substring(1,indexes.size()-2).replaceAll(",", " "));
    }
}
