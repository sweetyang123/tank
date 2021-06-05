package frame.cor;

import frame.GameObject;

//碰撞器，坦克与坦克，子弹与坦克碰撞
public interface Collider {
  void  collWith(GameObject o1,GameObject o2);
}
