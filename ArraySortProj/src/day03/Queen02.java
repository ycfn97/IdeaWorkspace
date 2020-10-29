package day03;

import java.util.Scanner;

public class Queen02 {
    public static int method = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input queen's number:");
        int num = scanner.nextInt();
        int queen[][] = new int[num][num];
        for (int i = 0; i < num; i++) {
            for (int n = 0; n < num; n++) {
                queen[i][n] = 0;
            }
        }
        search(queen, 0);
        System.out.println();
        System.out.println("total:" + method + " methods");
    }

    /**
     * 判断此位置是否符合皇后放置标准
     * @param queen:皇后
     * @param j:行坐标
     * @param k:列坐标
     * @return 是否符合，是true，否false
     */
    public static boolean judge(int queen[][], int j, int k) {
        for (int i = 0; i < k; i++) {
            if (queen[j][i] == 1) return false;
        }
        for (int i = 0; i < j; i++) {
            if (queen[i][k] == 1) return false;
        }
        for (int a = j - 1, b = k - 1; a >= 0 && b >= 0; a--, b--) {
            if (queen[a][b] == 1) return false;
        }
        for (int a = j - 1, b = k + 1; a >= 0 && b < queen.length; a--, b++) {
            if (queen[a][b] == 1) return false;
        }
        return true;
    }

    public static void search(int queen[][], int i) {
        for (int j = 0; j < queen.length; j++) {
            if (judge(queen, i, j)) {
                queen[i][j] = 1;
                search(queen, i + 1);
                queen[i][j] = 0;
            }
        }
        if (i >= queen.length) {
            method++;
            printArray(queen);
            System.out.println();
            return;
        }
    }

    public static void printArray(int queen[][]) {
        for (int i = 0; i < queen.length; i++) {
            for (int j = 0; j < queen.length; j++) {
                System.out.print(queen[i][j]);
            }
            System.out.println();
        }
    }
}