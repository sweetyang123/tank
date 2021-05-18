package factory;

/**
 * 简单工厂的扩展性不好
 */
public class Main {
    public static void main(String[] args) {
//        Car ca= new Car();
//        Car ca= new SimpleFactory().createCar();
//        ca.go();
//        Plane pa= new SimpleFactory().createPlane();
//        pa.go();
//        Moveable c= new SimpleFactory().createPlane();
        Moveable c= new SimpleFactory().create("car");
        Moveable p= new SimpleFactory().create("plane");
        p.go();
        c.go();
    }
}
