package factorymode.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {
        PhoneFactory miFactory=new MiFactory();
        PhoneFactory appleFactory=new AppleFactory();

        miFactory.makePhone();
        appleFactory.makePhone();
    }
}
