package base;

public class exe25 {
    public static void main(String[] args) {
        new A(new B());
    }
}

class A {
    public A() {
        System.out.println("A");//2
    }

    public A(B b) {
        this();
        System.out.println("AB");//3
    }
}

class B {
    public B() {
        System.out.println("B");//1
    }
}
