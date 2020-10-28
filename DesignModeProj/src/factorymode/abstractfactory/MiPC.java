package factorymode.abstractfactory;

public class MiPC implements PC{
    public MiPC() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make a MiPC!");
    }
}
