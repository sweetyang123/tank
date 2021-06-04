package frame;



import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame  extends Frame {

    GameModel gm = new GameModel();

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
    public void paint(Graphics g) {
        gm.paint(g);
    }

    Image offScreenImage = null;
//解决闪烁
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(gm.GAME_WIDTH, gm.GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0,gm.GAME_WIDTH, gm.GAME_HEIGHT);
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
                case KeyEvent.VK_CONTROL:gm.myTank.fire();
                    break;
                default: break;
            }
            gm.setMainTankDir(BL,BR,BU,BD);
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
            gm.setMainTankDir(BL,BR,BU,BD);
        }

    }
}
