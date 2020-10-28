package hard;

/**
 * 8.设计方法实现：输入整型数98765，输出是56789
 */
public class exe08 {
    public static void main(String[] args) {
        System.out.println(reverse(98765));
    }

    public static StringBuilder reverse(int n){
        StringBuilder stringBuilder=new StringBuilder(String.valueOf(n));
        stringBuilder.reverse();
        return stringBuilder;
    }
}
