package frame;



import frame.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.peer.ListPeer;
import java.util.ArrayList;
import java.util.List;

public class TestFrame  extends Frame {
    ImgFactory imgFactory = new ResourceImg();
    public GameFactory gf=new DefaultFactory();
//    public GameFactory gf=new RectFactory();
    BaseTank myTank=gf.createTank(100,50,Group.GOOD,Dir.DOWN,this);
   //不同包下需要加public
    public List<BaseBullet> bullets= new ArrayList<BaseBullet>();
    public List<BaseTank> tanks= new ArrayList<BaseTank>();
    public List<BaseExplode> explodes=new ArrayList<BaseExplode>();
   // Bullet bullet = new Bullet(150,150,20,20,Dir.DOWN);
    static final int GAME_WIDTH = PropertyMgr.getInt("GAME_WIDTH"),
           GAME_HEIGHT = PropertyMgr.getInt("GAME_HEIGHT");
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
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.drawString("子弹数量"+bullets.size(),30,50);
        g.drawString("坦克数量"+tanks.size(),30,60);
        g.drawString("爆炸数量"+explodes.size(),30,70);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i <bullets.size() ; i++) {
            bullets.get(i).paint(g);
        }
        for (int i = 0; i <tanks.size() ; i++) {
            tanks.get(i).paint(g);
        }
        //给每个子弹与坦克进行碰撞检测
        for (int i = 0; i <tanks.size() ; i++) {
            for (int j = 0; j <bullets.size() ; j++) {
                tanks.get(i).collWith(bullets.get(j));
            }
        }
        for (int i = 0; i <explodes.size() ; i++) {
            explodes.get(i).paint(g);
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
                case KeyEvent.VK_A:imgFactory.varImg("image/GoodTank2.png","image/tankU.gif","image/bulletU" +
                        ".png","image/e");
                    break;
                default: break;
            }
            setMainTankDir();
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
