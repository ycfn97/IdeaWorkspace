package stringbuilderexe;

import org.junit.Test;

public class StringBuildexe01 {
    @Test
    public void test01(){
        String str01="sunqi";
        String str02="i like java";
        String str03="shangguigu";
        StringBuilder stringBuilder=new StringBuilder(str01);
        stringBuilder.insert(0,str02).insert(stringBuilder.length(),str03);
        System.out.println(stringBuilder);

    }
}
