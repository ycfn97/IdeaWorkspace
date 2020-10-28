package base;

public class exe23 {
    int a;
    int b;

    public void f() {
        a = 0;
        b = 0;
        int[] c = {0};
        g(b, c);
        System.out.println(a + " " + b + " " + c[0]);//1 0 1
    }

    public void g(int b, int[] c) {
        a = 1;
        b = 1;
        c[0] = 1;
    }

    public static void main(String[] args) {
        exe23 t = new exe23();
        t.f();
    }
}
