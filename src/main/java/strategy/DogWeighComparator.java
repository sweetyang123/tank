package strategy;

public class DogWeighComparator implements Comparator<Dog>{
    @Override
    public int compare(Dog o1, Dog o2) {
        if (o1.weigh>o2.weigh)return 1;
        else if (o1.weigh<o2.weigh)return -1;
        return 0;
    }
}
