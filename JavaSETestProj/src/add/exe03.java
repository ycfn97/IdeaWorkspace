package add;

import java.util.Scanner;

/**
 * 3、假设日期段用两个6位长度的正整数表示，
 * 例如：(201401，201406)用来表示2014年1月到2014年6月，
 * 求两个日期段的重叠月份数。例如：输入：201401和201406，
 * 201403和201409，输出：4
 * 解释：重叠月份：3,4,5,6月共4个月
 */
public class exe03 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("please input:");
        int a=scanner.nextInt();
        System.out.println("and please input:");
        int b=scanner.nextInt();
        System.out.println("please input:");
        int c=scanner.nextInt();
        System.out.println("and please input:");
        int d=scanner.nextInt();
        if (a>d||b<c){
            System.out.println(0);
            return;
        }
        int s1=Integer.parseInt(new StringBuilder(String.valueOf(a)).substring(4));
        int s2=Integer.parseInt(new StringBuilder(String.valueOf(b)).substring(4));
        int s3=Integer.parseInt(new StringBuilder(String.valueOf(c)).substring(4));
        int s4=Integer.parseInt(new StringBuilder(String.valueOf(d)).substring(4));
        if (a<=d&&a<=c&&b>=c&&b<=d){
                System.out.println(s2-s3+(b/100-c/100)*12+1);

        }
        if (a<=d&&a<=c&&b>=c&&b>=d){
            System.out.println(s4-s3+1+(d/100-c/100)*12);
        }
        if (a>=c&&a<=d&&b>=c&&b>=d){
            System.out.println(s4-s1+1+(d/100-a/100)*12);
        }
        if (c<=a&&c<=b&&b<=d&&b>=a){
            System.out.println(s2-s1+1+(b/100-a/100)*12);
        }


    }
}
