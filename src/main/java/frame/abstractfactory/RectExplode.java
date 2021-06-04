package frame.abstractfactory;

import frame.ResourceImg;
import frame.TestFrame;

import java.awt.*;

/**
 * 子弹
 */
public class RectExplode extends BaseExplode {
    private int x,y;
    public static final int width=10,
            height= 10;
    private TestFrame tf=null;
    int step=0;
    public RectExplode(int x, int y, TestFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    public void paint(Graphics g) {
        Color c =g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillRect(x,y,width+step,height+step);
        g.setColor(c);
        step+=5;
        //爆炸的图片显示完后，则从将该爆炸从爆炸组中移除
        if (step>=40)tf.explodes.remove(this);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



}
