package stringexe;

import org.junit.Test;

import java.util.Arrays;

public class SplitString {
    @Test
    public void test02() {
        String PATH = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin;C:\\Program Files\\Python38\\Scripts\\;C:\\Program Files\\Python38\\;C:\\Program Files (x86)\\NetSarang\\Xshell 6\\;C:\\Tomcat\\apache-tomcat-9.0.19-windows-x64\\apache-tomcat-9.0.19\\bin;C:\\Program Files\\Java\\jdk-12.0.2\\bin;C:\\Windows\\system32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Windows\\System32\\OpenSSH\\;C:\\Program Files (x86)\\NVIDIA Corporation\\PhysX\\Common;C:\\Users\\18729\\AppData\\Local\\Programs\\Python\\Python38\\Scripts\\;C:\\Users\\18729\\AppData\\Local\\Programs\\Python\\Python38\\;C:\\Users\\18729\\AppData\\Local\\Microsoft\\WindowsApps;C:\\Wind\\Wind.NET.Client\\WindNET\\bin\\;C:\\Program Files\\JetBrains\\PyCharm 2019.3.3\\bin;;;C:\\Program Files\\JetBrains\\IntelliJ IDEA 2019.3.4\\bin;";
        String[] pathStr = PATH.split(";");
        System.out.println(Arrays.toString(pathStr));
        System.out.println();
        for (int i = 0; i < pathStr.length; i++) {
            System.out.println(pathStr[i]);
        }
    }

    @Test
    public void test01() {
        String PATH = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin;C:\\Program Files\\Python38\\Scripts\\;C:\\Program Files\\Python38\\;C:\\Program Files (x86)\\NetSarang\\Xshell 6\\;C:\\Tomcat\\apache-tomcat-9.0.19-windows-x64\\apache-tomcat-9.0.19\\bin;C:\\Program Files\\Java\\jdk-12.0.2\\bin;C:\\Windows\\system32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Windows\\System32\\OpenSSH\\;C:\\Program Files (x86)\\NVIDIA Corporation\\PhysX\\Common;C:\\Users\\18729\\AppData\\Local\\Programs\\Python\\Python38\\Scripts\\;C:\\Users\\18729\\AppData\\Local\\Programs\\Python\\Python38\\;C:\\Users\\18729\\AppData\\Local\\Microsoft\\WindowsApps;C:\\Wind\\Wind.NET.Client\\WindNET\\bin\\;C:\\Program Files\\JetBrains\\PyCharm 2019.3.3\\bin;;;C:\\Program Files\\JetBrains\\IntelliJ IDEA 2019.3.4\\bin;";
        PATH = PATH.replaceAll(";", "\n");
        System.out.println(PATH);
    }
}
