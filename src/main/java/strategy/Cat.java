package strategy;

public class Cat {
    public  int food;

    public Cat(int food) {
        this.food = food;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "food=" + food +
                '}';
    }
}
