package frame;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    public static  final int height=ResourceImg.tankD.getWidth(),
            width=ResourceImg.tankD.getHeight();
    private Dir dir;
    private boolean moving=true;
    private boolean living=true;
    private static  final int SPEED=2;
    private TestFrame  tf=null;
    private Random random=new Random();
    private Group group=Group.GOOD;

    public Tank(int x, int y,Group group,Dir dir,TestFrame  tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!this.living){
            tf.tanks.remove(this);
//            tf.explodes.remove()
//            Explode explode = new Explode(this.x, this.y, tf);
//            explode.paint(g);
        }
        Color c =g.getColor();
        g.setColor(Color.RED);
        //根据按键改变坦克炮铜方向
        switch(dir){
            case LEFT : g.drawImage(ResourceImg.tankL,x,y,null);
                break;
            case RIGHT: g.drawImage(ResourceImg.tankR,x,y,null);
                break;
            case UP: g.drawImage(ResourceImg.tankU,x,y,null);
                break;
            case DOWN: g.drawImage(ResourceImg.tankD,x,y,null);
                break;
        }
        //g.fillRect(x,y,width,height);
        g.setColor(c);
        move();
    }

    public boolean isMoving() {
        return moving;
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
    }

    private void randomDir() {
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx=this.x+Tank.width/2-Bullet.width/2;
        int by=this.y+Tank.height/2-Bullet.height/2;
      tf.bullets.add(new Bullet(bx,by,this.group,dir,tf));
    }
    //将坦克和子弹转为矩形，对两个矩形进行碰撞检测
    public void collWith(Bullet bullet) {
//        分组相同，则不进行碰撞
        if (this.group==bullet.getGroup()) return;
        Rectangle tankRect = new Rectangle(this.x,this.y,width,height);
        Rectangle bulletRect = new Rectangle(bullet.getX(),bullet.getY(),bullet.width,bullet.height);
        if (tankRect.intersects(bulletRect)){
            //碰撞后子弹和坦克都死掉，从list里移除，并living属性为false
            this.die();
            bullet.die();
            //产生碰撞时加入爆炸
            tf.explodes.add(new Explode(this.x,this.y,tf));
        }
    }

    private void die() {
        this.living=false;
    }
}
