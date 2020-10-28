package factorymode.simplefactory;

public class PhoneFactoryTest {
    public static void main(String[] args) {
        PhoneFactory phoneFactory=new PhoneFactory();
        phoneFactory.makePhone("xiaomi");
        phoneFactory.makePhone("Apple");
        //test finish
    }
}
