package factory;

public class SimpleFactory {
    public  Moveable create(String name){
        if (name.equals("car")){return new Car();}
        else if(name.equals("plane")){return new Plane();}
        //before processing
        return  null;
    }
    public  Plane createPlane(){
        return  new Plane();
    }
}
