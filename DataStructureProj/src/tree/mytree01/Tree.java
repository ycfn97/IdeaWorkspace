package tree.mytree01;

public class Tree {
    private TreeNode root;

    public void insert(TreeNode target,TreeNode treeNode){
        if (treeNode.value<target.value){
            if (target.left==null){
                target.left=treeNode;
                return;
            }
            insert(target.left,treeNode);
        }
        if (treeNode.value>target.value){
            if (target.right==null){
                target.right=treeNode;
                return;
            }
            insert(target.right,treeNode);
        }
    }

    public void add(int n){
        TreeNode treeNode=new TreeNode();
        treeNode.value=n;
        if (root==null){
            root=treeNode;
            return;
        }
        insert(root, treeNode);
    }

    public void view(TreeNode target){
        if (target==null){
            return;
        }
        view(target.left);
        System.out.println(target.value);
        view(target.right);
    }

    public void travel(){
        view(root);
    }
}
