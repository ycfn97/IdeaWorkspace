package day03;

import java.util.Arrays;

public class XuanZePaiXu {
    public static void main(String[] args) {
        int array[]={32,54,11,43,12,45,87,12,3,4,-2,36};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(selectionSort(array)));
    }

    public static int[] selectionSort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) //找到最小的数
                    minIndex = j; //将最小数的索引保存
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
