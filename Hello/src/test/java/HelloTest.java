import org.junit.Test;

public class HelloTest {
    @Test
    public void test01(){
        Hello hello = new Hello();
        String maven = hello.sayHello("Maven");
        System.out.println(maven);
    }
}
