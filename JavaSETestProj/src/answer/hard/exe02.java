package answer.hard;

public class exe02 {
    public static void main(String[] args) {
        double height = 100;
        double distance = 0;
        int count = 10;
        for (int i = 1; i <= count; i++) {
            distance += height;// 加落下的距离
            height = height / 2;// 弹起的高度 第i次弹起的高度
            if (i != count) {
                distance += height; // 加弹起的距离
            }
        }
        System.out.println("第" + count + "次落地时，经过了：" + distance + "米");
        System.out.println("第" + count + "次反弹的高度是：" + height + "米");
    }
}
