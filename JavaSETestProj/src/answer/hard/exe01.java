package answer.hard;

public class exe01 {
    public static void main(String[] args) {
        System.out.println("101-200之间的素数有：");
        for (int i = 101; i <= 200; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i);
            }
        }
    }
}
