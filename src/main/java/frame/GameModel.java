package frame;

import frame.cor.Collider;
import frame.cor.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel{
    private static final GameModel  gm= new GameModel();
    static Tank myTank =null;
    static final int GAME_WIDTH = PropertyMgr.getInt("GAME_WIDTH"),
            GAME_HEIGHT = PropertyMgr.getInt("GAME_HEIGHT");

    List<GameObject> lists = new ArrayList<>();
    static {
        init();
    }
    public static GameModel getInstance(){
        return gm;
    }
    private static void init(){
       myTank=new Tank(100,50,Group.GOOD,Dir.DOWN);
        for (int i = 0; i <PropertyMgr.getInt("tankCount") ; i++) {
           new Tank(80+i*100,70,Group.BAD,Dir.DOWN);
        }
        for (int i = 0; i <PropertyMgr.getInt("wallCount") ; i++) {
            new Wall(80+i*130,230);
        }
    }
    private GameModel() {
    }
    public void add(GameObject go){
        lists.add(go);
    }
    public void remove(GameObject go){
        lists.remove(go);
    }
    public void paint(Graphics g){
        Color c = g.getColor();
        //将所有物体画出来，坦克，子弹，爆炸
        for (int i = 0; i <lists.size() ; i++) {
            lists.get(i).paint(g);
        }
        Collider collider = new ColliderChain();
        //给每个子弹与坦克进行碰撞检测
        for (int i = 0; i <lists.size() ; i++) {
            for (int j = i+1; j <lists.size() ; j++) {
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
    //保存当前快照
    public void save(){
        ObjectOutputStream oos=null;
        File file = new File("E:/code/mashibing/tank.data");
        try {
            oos=new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(lists);
            oos.writeObject(myTank);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    //读取之前的快照，恢复之前的mytank和lists
    public void load(){
        ObjectInputStream ois=null;
        try {
            ois=new ObjectInputStream(new FileInputStream("E:/code/mashibing/tank.data"));
            //先进先出
            lists=(List<GameObject>) ois.readObject();
            myTank=(Tank) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois!=null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
