package frame;

import java.awt.*;
import java.util.Random;

public class Wall extends GameObject{
    public static  final int height=100,
            width=10;


    //解决每次碰撞时创建：一开始就创建对象，再跟随tank对象，移动和初始化时赋值
    private  Rectangle wallRect=new Rectangle();

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;

        wallRect.x=this.x;
        wallRect.y=this.y;
        wallRect.width=width;
        wallRect.height=height;
        GameModel.getInstance().add(this);
    }
    public void paint(Graphics g) {
        Color c =g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Rectangle getWallRect() {
        return wallRect;
    }
}
