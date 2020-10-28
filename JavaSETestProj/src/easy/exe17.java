package easy;

import java.io.*;

/**
 * 17、请编写代码把一个GBK的文本文件内容读取后存储到一个UTF-8的文本文件中。
 */
public class exe17 {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader=null;
        OutputStreamWriter writer=null;
        try {
            reader=new InputStreamReader(new FileInputStream("GBK.txt"),"gbk");
            writer=new OutputStreamWriter(new FileOutputStream("UTF8.txt"),"utf-8");
            char[] chars=new char[10];
            int count=0;
            while ((count=reader.read(chars))!=-1){
                writer.write(chars,0,count);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            reader.close();
            writer.close();
        }
    }
}
