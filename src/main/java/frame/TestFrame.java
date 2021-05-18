package frame;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame extends Frame {

    private  Dir dir=Dir.DOWN;
    Tank myTank = new Tank(100,100,50,50,dir);
    public TestFrame(){
        // Frame f =new Frame();
        setVisible(true);
        setResizable(false);//不能改变大小
        setSize(800,500);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new myKeyListener());
    }

    @Override
    public void paint(Graphics g){
        System.out.println("paint");
//        g.fillRect(myTank,y,80,80);
        myTank.paint(g);
    }

    class myKeyListener extends KeyAdapter {
        boolean BL=false;
        boolean BR=false;
        boolean BU=false;
        boolean BD=false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:BL=true;
                    break;
                case KeyEvent.VK_UP:BU=true;
                    break;
                case KeyEvent.VK_RIGHT:BR=true;
                    break;
                case KeyEvent.VK_DOWN:BD=true;
                    break;
                default: break;
            }
            setMainTankDir();
            System.out.println("key press");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:BL=false;
                    break;
                case KeyEvent.VK_UP:BU=false;
                    break;
                case KeyEvent.VK_RIGHT:BR=false;
                    break;
                case KeyEvent.VK_DOWN:BD=false;
                    break;
                default: break;
            }
            setMainTankDir();
            System.out.println("key release");
        }
        private void setMainTankDir(){
            if (BL)myTank.setDir(Dir.LEFT);
            if (BR)myTank.setDir(Dir.RIGHT);
            if (BU)myTank.setDir(Dir.UP);
            if (BD)myTank.setDir(Dir.DOWN);
        }
    }
}
