package frame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tank {
    private int x,y;
    private int height,width;
    private Dir dir;
    private boolean moving=false;
    private static  final int SPEED=20;
    private TestFrame  tf=null;

    public Tank(int x, int y,int height,int width,Dir dir,TestFrame  tf) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
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
        Color c =g.getColor();
        g.setColor(Color.RED);
        //根据按键改变坦克炮铜方向
        BufferedImage image=ResourceImg.tankD;
        switch(dir){
            case LEFT : image=ResourceImg.tankL;
                break;
            case RIGHT: image=ResourceImg.tankR;
                break;
            case UP: image=ResourceImg.tankU;
                break;
            case DOWN: image=ResourceImg.tankD;
                break;
        }
        g.drawImage(image,x,y,null);

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
      tf.bullets.add(new Bullet(x,y,10,10,dir,tf));
        System.out.println("========="+dir);
    }
}
