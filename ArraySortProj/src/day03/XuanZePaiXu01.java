package day03;

import java.util.Arrays;

public class XuanZePaiXu01 {
    public static void main(String[] args) {
        int array[]={32,54,11,43,12,45,87,12,3,4,-2,36};
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arraySort(array)));
    }

    public static int[] arraySort(int array[]){
        int index=0;
        for (int i=0;i<array.length;i++){
            int min=array[i];
            for (int j=i;j<array.length;j++){
                if (array[j]<=min){
                    min=array[j];
                    index=j;
                }
            }
            int temp=array[i];
            array[i]=array[index];
            array[index]=temp;
        }
        return array;
    }
}
