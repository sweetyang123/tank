package frame.abstractfactory;

import frame.*;

public class DefaultFactory extends GameFactory{
    public Tank createTank(int x, int y, Group group, Dir dir, TestFrame tf){
        return  new Tank(x,y,group,dir,tf);
    }
    public Bullet createBullet(int x, int y,Group group,Dir dir,TestFrame tf){
        return  new Bullet(x,y,group,dir,tf);
    }
    public Explode createExplode(int x, int y, TestFrame tf){
        return  new Explode(x,y,tf);
    }
}
