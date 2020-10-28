package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.设计方法实现：判断101-200之间有多少个素数，并输出所有素数
 */
public class exe01 {
    public static void main(String[] args) {
        int count=0;
        List list=new ArrayList();
        fo:for (int i = 101; i <=200; i++) {
            for (int j = 2; j <=i/2; j++) {
                if (i%j==0){
                    continue fo;
                }
            }
            count++;
            list.add(i);
        }
        System.out.println("一共有"+count+"个素数");
        System.out.println(list);
    }
}
