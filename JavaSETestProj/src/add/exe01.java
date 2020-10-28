package add;

/**
 * 1、编写代码完成如下功能
 * public static String replace(String text, String target, String replace){
 * ....
 * }
 * 示例：replace(“aabbccbb”, “bb”, “dd”);  结果：aadccdd
 * 注意：不能使用String及StringBuffer等类的replace等现成的替换API方法。
 */
public class exe01 {
    public static void main(String[] args) {
        System.out.println(replace("aabbccdd","bb","dd"));
    }

    public static String replace(String text,String target,String replace){
        StringBuilder s1=new StringBuilder(text);
        for (int i = 0; i <s1.length()-target.length(); i++) {
            if (s1.substring(i,i+target.length()).equals(target)){
                s1=new StringBuilder(s1.substring(0,i)+replace+s1.substring(i+target.length()));
            }
        }
        return new String(s1);
    }
}
