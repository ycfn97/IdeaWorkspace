package factorymode.abstractfactory;

public class AppleFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new Apple();
    }

    @Override
    public PC makePC() {
        return new Mac();
    }
}
