package frame;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements Serializable {
    public int x,y;
    //加上static后，所有的物体叠在一起了
//    public static int x,y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();

}
