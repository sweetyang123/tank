package frame;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
//    Tank myTank = new Tank(100,50,Group.GOOD,Dir.DOWN,this);
    List<Bullet> bullets= new ArrayList<Bullet>();
    List<Tank> tanks= new ArrayList<Tank>();
    List<Explode> explodes=new ArrayList<Explode>();
    // Bullet bullet = new Bullet(150,150,20,20,Dir.DOWN);
    static final int GAME_WIDTH = PropertyMgr.getInt("GAME_WIDTH"),
            GAME_HEIGHT = PropertyMgr.getInt("GAME_HEIGHT");
}
