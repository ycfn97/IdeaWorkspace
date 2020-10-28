package linkedlist.mylinkedlist;

import tree.mytree01.TreeNode;

/**
 * 链表类，实现添加节点元素和遍历功能
 */
public class Link {
    Node head;
    Node tail;
    int size;

    public int size(){
        return size;
    }

    /**
     * 添加节点，如果头节点为空也就是空链表时，头尾节点指向同一节点元素
     * 否则链表非空，将尾指针指向下一个节点地址，同时将尾结点指向最后一个元素。
     * @param value 节点元素内容
     */
    public void add(Object value){
        Node newNode=new Node();
        newNode.value=value;
        if (head==null){
            head=newNode;
            tail=newNode;
        }else {
            tail.next=newNode;
            tail=newNode;
        }
        size++;
    }

    /**
     * 遍历链表
     * 如果链表中还有下一个元素，那么就把当前节点的尾指针赋值给下一节点
     * 直到当前遍历节点的尾指针指向为空为止。
     */
    public void travel(){
        Node nextNode=head;
        while (nextNode!=null) {
            System.out.println(nextNode.value);
            nextNode=nextNode.next;
        }
    }

    public boolean remove(Object value){
        if (head.value.equals(value)){
            head=head.next;
            size--;
            return true;
        }
        Node pre=head;
        while (pre.next!=null){
            if (pre.next.value.equals(value)){
                if (pre.next==tail){
                    tail=pre;
                    size--;
                    return true;
                }
                pre.next=pre.next.next;
                size--;
                return true;
            }
            pre=pre.next;
        }
        return false;
    }
}
