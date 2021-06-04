package frame.abstractfactory;

import frame.Group;

import java.awt.*;

public abstract class BaseBullet {
    public Group group=Group.GOOD;
    public Rectangle bulletRect=new Rectangle();
    public abstract void paint(Graphics g);

    public Group getGroup(){
        return this.group;
    }

    public Rectangle getBulletRect(){
        return bulletRect;
    }

    public abstract void die();
}
