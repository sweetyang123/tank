package tank01;

import java.awt.*;

/**
 * 子弹
 */
public class Explode {
    private int x,y;
    public static final int width=ResourceImg.explodes[0].getWidth(),
            height= ResourceImg.explodes[0].getHeight();
    private TestFrame tf=null;
    int step=0;
    public Explode(int x, int y, TestFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    public void paint(Graphics g) {
        g.drawImage(ResourceImg.explodes[step++],x,y,null);
        //爆炸的图片显示完后，则从将该爆炸从爆炸组中移除
        if (step>= ResourceImg.explodes.length)tf.explodes.remove(this);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



}
