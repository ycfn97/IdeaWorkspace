package base;

public class exe24 {
    static int x, y, z;

    static {
        int x = 5;
        x--;
    }

    static {
        x--;
    }

    public static void main(String[] args) {
        System.out.println("x=" + x);//-1
        z--;//0
        method();
        System.out.println("result:" + (z + y + ++z));//3
    }

    public static void method() {
        y = z++ + ++z;//0
    }
}
