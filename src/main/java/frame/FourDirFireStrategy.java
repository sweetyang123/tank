package frame;

public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank t) {
        int bx=t.getX()+Tank.width/2-Bullet.width/2;
        int by=t.getY()+Tank.height/2-Bullet.height/2;
        Dir[] dirs = Dir.values();
        for (Dir vdir:dirs) {
            new Bullet(bx,by,t.getGroup(),vdir,t.getTf());
        }
    }
}
