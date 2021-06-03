package frame;

import frame.abstractfactory.BaseTank;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(BaseTank t) {
        int bx=t.x+Tank.width/2-Bullet.width/2;
        int by=t.y+Tank.height/2-Bullet.height/2;
        Dir[] dirs = Dir.values();
        for (Dir vdir:dirs) {
           t.tf.gf.createBullet(bx,by,t.group,vdir,t.tf);
        }
    }
}
