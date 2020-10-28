package stringexe;

import org.junit.Test;

/**
 * 模拟一个trim方法，去除字符串两端的空格。
 */
public class SimulateTrim {
    public static void main(String[] args) {
        String str="   asdf sdf asdg sdgfasg,gasgi[;p  ,sdfa     ";
        System.out.println(str);
        SimulateTrim moNiTrim=new SimulateTrim();
        String str01=moNiTrim.simulateTrim(str);
        System.out.println(str01);
    }

    /**
     * 第一种思路，使用delecharat函数删除开头和末尾空格
     * @param str 待trim的字符串
     * @return trim后的字符串
     */
    public String simulateTrim(String str){
        StringBuilder strBuilder=new StringBuilder(str);
        while (strBuilder.charAt(0)==' '){
            strBuilder.deleteCharAt(0);
        }
        while (strBuilder.charAt(strBuilder.length()-1)==' '){
            strBuilder.deleteCharAt(strBuilder.length()-1);
        }
        return new String(strBuilder);
    }

    @Test
    public void Test(){
        String str="   asdf sdf asdg sdgfasg,gasgi[;p  ,sdfa     ";
        System.out.println(simulateTrim01(str));
    }

    /**
     * 第二种思路，substring取子串
     * @param str 待trim的字符串
     * @return trim后的字符串
     */
    public String simulateTrim01(String str){
        while (str.charAt(0)==' '){
            str=str.substring(1,str.length());
        }
        while (str.charAt(str.length()-1)==' '){
            str=str.substring(0,str.length()-1);
        }
        return str;
    }
}
