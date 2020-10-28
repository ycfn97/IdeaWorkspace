package hard;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 5.设计方法实现：求1+2！+3！+...+20！的和
 */
public class exe05 {
    public static void main(String[] args) {
        System.out.println(bigsum(20));
    }

    public static long sum(int n){
        if (n==1){
            return 1;
        }
        return sum(n-1)*n;
    }

    public static long bigsum(int n){
        if (n==1){
            return 1;
        }
        return sum(n)+bigsum(n-1);
    }
}
