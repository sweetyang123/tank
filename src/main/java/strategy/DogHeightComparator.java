package strategy;

public class DogHeightComparator implements Comparator<Dog>{
    @Override
    public int compare(Dog o1, Dog o2) {
        if (o1.height>o2.height)return 1;
        else if (o1.height<o2.height)return -1;
        return 0;
    }
}
