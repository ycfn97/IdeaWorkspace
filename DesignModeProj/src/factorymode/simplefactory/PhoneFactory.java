package factorymode.simplefactory;

public class PhoneFactory {
    public Phone makePhone(String phoneType){
        if (phoneType.equalsIgnoreCase("xiaomi")) return new MiPhone();

        if (phoneType.equalsIgnoreCase("apple")) return new Apple();

        return null;
    }
}
