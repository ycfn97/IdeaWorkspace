package myarraysort;

import java.util.Arrays;

public class MaoPao{
    public static void main(String[] args) {
        int arr[]=new int[10];
        for (int i=0;i<10;i++){
            arr[i]=(int)(Math.random()*100+1);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("******************************");
        sort(arr);
        System.out.println(Arrays.toString(arr));


    }

    /**
     * 第一趟：冒泡次数
     * 第二趟：每一趟比较的次数
     * 思路，每次比较相邻两个元素大小，看是否需要交换位置，每趟将较大的元素放在最后面
     * @param arr 待排序数组
     */
    public static void sort(int[] arr){
        if (arr.length==0){
            return;
        }
        for (int i=0;i<arr.length-1;i++) {
            for (int j=0;j<arr.length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    int temp=arr[j+1];
                    arr[j+1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }

}

