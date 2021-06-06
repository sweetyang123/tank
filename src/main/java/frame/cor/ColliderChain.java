package frame.cor;

import frame.GameObject;
import frame.PropertyMgr;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
   List<Collider> colliders = new LinkedList<>();

   public ColliderChain() {
     String chains= PropertyMgr.getString("ColliderChain");
     if(chains!=null&&!chains.equals("")){
        for (String chain:chains.split(",")) {
           try {
              this.add((Collider) Class.forName(chain).newInstance());
           } catch (Exception e) {
              e.printStackTrace();
           }
        }
     }
//
//      this.add(new TankTankCollider());
//      this.add(new BulletTankCollider());
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
