package frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TestFrame ts= new TestFrame();
        for (int i = 0; i <PropertyMgr.getInt("tankCount") ; i++) {
            ts.tanks.add(ts.gf.createTank(150+i*60,70,Group.BAD,Dir.DOWN,ts));
        }
     //ts.repaint();
        while (true){
            Thread.sleep(50);
            ts.repaint();
        }
    }
}
