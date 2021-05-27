package frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TestFrame ts= new TestFrame();
        for (int i = 0; i <5 ; i++) {
            ts.tanks.add(new Tank(150+i*60,70,Group.BAD,Dir.DOWN,ts));
        }
     //ts.repaint();
        while (true){
            Thread.sleep(50);
            ts.repaint();
        }
    }
}
