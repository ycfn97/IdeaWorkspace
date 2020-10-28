import java.util.Map;
import java.util.WeakHashMap;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: WeakHashMapProj
 * Package: PACKAGE_NAME
 * ClassName: TestWeakHashMap
 *
 * @author 18729 created on date: 2020/10/27 11:54
 * @version 1.0
 * @since JDK 1.8
 */
public class TestWeakHashMap {
    public static void main(String[] args){
        Map<Integer,byte[]> weakHashMap = new WeakHashMap<Integer,byte[]>();
        for(int i=0;i<100000;i++){
            weakHashMap.put(i, new byte[i]);
        }
    }
}
