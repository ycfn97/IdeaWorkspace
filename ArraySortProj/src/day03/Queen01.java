package day03;

public class Queen01 {
    public static int[][] array = new int[8][8];    //初始化棋盘
    public static int map;        //八皇后可能解的个数

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        System.out.println("八皇后问题 : ");
        search(0);
        //System.out.println();
        System.out.println("八皇后一共有" + map + "个解");
    }

    public static void search(int i) {
        if (i > 7) {
            map++;
            print();
            return;        //return后会接着指向array[i][j]=0,然后继续for循环,寻找下一个合适的解
        }
        for (int j = 0; j < 8; j++) {
            if (check(i, j)) {
                array[i][j] = 1;
                search(i + 1);
                array[i][j] = 0;        //清零,以免回溯的时候出现脏数据,当这一行都没有合适结点时被调用
            }
        }
    }

    public static boolean check(int i, int j) {        //检查该结点是否符合条件
        for (int k = 0; k < i; k++) {        //检查与该结点在一列上的元素是否有不为1的结点,且只检查在改结点上方的行,因为下方的行全为0,无需检查
            if (array[k][j] == 1) {
                return false;
            }
        }
        for (int m = i - 1, n = j - 1; m >= 0 && n >= 0; m--, n--) {        //检查左对角线上的元素
            if (array[m][n] == 1) {
                return false;
            }
        }
        for (int m = i - 1, n = j + 1; m >= 0 && n <= 7; m--, n++) {        //检查右对角线上的元素
            if (array[m][n] == 1) {
                return false;
            }
        }
        return true;
    }

    public static void print() {        //打印符合条件结点的位置
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (array[i][j] == 1) {
                    System.out.print("[" + i + " ," + j + "]   ");
                }
            }
        }
        System.out.println();
    }
}
