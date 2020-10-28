package easy;

/**
 * 11、请编写一个饿汉式单例设计模式
 */
public class exe11 {
    private exe11(){

    }
    private static final exe11 e=new exe11();

    public static exe11 getE() {
        return e;
    }
}

enum exe111{
    INSTANCE;
}
