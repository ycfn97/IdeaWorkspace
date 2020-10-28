package singleton;

/**
 * 考虑线程安全懒汉式
 */
public class LazyBones01 {
    private LazyBones01() {
    }

    private static volatile LazyBones01 lazyBones=null;

    public static LazyBones01 getLazyBones() {
//        return lazyBones;
        synchronized (LazyBones01.class){
            if (lazyBones==null){
                return new LazyBones01();
            }
        }
        return lazyBones;
    }
}
