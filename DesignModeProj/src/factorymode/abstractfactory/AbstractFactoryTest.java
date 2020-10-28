package factorymode.abstractfactory;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory applyFactory=new AppleFactory();
        AbstractFactory miFactory=new MiFactory();

        applyFactory.makePC();
        applyFactory.makePhone();

        miFactory.makePhone();
        miFactory.makePC();
    }
}
