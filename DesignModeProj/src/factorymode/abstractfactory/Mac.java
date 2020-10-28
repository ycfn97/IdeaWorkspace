package factorymode.abstractfactory;

public class Mac implements PC{
    public Mac() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make a Mac!");
    }
}
