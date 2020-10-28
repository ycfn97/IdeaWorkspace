package base;

public class exe19 {
    public static void main(String[] args) {
        int i = 0;
        change(i);
        i = i++;
        System.out.println("i = " + i);//0
    }
    public static void change(int i){
        i++;
    }
}
