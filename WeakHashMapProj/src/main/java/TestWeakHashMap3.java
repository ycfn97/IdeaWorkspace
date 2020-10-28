import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: WeakHashMapProj
 * Package: PACKAGE_NAME
 * ClassName: TestWeakHashMap3
 *
 * @author 18729 created on date: 2020/10/27 13:48
 * @version 1.0
 * @since JDK 1.8
 */
public class TestWeakHashMap3 {
    public static void main(String[] args){
        List<WeakHashMap<Integer[][], Integer[][]>> maps = new ArrayList<WeakHashMap<Integer[][],Integer[][]>>();
        int totalNum = 10000;
        for(int i=0;i<totalNum;i++){
            WeakHashMap<Integer[][], Integer[][]> w = new WeakHashMap<Integer[][], Integer[][]>();
            w.put(new Integer[1000][1000], new Integer[1000][1000]);
            maps.add(w);
            System.gc();//显示gc
            System.out.println(i);
        }
    }
}
