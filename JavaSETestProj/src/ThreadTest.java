import org.junit.Test;
import java.util.concurrent.*;

public class ThreadTest {
    /**
     * 继承Thread类
     */
    @Test
    public void test01() {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running...");
            }
        }.start();
    }

    /**
     * 实现Runnable接口
     */
    @Test
    public void test02() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running...");
            }
        }).start();
    }

    /**
     * 使用Callable和Future创建线程
     * 步骤
     * 创建实现Callable接口的类myCallable
     * 以myCallable为参数创建FutureTask对象
     * 将FutureTask作为参数创建Thread对象
     * 调用线程对象的start()方法
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test03() throws InterruptedException, ExecutionException {
        FutureTask<String> fu = new FutureTask(new Callable<String>() {
            @Override
            public String call() {
                System.out.println(Thread.currentThread().getName() + " running...");
                return Thread.currentThread().getName() + " result returned.";
            }
        });
        new Thread(fu).start();
        Thread.sleep(1000);
        System.out.println(fu.get());
    }

    /**
     * 使用Executor框架创建线程池
     * Executors提供了一系列工厂方法用于创先线程池，返回的线程池都实现了ExecutorService接口。
     * 主要有newFixedThreadPool，newCachedThreadPool，newSingleThreadExecutor，newScheduledThreadPool，后续详细介绍这四种线程池
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running");
                }
            });
        }
    }
}
