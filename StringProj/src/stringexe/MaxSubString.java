package stringexe;

import org.junit.Test;

/**
 * 选做 : 获取两个字符串中最大相同子串。比如：
 * str1 = "abcwerthelloyuiodef ";str2 = "cvhellobnm"
 * 提示：将短的那个串进行长度依次递减的子串与较长
 * 的串比较。
 */
public class MaxSubString {
    @Test
    public void test01() {
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";
        String sub = "";
        for (int i = 0; i < str2.length(); i++) {
            for (int j = 0; j < str2.length() - i; j++) {
                String sub01 = str2.substring(j, j + i + 1);
                if (str1.indexOf(sub01) == -1) {
                    continue;
                }
                sub = sub01;
            }
        }
        System.out.println(sub);
    }


    @Test
    public void test02() {
        /*获取两个字符串中最大相同子串。比如：
        str1 = "abcwerthelloyuiodef ";str2 = "cvhellobnm"
        提示：将短的那个串进行长度依次递减的子串与较长的串比较。*/

        String str1 = "abcwerthelloyuiodef";
        // 10 -> 9 (c-n,v-m) -> 8(c-b,v-n,h-m) -> 7(c-o,v-b,h-n,e-m) -> 6 -> 5
        String str2 = "cvhellobuiodenm";
        int ruler = str2.length(); // 一个长度, 用于控制从短串中取子串的长度.
        boolean flag = false;
        l1:
        while (ruler > 0) {
            int begin = 0; // 用于控制从短串中取子串的开始索引
            while (begin + ruler <= str2.length()) { // begin + ruler是短串中取的子串的结束索引
                String sub = str2.substring(begin, begin + ruler); // 从短串中取子串.
                if (str1.indexOf(sub) != -1) {
                    flag = true;
                    System.out.println(sub);
                }
                begin++;
            }
            if (flag) {
                break;
            }
            ruler--;
        }
        if (!flag) {
            System.out.println("没有最大相同子串");
        }
    }
}


