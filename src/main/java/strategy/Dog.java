package strategy;

public class Dog {
    public int weigh;
    public int height;

    public Dog(int weigh, int height) {
        this.weigh = weigh;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weigh=" + weigh +
                ", height=" + height +
                '}';
    }
}
