package easy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 16、请编写代码读取一个项目根目录下info.properties文件
 */
public class exe16 {
    public static void main(String[] args) {
        Properties properties=new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("info.properties"));
            String username=properties.getProperty("user");
            System.out.println(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
