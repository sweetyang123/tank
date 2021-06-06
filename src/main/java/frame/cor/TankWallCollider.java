package frame.cor;

import frame.GameObject;
import frame.Tank;
import frame.Wall;

public class TankWallCollider implements Collider{
    @Override
    public boolean collWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank &&o2 instanceof Wall){
            Tank t= (Tank) o1;
            Wall w= (Wall) o2;
//            碰撞上tank后退一步，并消失，返回false
            if (t.getTankRect().intersects(w.getWallRect())){
                t.beforeStep();
                return false;
//                System.out.println(tank1.getTankRect()+"============="+tank2.getTankRect());
            }
        }
        return true;
    }
}
