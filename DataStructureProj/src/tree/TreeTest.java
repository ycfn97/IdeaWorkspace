package tree;

class Tree {

    private class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    private TreeNode root; // 根引用

    public void add(int n) {
        TreeNode newNode = new TreeNode();
        newNode.value = n;
        if (root == null) { // 树为空
            root = newNode;
        } else {
            insert(root, newNode);
        }
    }

    public void insert(TreeNode target, TreeNode newNode) {
        if (newNode.value < target.value) { // 新结点比目标小, 向左走
            if (target.left == null) {
                target.left = newNode;
            } else {
                insert(target.left, newNode);
            }
        } else { // 向右走
            if (target.right == null) {
                target.right = newNode;
            } else {
                insert(target.right, newNode);
            }
        }
    }

    public void view(TreeNode node) {
        if (node == null) {
            return;
        }
        view(node.left);
        System.out.println(node.value);
        view(node.right);
    }

    public void travel() {
        view(root);
    }
}

public class TreeTest {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.add(20);
        tree.add(10);
        tree.add(50);
        tree.add(80);
        tree.add(5);
        tree.add(2);
        tree.add(9);

        tree.travel();

    }
}
