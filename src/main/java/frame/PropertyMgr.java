package frame;

import java.io.IOException;
import java.util.Properties;

import static frame.PropertyMgr.Property.getProp;

public class PropertyMgr {
    private static PropertyMgr instance;
    private static Properties prop;

    private PropertyMgr() {
    }
    static class Property{
      public static Properties  getProp(){
          if (prop==null){
//              instance=new PropertyMgr();
              prop = new Properties();
              try {
                  prop.load(PropertyMgr.class.getResourceAsStream("/resources/resconfig"));
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
            return prop;
        }
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
