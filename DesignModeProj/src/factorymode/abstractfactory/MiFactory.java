package factorymode.abstractfactory;

public class MiFactory implements AbstractFactory{
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }

    @Override
    public PC makePC() {
        return new MiPC();
    }
}
