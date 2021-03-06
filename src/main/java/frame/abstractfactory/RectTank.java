package frame.abstractfactory;

import frame.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    private int x,y;
    public static  final int height=ResourceImg.goodTankD.getWidth(),
            width=ResourceImg.goodTankD.getHeight();
    private Dir dir;
    private boolean moving=true;
    private boolean living=true;
    private static  final int SPEED=PropertyMgr.getInt("tankSpeed");
//    private TestFrame  tf=null;
    private Random random=new Random();
    private Group group=Group.GOOD;
    private FireStrategy fs;



    private  Rectangle tankRect=new Rectangle();

//    private  Rectangle tankRect=new Rectangle(this.x,this.y,width,height);

    public RectTank(int x, int y, Group group, Dir dir, TestFrame  tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        tankRect.x=this.x;
        tankRect.y=this.y;
        tankRect.width=width;
        tankRect.height=height;
        //初始化时选择发火策略
        try {
            if (this.group==Group.GOOD){
                fs= (FireStrategy) Class.forName(PropertyMgr.getString("fourFS")).newInstance();
            }
            else fs= new DefaultFireStrategy();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    @Override
    public void paint(Graphics g) {
        if (!this.living){
            tf.tanks.remove(this);
        }
        Color c =g.getColor();
        g.setColor(Color.RED);
        //根据按键改变坦克炮铜方向
        switch(dir){
            case LEFT : g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankL:ResourceImg.badTankL,x,y,null);
                break;
            case RIGHT: g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankR:ResourceImg.badTankR,x,y,null);
                break;
            case UP: g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankU:ResourceImg.badTankU,x,y,null);
                break;
            case DOWN: g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankD:ResourceImg.badTankD,x,y,null);
                break;
        }
        //g.fillRect(x,y,width,height);
        g.setColor(c);
        move();
    }


    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void move(){

        if (!moving)return;
        switch(dir){
            case LEFT : x-=SPEED;
                break;
            case RIGHT: x+=SPEED;
                break;
            case UP: y-=SPEED;
                break;
            case DOWN: y+=SPEED;
                break;
        }
        if (x<0||y<0||x>tf.getWidth()||y>tf.getHeight())living=false;
        //坦克随机掉子弹
        if (this.group==Group.BAD&&random.nextInt(100)>95)this.fire();
        //坦克随机动
        if (this.group==Group.BAD&&random.nextInt(100)>95)this.randomDir();
        //边界检测
        boundsCheck();
        //移动时更新矩形位置
        tankRect.x=this.x;
        tankRect.y=this.y;
    }

    private void boundsCheck() {
        if (this.x<5)x=5;
        if (this.y<25)y=25;
        if (this.x>this.tf.getWidth()-this.width-5)x=this.tf.getWidth()-this.width-5;
        if (this.y>this.tf.getHeight()-this.height-5)y=this.tf.getHeight()-this.height-5;

    }

    private void randomDir() {
        //Dir.values()等到枚举中值的一个数组
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }


    //将坦克和子弹转为矩形，对两个矩形进行碰撞检测
    @Override
    public void collWith(BaseBullet bullet) {
//        分组相同，则不进行碰撞
        if (this.group==bullet.getGroup()) return;
//        Rectangle tankRect = new Rectangle(this.x,this.y,width,height);
//        Rectangle bulletRect = new Rectangle(bullet.getX(),bullet.getY(),bullet.width,bullet.height);
        if (this.tankRect.intersects(bullet.getBulletRect())){
            //碰撞后子弹和坦克都死掉，从list里移除，并living属性为false
            this.die();
            bullet.die();
            //产生碰撞时加入爆炸或死时爆炸
           // tf.explodes.add(new Explode(this.x,this.y,tf));
        }
    }

    private void die() {
        this.living=false;
        int ex=this.x+RectTank.width/2-Explode.width/2;
        int ey=this.y+RectTank.height/2-Explode.height/2;
        //碰撞时加入爆炸
         tf.explodes.add(tf.gf.createExplode(ex,ey,tf));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public TestFrame getTf() {
        return tf;
    }
}
