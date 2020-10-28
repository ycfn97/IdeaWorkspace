package hard;

/**
 * 设计方法实现：用100元钱买100支笔，其中钢笔3元/支，圆珠笔2元/支，铅笔0.5元/支，问钢笔、圆珠笔和铅笔可以各买多少支？
 */
public class exe03 {
    public static void main(String[] args) {
        int pen = 3, pencount;
        int point = 2, pointcount;
        double pencil = 0.5, pencilcount;
        double sum = 0;
        for (pencount = 0; pencount <= 100 / pen; pencount++) {
            for (pointcount = 0; pointcount <= 100 / point; pointcount++) {
                for (pencilcount = 0; pencilcount <= 100 / pencil; pencilcount++) {
                    sum = pencount * pen + pointcount * point + pencilcount * pencil;
                    if (sum == 100 && (pencilcount + pointcount + pencount) == 100) {
                        System.out.println("pencil:" + pencilcount + ",point:" + pointcount + ",pen:" + pencount);
                    }
                }
            }
        }
    }
}
