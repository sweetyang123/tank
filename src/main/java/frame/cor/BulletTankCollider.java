package frame.cor;

import frame.Bullet;
import frame.GameObject;
import frame.Tank;

public class BulletTankCollider implements Collider{
    @Override
    public boolean collWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet){
            Tank tank = (Tank) o1;
            Bullet bullet = (Bullet) o2;
            //  分组相同，则不进行碰撞
            if (tank.group==bullet.getGroup()) return true;
            if (tank.getTankRect().intersects(bullet.getBulletRect())){
                //碰撞后子弹和坦克都死掉，从list里移除，并living属性为false
                tank.die();
                bullet.die();
                return  false;
            }
        }else if (o2 instanceof Tank && o1 instanceof Bullet) {
            collWith(o2,o1);
        }
        return true;
    }
}
