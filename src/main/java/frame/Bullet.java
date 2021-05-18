package frame;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {
    private int x,y;
    private int width,height;
    private Dir dir =Dir.DOWN;
    private static  final int SPEED=10;
    private boolean moving=false;


    public Bullet(int x, int y, int width, int height, Dir dir) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dir = dir;
    }
    public void paint(Graphics g) {
        Color c= g.getColor();
        g.setColor(Color.GREEN);
        g.fillOval(x,y,width,height);
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
    }
}
