package singleton;

/**
 * 也称懒汉式
 *  * 虽然达到了按需初始化的目的，但却带来线程不安全的问题
 *  * 可以通过synchronized解决，但也带来效率下降
 */
public class Mgr03 {
    private static volatile Mgr03 instance ;
    private Mgr03(){}
    public static Mgr03 getInstance() {
        if (instance==null){
            //代码块里加锁，不可行
            synchronized (Mgr02.class) {
                //双重检查
                if (instance == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Mgr03();
                }
            }

        }
        return instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                public void run() {
//                    System.out.println(Mgr02.getInstance().hashCode());
//                }
//            }).start();
            new Thread(()->
                    System.out.println(Mgr03.getInstance().hashCode())
            ).start();
        }
    }
}
