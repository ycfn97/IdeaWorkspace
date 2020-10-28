package answer.hard;

public class exe05 {
    public static void main(String[] args) {
        long sum = 0;
        for (int i = 1; i <= 20; i++) {
            sum += jieCheng(i);
        }
        System.out.println("sum = " + sum);
    }

    public static long jieCheng(int n){
        long temp = 1;
        for (int j = 1; j <=n; j++) {
            temp *= j;
        }
        return temp;
    }
}
