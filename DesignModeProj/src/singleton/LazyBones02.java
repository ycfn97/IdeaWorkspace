package singleton;

/**
 * 兼顾效率和线程安全的懒汉式
 */
public class LazyBones02 {
    private LazyBones02() {
    }

    private static volatile LazyBones02 lazyBones02=null;

    public static LazyBones02 getLazyBones02() {
//        return lazyBones02;
        if (lazyBones02==null){
            synchronized (LazyBones02.class){
                if (lazyBones02==null){
                    return new LazyBones02();
                }
            }
        }
        return lazyBones02;
    }
}
