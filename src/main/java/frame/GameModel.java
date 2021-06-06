package frame;

import frame.cor.BulletTankCollider;
import frame.cor.Collider;
import frame.cor.ColliderChain;
import frame.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    Tank myTank = new Tank(100,50,Group.GOOD,Dir.DOWN,this);
    // Bullet bullet = new Bullet(150,150,20,20,Dir.DOWN);
    static final int GAME_WIDTH = PropertyMgr.getInt("GAME_WIDTH"),
            GAME_HEIGHT = PropertyMgr.getInt("GAME_HEIGHT");

    List<GameObject> lists = new ArrayList<>();
    public GameModel() {
        lists.add(myTank);
        for (int i = 0; i <PropertyMgr.getInt("tankCount") ; i++) {
            lists.add(new Tank(80+i*100,70,Group.BAD,Dir.DOWN,this));
        }
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
//        g.drawString("子弹数量"+bullets.size(),30,50);
//        g.drawString("坦克数量"+tanks.size(),30,60);
//        g.drawString("爆炸数量"+explodes.size(),30,70);
        //将所有物体画出来，坦克，子弹，爆炸
        for (int i = 0; i <lists.size() ; i++) {
            lists.get(i).paint(g);
        }
        Collider collider = new ColliderChain();
//        Collider collider = new BulletTankCollider();
//        Collider collider1 = new TankTankCollider();
        //给每个子弹与坦克进行碰撞检测
        for (int i = 0; i <lists.size() ; i++) {
            for (int j = i+1; j <lists.size() ; j++) {
//                collider.collWith(lists.get(i),lists.get(j));
//                collider1.collWith(lists.get(i),lists.get(j));
                collider.collWith(lists.get(i),lists.get(j));
            }
        }
        g.setColor(c);
        myTank.paint(g);

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
