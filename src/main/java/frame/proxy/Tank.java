package frame.proxy;

import sun.misc.ProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Tank implements Movable {
    public static void main(String[] args) {
        Tank tank=new Tank();
//        ProxyGenerator

        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");
            Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                    new Class[]{Movable.class},new LogMovable(tank));
            m.move1();
            m.move();
    }

    @Override
    public void move() {
        System.out.println("tank moving cccccc");
    }

    @Override
    public void move1() {
        System.out.println("cccccsssssss");
    }

}
class LogMovable implements InvocationHandler {
    Tank tank;

    public LogMovable(Tank tank) {
        this.tank = tank;
    }

    /**
     *
     * @param proxy 动态代理对象
     * @param method  在这里是Movable的move方法
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method====="+method.getName()+"===start");
        Object o=method.invoke(tank,args);
        System.out.println("method====="+method.getName()+"===end");
        return o;
    }
}
interface Movable{
    void move();
    void move1();
}