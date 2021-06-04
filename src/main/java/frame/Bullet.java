package frame;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {


    private int x,y;
    public static final int width=ResourceImg.bulletD.getWidth(),
            height= ResourceImg.bulletD.getHeight();
    private Dir dir =Dir.DOWN;
    private static  final int SPEED=PropertyMgr.getInt("bulletSpeed");
    public boolean living=true;
    private GameModel gm=null;

    private Group group=Group.GOOD;
    private Rectangle bulletRect=new Rectangle();
//    private Rectangle bulletRect=new Rectangle(this.x,this.y,width,height);


    public Bullet(int x, int y,Group group,Dir dir,GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;
        //初始化子弹矩形
        bulletRect.x=this.x;
        bulletRect.y=this.y;
        bulletRect.width=width;
        bulletRect.height=height;
    }
    public void paint(Graphics g) {
        //子弹死了则移除
        if (!living)gm.bullets.remove(this);
        Color c= g.getColor();
        g.setColor(Color.GREEN);
        switch(dir){
            case LEFT : g.drawImage(ResourceImg.bulletL,x,y,null);
                break;
            case RIGHT: g.drawImage(ResourceImg.bulletR,x,y,null);
                break;
            case UP: g.drawImage(ResourceImg.bulletU,x,y,null);
                break;
            case DOWN: g.drawImage(ResourceImg.bulletD,x,y,null);
                break;
        }
        //g.fillOval(x,y,width,height);
        g.setColor(c);
        move();
    }
    private void move(){
        switch(dir){
            case LEFT : x-=SPEED;
                break;
            case RIGHT : x+=SPEED;
                break;
            case UP : y-=SPEED;
                break;
            case DOWN : y+=SPEED;
                break;
        }
        if (x<0||y<0||x>gm.GAME_WIDTH||y>gm.GAME_HEIGHT)living=false;

        bulletRect.x=this.x;
        bulletRect.y=this.y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void die() {
        living=false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getBulletRect() {
        return bulletRect;
    }

    public void setBulletRect(Rectangle bulletRect) {
        this.bulletRect = bulletRect;
    }
}
