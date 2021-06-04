package frame;

import frame.abstractfactory.BaseBullet;
import frame.abstractfactory.BaseTank;

public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(BaseTank t) {
        int bx=t.x+t.width/2-BaseBullet.width/2;
        int by=t.y+t.height/2-BaseBullet.height/2;
        t.tf.gf.createBullet(bx,by,t.group,t.dir,t.tf);
    }
}
