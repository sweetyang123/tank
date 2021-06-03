package tank01;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bx=t.getX()+Tank.width/2-Bullet.width/2;
        int by=t.getY()+Tank.height/2-Bullet.height/2;
        new Bullet(bx,by,t.getGroup(),t.getDir(),t.getTf());
    }
}
