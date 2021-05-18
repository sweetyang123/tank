package frame;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame extends Frame {
    private int x=100;
    private int y=100;
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
        g.fillRect(x,y,80,80);
//        x+=50;
//        y+=50;
    }
//    class myKeyListener extends KeyAdapter {
//        @Override
//        public void keyPressed(KeyEvent e) {
//            int key=e.getKeyCode();
//            switch (key){
//                case KeyEvent.VK_LEFT:x-=50;
//                    break;
//                case KeyEvent.VK_UP:y-=50;
//                    break;
//                case KeyEvent.VK_RIGHT:x+=50;
//                    break;
//                case KeyEvent.VK_DOWN:y+=50;
//                    break;
//                default: break;
//            }
////            x+=60;
//            repaint();
//            System.out.println("key press");
//        }
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//            System.out.println("key release");
//        }
//    }

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
            if (BL)x-=50;
            if (BR)x+=50;
            if (BU)y-=50;
            if (BD)y+=50;
            repaint();
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
            System.out.println("key release");
        }
    }
}
