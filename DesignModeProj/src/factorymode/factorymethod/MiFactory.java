package factorymode.factorymethod;

public class MiFactory implements PhoneFactory {
    @Override
    public Phone makePhone() {
        return new MiPhone();
    }
}
