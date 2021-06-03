package frame.abstractfactory;

import frame.Dir;
import frame.Group;
import frame.TestFrame;

public  abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Group group, Dir dir, TestFrame tf);

    public abstract BaseBullet createBullet(int x, int y,Group group,Dir dir,TestFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TestFrame tf);
}
