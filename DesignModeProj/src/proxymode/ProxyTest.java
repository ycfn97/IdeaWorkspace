package proxymode;

/**
 * 代理模式
 * 把代理对象当成被代理对象使用
 *
 * 代理对象和被代理对象之间通过接口连接
 *
 * 使用场景1
 * 使用者无法直接创建被代理对象
 * 使用者功能需要增强，但无法修改被代理类
 *
 * 面向切面编程
 */
public class ProxyTest {
    public static void main(String[] args) {
        Court court=new Lawyer();
        court.court();
    }
}

interface Court{
    void court();
}

class Person02 implements Court{
    @Override
    public void court() {
        System.out.println("张三欠我钱，我要张三还钱！");
    }
}

class Person01 implements Court{
    @Override
    public void court() {
        System.out.println("老王和我老婆有瓜葛，我要离婚！");
    }
}

class Lawyer implements Court{
    Court person=new Person02();

    @Override
    public void court() {
        System.out.println("我是律师");
        person.court();
        System.out.println("我会帮原告打官司");
    }
}


