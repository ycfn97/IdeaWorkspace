package factorymode.abstractfactory;

public class Apple implements Phone {
    public Apple() {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("make a apple phone!");
    }
}
