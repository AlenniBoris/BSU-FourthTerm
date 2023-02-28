package Rekkurentnie;

import java.util.Scanner;

public class Task0_3 {
    static long fact(long n){
        long res = 1l;

        for (int i = 0; i <= n; i++) {
            res *= i;
        }

        return res;
    }

    static long binpow (long a,long n) {
        if (n == 0)
            return 1;
        if (n % 2 == 1)
            return binpow (a, n-1) * a;
        else {
            long b = binpow (a, n/2);
            return b * b;
        }
    }

    static long coef = (long) (10e9 + 7);

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long k = sc.nextLong();

        System.out.println(
                ((fact(n) % coef) * (binpow(fact(k)*fact(n-k), coef - 2) % coef)) % coef
        );
    }
}
