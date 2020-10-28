package linkedlist.mylinkedlist;

public class LinkTest {
    public static void main(String[] args) {
        Link link=new Link();
        link.add("aaa");
        link.add("bbb");
        link.add("ccc");
        link.add("ddd");
        link.add("eee");
        link.add("fff");
        link.travel();
        System.out.println("**********************");
        link.remove("ccc");
        link.remove("fff");
        link.remove("aaa");
        link.travel();
    }
}
