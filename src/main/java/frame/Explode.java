package frame;

import java.awt.*;

/**
 * 子弹
 */
public class Explode {
    private int x,y;
    public static final int width=ResourceImg.explodes[0].getWidth(),
            height= ResourceImg.explodes[0].getHeight();
    public boolean living=true;
    private TestFrame tf=null;
    int step=0;

    public Explode(int x, int y, TestFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    public void paint(Graphics g) {
        g.drawImage(ResourceImg.explodes[step++],x,y,null);
        if (step>=ResourceImg.explodes.length)step=0;

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

}
