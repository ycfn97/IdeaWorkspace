package easy;

/**
 * 18、用实现Runnable接口的方式，启动一个线程完成在线程中打印1-100的数字
 */
public class exe18 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <101; i++) {
                    System.out.println(i);
                }
            }
        }).start();
    }
}
