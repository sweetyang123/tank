package frame.cor;

import frame.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
   List<Collider> colliders = new LinkedList<>();

   public ColliderChain() {
      this.add(new TankTankCollider());
      this.add(new BulletTankCollider());
   }

   public void  add(Collider collider){
   colliders.add(collider);
   }
   public boolean collWith(GameObject o1, GameObject o2){
      for (int i = 0; i < colliders.size(); i++) {
         colliders.get(i).collWith(o1,o2);
      }
      return true;
   }
}
