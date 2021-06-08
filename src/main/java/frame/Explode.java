package frame;

import java.awt.*;

/**
 * 子弹
 */
public class Explode extends GameObject{
    public static final int width=ResourceImg.explodes[0].getWidth(),
            height= ResourceImg.explodes[0].getHeight();
    int step=0;
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        GameModel.getInstance().add(this);
    }
    public void paint(Graphics g) {
        g.drawImage(ResourceImg.explodes[step++],x,y,null);
        //爆炸的图片显示完后，则从将该爆炸从爆炸组中移除
        if (step>=ResourceImg.explodes.length)GameModel.getInstance().remove(this);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
