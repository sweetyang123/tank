package frame.cor;

import frame.GameObject;
import frame.Tank;

public class TankTankCollider implements Collider{
    @Override
    public boolean collWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank &&o2 instanceof Tank){
            Tank tank1= (Tank) o1;
            Tank tank2= (Tank) o2;
//            碰撞上各自后退一步，并消失，返回false
            if (tank1.getTankRect().intersects(tank2.getTankRect())){
                tank1.beforeStep();
                tank2.beforeStep();
                return false;
//                System.out.println(tank1.getTankRect()+"============="+tank2.getTankRect());
            }
        }
        return true;
    }
}
