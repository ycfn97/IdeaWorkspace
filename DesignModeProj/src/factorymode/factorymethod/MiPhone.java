package factorymode.factorymethod;

public class MiPhone implements Phone {
    public MiPhone() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make a xiaomi phone!");
    }
}
