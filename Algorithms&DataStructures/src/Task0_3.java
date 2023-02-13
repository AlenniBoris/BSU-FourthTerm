import java.math.BigInteger;
import java.util.Scanner;

public class Task0_3 {
    static BigInteger fact(BigInteger n){
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return BigInteger.valueOf(1);
        }else {
            return (n.multiply(fact(n.subtract(BigInteger.ONE))));
        }
    }

    static long coef = (long)(10e9 + 7);

    static BigInteger equation(BigInteger numberN, BigInteger numberK){
        return  fact(numberN).divide( fact(numberK).multiply( fact(numberN.subtract(numberK) ) ) );
    }

    static BigInteger calculate(BigInteger N, BigInteger K){
        return first(N, K).add(second(N,K));
    }

    static BigInteger first(BigInteger N, BigInteger K){
        if (N.compareTo(BigInteger.ONE) <= 0 && K.compareTo(BigInteger.ONE) <= 0){
            return equation(N, K);
        }else{
            return calculate(N.subtract(BigInteger.ONE), K.subtract(BigInteger.ONE));
        }
    }

    static BigInteger second(BigInteger N, BigInteger K){
        if (N.compareTo(BigInteger.ONE) <= 0){
            return equation(N, K);
        }else{
            return calculate(N.subtract(BigInteger.ONE), K);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BigInteger N = sc.nextBigInteger();
        BigInteger K = sc.nextBigInteger();

        System.out.println(calculate(N, K).mod(BigInteger.valueOf(coef)));
    }
}
