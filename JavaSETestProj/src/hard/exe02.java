package hard;

/**
 * 2.设计方法实现：一个球从100米高度自由落下，每次落地后反跳回原高度的一半，再落下，求它在第10次落地时，共经过多少米？第10次反弹多高？
 */
public class exe02 {
    public static void main(String[] args) {
        System.out.println(sum(100,10));
        System.out.println(height(100,10));
    }

    public static double sum(double start,int time){
        if (time==0) return start;
        return sum(start,time-1)+height(start,time-1);
    }

    public static double height(double start,int time){
        if (time==0) return start;
        return 0.5*height(start,time-1);
    }
}
