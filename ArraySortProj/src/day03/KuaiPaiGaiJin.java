package day03;

public class KuaiPaiGaiJin {

    public static class QuicksortSup {

        public static void main(String[] args) {
            int[] arr = {4,1,4,4,4,5,2,2,2,2,2,7,5,6,4,4,4,4,4,0,9};
            int len = arr.length;

            //从小到大快速排序
            quickSort(arr, 0, len-1);

            for(int i=0; i<len; i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }

        private static void quickSort(int[] arr, int l, int r) {
            if(l >= r) return ; //只有一个元素或不合法时返回

            /*
             * 改进一：对于少量区间更换更好的排序方法
             */
            int M = 8;
            if(r - l < M) {
                InsertionSort(arr, l, r);//1.插入排序
                return ;
            }

            int i = l;
            int j = r;

            /**
             * 改进三：选取区间的三个值，排序后去中间值当基准值
             * （为了不影响原来算法的书写，所以把取得的中间值移动到最左端）
             */
            int mid = l + ((r - l) >> 1);
            if(arr[l] > arr[mid]) swap(arr, l, mid);
            if(arr[l] > arr[r]) swap(arr, l, r);
            if(arr[mid] > arr[r]) swap(arr, mid, r);

            swap(arr, l, mid);
            int key = arr[l]; //3.基准值

            /**
             * 改进二：对于与基准值相等的最后移到数组中间，不再参与递归
             */
            int lLen = l;//2.左区间与基准值相等的标志
            int rLen = r;//2.右区间与基准值相等的标志

            while(i < j) {
                while(i < j && arr[j] >= key) //从后开始往回找
                    j --;
                while(i < j && arr[i] <= key)
                    i ++;
                if(i < j)
                    swap(arr, i, j);

                //2.把与基准值相等的放到区间两端
                if(arr[i] == key) {
                    swap(arr, i, lLen);
                    lLen ++;
                }
                if(arr[j] == key) {
                    swap(arr, j, rLen);
                    rLen --;
                }
            }
            //与基准值交换
            swap(arr, i, l);

            //2.使区间两端与基准值相等的元素移动到基准值位置附近
            for(int u=l; u<lLen; u++) {
                swap(arr, u, --i);
            }
            for(int v=r; v>rLen; v--) {
                swap(arr, v, --j);
            }

            quickSort(arr, l, i-1);
            quickSort(arr, i+1, r);
        }

        private static void InsertionSort(int[] arr, int l, int r) {
            for(int i=l+1; i<=r; i++) { //要插入的元素下标
                for(int j=l; j<i; j++) {
                    if(arr[i] < arr[j]) {
                        swap(arr, i, j);
                    }
                }
            }
        }

        private static void swap(int[] arr, int i, int j) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }

    }
}
