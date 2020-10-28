package tree.mytree;

public class Tree {
    private TreeNode root;

    /**
     * 添加元素，首先将元素装入树节点中，判断根节点是否为空，如果为空，直接插入，如果不为空，执行插入函数，将其插入到正确的位置
     * @param n 待插入元素
     */
    public void add(int n){
        TreeNode treeNode=new TreeNode();
        treeNode.value=n;

        if (root==null){
            root=treeNode;
        }else {
            insert(root,treeNode);
        }
    }

    /**
     * 由于二叉树的插入是一个递归过程，所以需要单独提取为一个插入函数，根据二叉树左小右大的排列规律，先从左边找，如果小于左边就继
     * 续遍历如果大于左边就向右遍历，直到找到空节点，然后将节点插入
     * @param target 开始节点
     * @param newNode 待插入节点
     */
    public void insert(TreeNode target,TreeNode newNode){
        if (newNode.value<target.value){
            if (target.left==null){
                target.left=newNode;
                return;
            }
            insert(target.left,newNode);
        }
        if (newNode.value>target.value){
            if (target.right==null){
                target.right=newNode;
                return;
            }
            insert(target.right,newNode);
        }
    }

    /**
     * 查看二叉树结构，如果开始节点为空，就直接返回，如果不为空，就向左边继续查看，直到左边为空，递归结束，接续执行下一行
     * 输出当前遍历到的节点，然后在向右遍历，直到右边遍历完，然后向上继续遍历
     * @param node 开始查看节点
     */
    public void view(TreeNode node){
        if (node==null){
            return;
        }
        view(node.left);
        System.out.println(node.value);
        view(node.right);
    }

    /**
     * 遍历函数，将查看二叉树函数开始节点指向根节点即可
     */
    public void travel(){
        view(root);
    }
}
