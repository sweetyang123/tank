package frame.abstractfactory;

import frame.*;

public class RectFactory extends GameFactory{
    public RectTank createTank(int x, int y, Group group, Dir dir, TestFrame tf){
        return  new RectTank(x,y,group,dir,tf);
    }
    public RectBullet createBullet(int x, int y,Group group,Dir dir,TestFrame tf){
        return  new RectBullet(x,y,group,dir,tf);
    }
    public RectExplode createExplode(int x, int y, TestFrame tf){
        return  new RectExplode(x,y,tf);
    }
}
