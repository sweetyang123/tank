package tank01;

import java.io.IOException;
import java.util.Properties;


public class PropertyMgr {
    private PropertyMgr() {
    }
    //JVM在加载类的时候只加载一次，所以只有一个实例，JVM 保证线程安全
   private static class Property{
       private static final Properties prop=new Properties();
       private static final PropertyMgr instance=new PropertyMgr();
   }
    public static Properties getProp() {
        try {
            Property.prop.load(PropertyMgr.class.getResourceAsStream("/resources/resconfig"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Property.prop;
    }
    public static PropertyMgr getInstance() {
        return Property.instance;
    }

    public static int getInt(String key){
        return Integer.valueOf(getProp().getProperty(key));
    }

    public static String getString(String key){
        return getProp().getProperty(key)+"";
    }
    public static Object getValue(String key){
        return getProp().getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getInt("tankCount"));
        //测试每次拿到的对象是否为同一个
        for (int i = 0; i < 100; i++) {
            new Thread(()->
                    System.out.println(getProp().hashCode())
            ).start();
        }
    }
}
