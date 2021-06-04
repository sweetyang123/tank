package frame.abstractfactory;

import frame.*;

import java.awt.*;

public abstract class BaseTank {
   public int x,y;
   public static int height,width;
   public Group group=Group.GOOD;
   public Dir dir=Dir.DOWN;
   public TestFrame  tf=null;


   public abstract void paint(Graphics g);

   public abstract void fire();

   public abstract void collWith(BaseBullet baseBullet);

   public abstract void setMoving(boolean b);

   public abstract void setDir(Dir left);
}
