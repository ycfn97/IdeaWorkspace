import java.util.HashMap;
import java.util.Map;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: WeakHashMapProj
 * Package: PACKAGE_NAME
 * ClassName: TestHashMap
 * WeakHashMap当系统内存不足时，垃圾收集器会自动的清除没有在任何其他地方被引用的键值对，因此可以作为简单缓存表的解决方案。而HashMap就没有上述功能。
 * @author 18729 created on date: 2020/10/27 11:53
 * @version 1.0
 * @since JDK 1.8
 */
public class TestHashMap {
    public static void main(String[] args){
        Map<Integer,byte[]> hashMap = new HashMap<Integer,byte[]>();
        for(int i=0;i<100000;i++){
            hashMap.put(i, new byte[i]);
        }
    }
}
