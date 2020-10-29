package myarraysort;

import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[]=new int[10];
        for (int i=0;i<10;i++){
            arr[i]=(int)(Math.random()*100+1);
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("please input number to search:");
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        boolean result=binarySearch01(arr,0,arr.length-1,num);
        System.out.println(result?"find it!":"not found.");
    }

    /**
     * 递归法二分查找，首先判断数组是否为空，如果不为空，找到中间节点判断待查找元素与中间节点数字大小关系，
     * 并对开始或者结束节点进行调整进行下一次递归调用，直到中间节点等于待查找元素或者开始节点大于结束节点
     * 那么就说明没有找到，返回false，查找函数用到一个boolean标记，如果找到标记就会变成true
     * @param arr 已排好序的待查找数组
     * @param start 开始查找位置
     * @param end 结束查找位置
     * @param num 待查找元素
     * @return 是否找到
     */
    public static boolean binarySearch(int[] arr,int start,int end,int num){
        boolean flag=false;
        if (arr.length==0){
            return flag;
        }
        int mid=(start+end)/2;
        if (start>end){
            return false;
        }
        if (arr[mid]>num){
            end=mid-1;
            flag=binarySearch(arr,start,end,num);
        }
        if (arr[mid]<num){
            start=mid+1;
            flag=binarySearch(arr,start,end,num);
        }
        if (arr[mid]==num){
            flag=true;
        }
        return flag;
    }

    /**
     * for循环二分查找，思路与递归法相同
     * @param arr 已排好序的待查找数组
     * @param start 开始查找位置
     * @param end 结束查找位置
     * @param num 待查找元素
     * @return 是否找到
     */
    public static boolean binarySearch01(int[] arr,int start,int end,int num){
        boolean flag=false;
        for (;start<=end;){
            int middle=(start+end)/2;
            if (arr[middle]==num){
                flag=true;
                break;
            }else if (num>arr[middle]){
                start=middle+1;
            }else {
                end=middle-1;
            }
        }
        return flag;
    }

}
