package singleton;

/**
 * 单线程懒汉式
 */
public class LazyBones {
    private LazyBones() {
    }

    private static LazyBones lazyBones=null;

    public static LazyBones getLazyBones() {
//        return lazyBones;
        if (lazyBones==null){
            return new LazyBones();
        }
        return lazyBones;
    }
}
