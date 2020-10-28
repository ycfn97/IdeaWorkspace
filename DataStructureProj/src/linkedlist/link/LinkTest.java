package linkedlist.link;

class Link {

    private class Node {
        Object value; // 数据域
        Node next; // 指针域
        public Node(Object value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public void add(Object obj) {
        Node newNode = new Node(obj);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void travel() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }

    public int size() {
        /*
        int size = 0;
        Node tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }*/
        return size;
    }

    public boolean remove(Object obj) {
        if (head.value.equals(obj)) { // 先判断是否删除的是头
            head = head.next;
            size--;
            return true;
        }
        Node prev = head;
        while (prev.next != null) { // 用于检索要删除的目标结点的前一个
            if (prev.next.value.equals(obj)) { // 判断的是prev.下一个节点的值
                // 真正删除它
                if (prev.next == tail) { // 如果删除的是尾结点, 必须让尾变化
                    tail = prev;
                }
                prev.next = prev.next.next; // 把要删除目标结点的后面的结点的地址写入前一个结点中.
                size--;
                return true;
            }
            prev = prev.next; // 如果判断失败, 继续右移指针.
        }
        return false;
    }
}

public class LinkTest {

    public static void main(String[] args) {
        Link link = new Link();
        link.add("yy");
        link.add("abc");
        link.add("aa");
        link.add("aa");
        link.add("cc");
        link.add("qq");
        link.add("zz");
        link.add("pp");
        link.add("aa");
        link.add("xx");

        link.travel();

        System.out.println("********************");

        link.remove("qq");

        link.travel();
    }
}
