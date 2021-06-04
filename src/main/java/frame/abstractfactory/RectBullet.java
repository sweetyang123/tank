package frame.abstractfactory;

import frame.*;

import java.awt.*;

/**
 * 子弹
 */
public class RectBullet extends BaseBullet {


    private int x,y;
    public static final int width=10,
            height= 10;
    private Dir dir =Dir.DOWN;
    private static  final int SPEED=PropertyMgr.getInt("bulletSpeed");
    public boolean living=true;
    private TestFrame tf=null;

//    private Group group=Group.GOOD;
//    private Rectangle bulletRect=new Rectangle();
//    private Rectangle bulletRect=new Rectangle(this.x,this.y,width,height);


    public RectBullet(int x, int y, Group group, Dir dir, TestFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
        //初始化子弹矩形
        bulletRect.x=this.x;
        bulletRect.y=this.y;
        bulletRect.width=width;
        bulletRect.height=height;

        tf.bullets.add(this);
    }
    public void paint(Graphics g) {
        //子弹死了则移除
        if (!living)tf.bullets.remove(this);
        Color c= g.getColor();
        g.setColor(Color.yellow);
        g.fillRect(x,y,width,height);
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
        if (x<0||y<0||x>tf.getWidth()||y>tf.getHeight())living=false;

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


}
