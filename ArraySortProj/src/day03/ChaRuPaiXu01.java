package day03;

import java.util.Arrays;

public class ChaRuPaiXu01 {
    public static void main(String[] args) {
        int array[]={32,54,11,43,12,45,87,12,3,4,-2,36};
        System.out.println(Arrays.toString(array));
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static int[] sort(int array[]){
        if (array.length<=1){
            return array;
        }
        for (int i=0;i<=array.length-1;i++){
            int preIndex=i;
            int index=i+1;
            for (int n=index;n>0&&preIndex>0&&n<12;n--,preIndex--){
                if (array[n]<array[preIndex]){
                    int temp=array[n];
                    array[n]=array[preIndex];
                    array[preIndex]=temp;
                }else break;
            }
        }
        return array;
    }
}
