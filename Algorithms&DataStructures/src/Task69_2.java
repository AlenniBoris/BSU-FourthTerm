import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task69_2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<Integer> mosquitoes = new ArrayList<>();
        for(String el : sc.nextLine().split(" ")){
            mosquitoes.add(Integer.parseInt(el));
        }

        int sum = mosquitoes.get(0);
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 2; i <= n;) {
            sum += mosquitoes.get(i);
            if (mosquitoes.get(i+2) > mosquitoes.get(i+3)){
                indexes.add(i);
                i += 2;
            }else if (mosquitoes.get(i+2) < mosquitoes.get(i+3)){
                indexes.add(i);
                i += 3;
            }
        }

        if (n == 2){
            sum = -1;
            indexes.clear();
        }

        if (n == 1){
            indexes.add(0);
        }

        System.out.println(sum);
        System.out.println(indexes.toString().replaceAll("," , " ").replaceAll("[^0-9 ]", ""));
    }
}
