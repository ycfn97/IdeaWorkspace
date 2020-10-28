package easy;

/**
 * 10、编写一个懒汉式单例设计模式
 */
public class exe10 {
    private exe10(){

    }

    private exe10 e;

    public exe10 getE() {
        if (e==null){
            synchronized (""){
                if (e==null){
                    e=new exe10();
                }
            }
        }
        return e;
    }
}
