package day03;

import java.util.Arrays;

public class KuaiSuPaiXu01 {
    public static void main(String[] args) {
        int array[]={32,54,11,43,12,45,87,12,3,4,-2,36};
        System.out.println(Arrays.toString(array));
        quickSort(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 分区函数
     * @param array 待分区数组
     * @param start 分区开始位置
     * @param end 分区结束位置
     * @return 基准值
     */
    public static int partition(int array[], int start, int end){
        int standard=array[end];
        while(start<end){
            while (array[start]<=standard&&start<end){
                start++;
            }
            if (array[start]>standard){
                array[end]=array[start];
                end--;
            }
            while (array[end]>=standard&&start<end){
                end--;
            }
            if (array[end]<standard){
                array[start]=array[end];
                start++;
            }
        }
        array[start]=standard;
        return start;
    }

    /**
     * 递归排序
     * @param array 待排序数组
     * @param start 快排开始位置
     * @param end 快排结束位置
     */
    static void quickSort(int array[], int start, int end){
        if (start<end){
            int index=partition(array,start,end);
            quickSort(array,start,index-1);
            quickSort(array,index+1,end);
        }
    }
}
