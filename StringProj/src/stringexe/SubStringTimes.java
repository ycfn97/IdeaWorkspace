package stringexe;

import org.junit.Test;

public class SubStringTimes {
    /**
     * 获取一个字符串在另一个字符串中出现的次数。
     * 比如：获取"ab"在 "abkkcadkabkebfkabkskab"
     * 中出现的次数
     * 思路一，利用循环和计数器，每次求子串第一次出现的下标，找到将计数器加1，将下标加1求子串继续寻找子串第二次出现的下标，直到找不到子串为止
     */
    @Test
    public void test01() {
        String s1 = "abkkcadkabkebfkabkskab";
        String s2 = "ab";
        int count = 0;
        while (s1.indexOf(s2)!=-1) {
            s1=s1.substring(s1.indexOf(s2)+1);
            count++;
        }
        System.out.println("出现了"+count+"次");
    }

    /**
     *思路同上，找到字符串后，计数器加1，从当前下标加1继续找子串位置
     */
    @Test
    public void test02() {
        String s1 = "abkkcadkabkebfkabkskab";
        String s2 = "ab";
        int count = 0;
        int index = 0;
        while (true) {
            index = s1.indexOf(s2, index);
            //if (从长串中检索短串的下标时, 返回-1) {
            if (index == -1) {
                //结束这个过程
                break;
            }
            count++;
            index++;
        }
        System.out.println(count);
    }
}
