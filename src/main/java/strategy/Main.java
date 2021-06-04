package strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Cat[] a = {new Cat(2),new Cat(1),new Cat(6)};
        Dog[] a = {new Dog(3,4),new Dog(5,1),new Dog(2,7)};
//        Sorter<Cat> sorter = new Sorter<>();
        Sorter<Dog> sorter = new Sorter<>();
        //实现Comparator接口为策略模式，可以在同一个对象上放多种策略
//        sorter.sort(a,new DogWeighComparator());
        sorter.sort(a,new DogHeightComparator());
        System.out.println(Arrays.toString(a));
    }
}
