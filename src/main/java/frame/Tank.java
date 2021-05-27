package frame;

import java.awt.*;

public class Tank {
    private int x,y;
    public static  final int height=ResourceImg.tankD.getWidth(),
            width=ResourceImg.tankD.getHeight();
    private Dir dir;
    private boolean moving=false;
    private boolean living=true;
    private static  final int SPEED=20;
    private TestFrame  tf=null;

    public Tank(int x, int y,Dir dir,TestFrame  tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!living)tf.tanks.remove(this);
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
    }

    public void fire() {
        int bx=this.x+Tank.width/2-Bullet.width/2;
        int by=this.y+Tank.height/2-Bullet.height/2;
      tf.bullets.add(new Bullet(bx,by,dir,tf));
    }
    //将坦克和子弹转为矩形，对两个矩形进行碰撞检测
    public void collWith(Bullet bullet) {
        Rectangle tankRect = new Rectangle(this.x,this.y,width,height);
        Rectangle bulletRect = new Rectangle(bullet.getX(),bullet.getY(),bullet.width,bullet.height);
        if (tankRect.intersects(bulletRect)){
            //碰撞后子弹和坦克都死掉，从list里移除，并living属性为false
            this.die();
            bullet.die();
        }
    }

    private void die() {
        this.living=false;
    }
}
