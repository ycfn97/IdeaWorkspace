package base;

public class exe27 {
    public static void main(String[] args) {
        Base b1 = new Base();//base 100
        //sub 100
        Base b2 = new Sub();//base 70
    }
}
class Base{
    Base(){
        method(100);
    }
    public void method(int i){
        System.out.println("base : " + i);
    }
}
class Sub extends Base{
    Sub(){
        super.method(70);
    }
    public void method(int j){
        System.out.println("sub : " + j);
    }
}
