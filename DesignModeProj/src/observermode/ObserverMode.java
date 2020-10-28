package observermode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2 编写程序，在main方法中创建两个线程。线程1每隔一定时间（200ms以内的随机时间）产生一个0-100之间的随机整数，打印后将该整数放到集合中；
 * 共产生100个整数，全部产生后，睡眠30秒；
 * 在线程2中，唤醒上述睡眠的线程1，并获取线程1中的集合并打印集合内容。
 */

/**
 * 测试类，创建两个线程并测试
 */
public class ObserverMode {
    public static void main(String[] args) {
        Thread thread1=new Thread(Thread1.getThread1());
        Thread thread2=new Thread(new Thread2(thread1,Thread1.getThread1()));

        thread1.start();
        thread2.start();
    }
}

/**
 * 线程2，观察线程，不断观察线程1的标记来判断线程1是否已经打印完随机数进入休眠状态，如是则打断线程1的睡眠
 */
class Thread2 implements Runnable{
    private Thread thread;
    private Thread1 thread1;

    public Thread2(Thread thread, Thread1 thread1) {
        this.thread = thread;
        this.thread1 = thread1;
    }

    @Override
    public void run() {
        for (;;){
            if (thread1.getFlag()){
                thread.interrupt();
                System.out.println(thread1.list);
                return;
            }
            /**
             * 因为flag用volatile修饰，所以观察者不用休眠
             */
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}

/**
 * 线程1，打印元素并保存进数组，然后进行休眠，若在此过程中休眠被打断，则输出被打断语句
 */
class Thread1 implements Runnable{
    private static Thread1 thread1;

    private Thread1() {
    }

    /**
     * 线程安全懒汉式
     * @return 单例线程对象
     */
    public static Thread1 getThread1() {
        if (thread1==null){
            synchronized (""){
                if (thread1==null){
                    thread1=new Thread1();
                }
            }
        }
        return thread1;
    }

    List list=new ArrayList();

    /**
     * flag用volatile修饰
     */
    private volatile Boolean flag=false;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        for (int i = 0; i <100; i++) {
            int random=(int)(Math.random()*100+1);
            int random1=(int)(Math.random()*200+1);
            list.add(random);
            System.out.println(random);
            try {
                Thread.sleep(random1);
            } catch (InterruptedException e) {
                System.out.println("little sleep interrupted");
            }
        }
        setFlag(true);
        System.out.println("go to long sleep");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            System.out.println("long sleep interrupted");
        }
    }
}
