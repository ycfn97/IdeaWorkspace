import org.junit.Test;

public class ThreadTest {
    @Test
    public void test01(){
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
    }

    @Test
    public void test02(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
