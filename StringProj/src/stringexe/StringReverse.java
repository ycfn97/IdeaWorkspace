package stringexe;

import org.junit.Test;
import java.util.Arrays;

public class StringReverse {
    //将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”

    /**
     * 思路一（复杂）首先利用substring方法找出需要翻转的部分，将要翻转部分转换为可变字符序列，利用reverse方法将这部分字符翻转，然后
     * 将原字符串和翻转的部分字符串变成字符数组，利用系统自带的arraycopy方法将反转后的部分字符数组复制到原字符数组的指定位置
     */
    @Test
    public void test01() {
        String string = "abcdefg";
        int begin = 2;
        int end = 6;

        String subStr=string.substring(2,6);
        System.out.println(subStr);
        StringBuilder stringBuilder=new StringBuilder(subStr);
        stringBuilder.reverse();
        System.out.println(stringBuilder);
        subStr=stringBuilder.toString();
        char[] chr=string.toCharArray();
        char[] sub=subStr.toCharArray();

        System.arraycopy(sub,0,chr,2,4);
        System.out.println(chr);
    }

    /**
     * 思路二（简单），利用数组，将字符串tochararray转换为数组，然后利用循环对指定部分的元素进行交换翻转
     */
    @Test
    public void test02(){
        String string = "abcdefg";
        int begin = 2;
        int end = 6;

        char[] chr=string.toCharArray();
        for (int i=0;i<=(6-1-2)/2;i++){
            char temp=chr[i+2];
            chr[i+2]=chr[6-i-1];
            chr[6-i-1]=temp;
        }
        System.out.println("result = "+String.valueOf(chr));
    }

    /**
     * 思路三（老师），切成3段, 只反转中间部分.将字符串分成三个子串，对中间部分进行翻转，然后进行拼接
     */
    @Test
    public void test03() {
        String string = "abcdefg";
        int begin = 2;
        int end = 6;

        // 切成3段, 只反转中间部分.
        String s1 = string.substring(0, begin);
        String s2 = string.substring(begin, end);
        String s3 = string.substring(end);

        char[] chars = s2.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char tmp = chars[i]; //
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = tmp;
        }
        String result = s1 + new String(chars) + s3;
        System.out.println("result = " + result);
    }
}
