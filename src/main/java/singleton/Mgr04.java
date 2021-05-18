package singleton;

/**
 * 静态内部类
 *  * 由于外部类加载时，内部类是不会加载的，
 *  就达到了需要的时候再实例化，jvm保证线程安全的
 */
public class Mgr04 {
    private Mgr04(){}
    private static class a{
       private final static Mgr04 instance=new Mgr04() ;
    }
    public static Mgr04 getInstance(){
        return a.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->
                    System.out.println(getInstance().hashCode())
            ).start();
        }
    }
}
