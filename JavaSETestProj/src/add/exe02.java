package add;

/**
 * 2、1个字符串中可能包含a-z中的多个字符，字符也可能重复，
 * 例如：String data = “aabcexmkduyruieiopxzkkkkasdfjxjdsds”;
 * 写一个程序，对于给定一个这样的字符串求出字符串出现次数最多的那个字母以及出现的次数
 * （若次数最多的字母有多个，则全部求出）
 */
public class exe02 {
    public static void main(String[] args) {
        String data = "aabcexmkduyruieiopxzkkkkasdfjxjdsds";
        char[] ch=data.toCharArray();
        for (int i = 0; i <ch.length; i++) {
            int count=0;
            if (ch[i]==' ') continue;
            for (int j = i+1; j <ch.length; j++) {
                if (ch[j]==ch[i]){
                    count++;
                    ch[j]=' ';
                }
            }
            System.out.println(ch[i]+":"+count);
        }
    }
}
