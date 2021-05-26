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
    private static  final int SPEED=10;
    public boolean living=true;
    private TestFrame tf=null;


    public Bullet(int x, int y,  Dir dir,TestFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }
    public void paint(Graphics g) {
        //子弹死了则移除
        if (!living)tf.bullets.remove(this);
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
        if (x<0||y<0||x>tf.getWidth()||y>tf.getHeight())living=false;
    }
}
