package singleton;

/**
 * 枚举是没有构造方法的，可防止反序列化
 */
public enum Mgr05 {
    Instance;
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->
                    System.out.println(Instance.hashCode())
            ).start();
        }
    }
}
