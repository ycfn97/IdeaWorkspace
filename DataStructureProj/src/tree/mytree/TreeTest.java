package tree.mytree;

public class TreeTest {
    public static void main(String[] args) {
        Tree tree=new Tree();
        tree.add(1);
        tree.add(5);
        tree.add(15);
        tree.add(3);
        tree.add(7);
        tree.add(8);
        tree.add(4);

        tree.travel();
    }
}
