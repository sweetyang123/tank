package frame.cor;

import frame.Bullet;
import frame.GameObject;
import frame.Wall;

public class BulletWallCollider implements Collider{
    @Override
    public boolean collWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            if (b.getBulletRect().intersects(w.getWallRect())){
                b.die();
            }
        }else  if (o2 instanceof Bullet && o1 instanceof Wall){
            collWith(o2,o1);
        }
        return false;
    }
}
