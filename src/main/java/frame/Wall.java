package frame;

import java.awt.*;
import java.util.Random;

public class Wall extends GameObject{
    private int x,y;
    private int beforeX,beforeY;
    public static  final int height=100,
            width=10;
    private Dir dir;
    private boolean moving=true;
    private boolean living=true;
    private static  final int SPEED=PropertyMgr.getInt("tankSpeed");
    private GameModel  gm=null;
    private Random random=new Random();
    public Group group=Group.GOOD;


    //解决每次碰撞时创建：一开始就创建对象，再跟随tank对象，移动和初始化时赋值
    private  Rectangle wallRect=new Rectangle();
//    private  Rectangle tankRect=new Rectangle(this.x,this.y,width,height);

    public Wall(int x, int y, Group group, Dir dir, GameModel  gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        wallRect.x=this.x;
        wallRect.y=this.y;
        wallRect.width=width;
        wallRect.height=height;
    }
//    坦克之间碰撞则回退到之前的位置
    public void beforeStep() {
        this.x = this.beforeX;
        this.y = this.beforeY;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!this.living){
            gm.lists.remove(this);
//            tf.explodes.remove()
//            Explode explode = new Explode(this.x, this.y, tf);
//            explode.paint(g);
        }
        Color c =g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
        g.setColor(c);
       // move();
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void move(){
        this.beforeX=x;
        this.beforeY=y;
        if (!moving)return;
        switch(dir){
            case LEFT : x-=SPEED;
                break;
            case RIGHT: x+=SPEED;
                break;
            case UP: y-=SPEED;
                break;
            case DOWN: y+=SPEED;
                break;
        }
       // if (beforeStep(this))x=beforeX;y=beforeY;
        if (x<0||y<0||x>gm.GAME_WIDTH||y>gm.GAME_HEIGHT)living=false;
        //随机动
        if (random.nextInt(100)>95)this.randomDir();
        //边界检测
        boundsCheck();
        //移动时更新矩形位置
        wallRect.x=this.x;
        wallRect.y=this.y;
    }

    private void boundsCheck() {
        if (this.x<5)x=5;
        if (this.y<25)y=25;
        if (this.x>gm.GAME_WIDTH-this.width-5)x=gm.GAME_WIDTH-this.width-5;
        if (this.y>gm.GAME_HEIGHT-this.height-5)y=gm.GAME_HEIGHT-this.height-5;

    }

    private void randomDir() {
        //Dir.values()等到枚举中值的一个数组
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public void die() {
        this.living=false;
        int ex=this.x+ Wall.width/2-Explode.width/2;
        int ey=this.y+ Wall.height/2-Explode.height/2;
        //碰撞时加入爆炸
         gm.lists.add(new Explode(ex,ey,gm));
    }

    public Rectangle getWallRect() {
        return wallRect;
    }

    public void setWallRect(Rectangle wallRect) {
        this.wallRect = wallRect;
    }
}
