package frame;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TestFrame ts= new TestFrame();

     //ts.repaint();
        while (true){
            Thread.sleep(50);
            ts.repaint();
        }
    }
}
