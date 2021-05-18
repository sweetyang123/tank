package frame;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TestFrame extends Frame {

    Tank myTank = new Tank(100,100,50,50,Dir.DOWN,this);
//    java.util.List
    List<Bullet> bullets= new ArrayList<Bullet>();
   // Bullet bullet = new Bullet(150,150,20,20,Dir.DOWN);
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
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
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.drawString("子弹数量"+bullets.size(),50,60);
        g.setColor(c);
        myTank.paint(g);
        for (Bullet b:bullets) {
            b.paint(g);
        }

    }
    Image offScreenImage = null;
//解决闪烁
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
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
                case KeyEvent.VK_CONTROL:myTank.fire();
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
            if (!BL&&!BR&&!BU&&!BD)myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (BL)myTank.setDir(Dir.LEFT);
                if (BR)myTank.setDir(Dir.RIGHT);
                if (BU)myTank.setDir(Dir.UP);
                if (BD)myTank.setDir(Dir.DOWN);

            }

        }
    }
}
