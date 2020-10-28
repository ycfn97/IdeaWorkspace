package factorymode.factorymethod;

public class AppleFactory implements PhoneFactory {
    @Override
    public Phone makePhone() {
        return new Apple();
    }
}
