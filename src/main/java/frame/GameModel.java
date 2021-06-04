package frame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    Tank myTank = new Tank(100,50,Group.GOOD,Dir.DOWN,this);

    List<Bullet> bullets= new ArrayList<Bullet>();
    List<Tank> tanks= new ArrayList<Tank>();
    List<Explode> explodes=new ArrayList<Explode>();
    // Bullet bullet = new Bullet(150,150,20,20,Dir.DOWN);
    static final int GAME_WIDTH = PropertyMgr.getInt("GAME_WIDTH"),
            GAME_HEIGHT = PropertyMgr.getInt("GAME_HEIGHT");

    public GameModel() {
        for (int i = 0; i <PropertyMgr.getInt("tankCount") ; i++) {
            this.tanks.add(new Tank(150+i*60,70,Group.BAD,Dir.DOWN,this));
        }
    }

    public void paint(Graphics g){
        System.out.println("paint");
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
    public void setMainTankDir(boolean BL,boolean BR,boolean BU,boolean BD){
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
