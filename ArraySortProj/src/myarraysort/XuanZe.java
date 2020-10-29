package myarraysort;

import java.util.Arrays;

public class XuanZe {
    public static void main(String[] args) {
        int arr[]=new int[10];
        for (int i=0;i<10;i++){
            arr[i]=(int)(Math.random()*100+1);
            System.out.println(Arrays.toString(arr));
            System.out.println("******************************");

        }
    }

    public static void sort(int[] arr){
        for (int i=0;i<arr.length;i++){
            int minIndex=i;
            for (int j=i+1;j<arr.length;j++){
                if (arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }
            int temp=arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
    }
}
