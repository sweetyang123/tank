package frame;

import frame.decorator.GODecorator;
import frame.decorator.RectDecorator;

import java.awt.*;
import java.util.Random;

public class Tank extends GameObject{
    private int x,y;
    private int beforeX,beforeY;
    public static  final int height=ResourceImg.goodTankD.getWidth(),
            width=ResourceImg.goodTankD.getHeight();
    private Dir dir;
    private boolean moving=true;
    private boolean living=true;
    private static  final int SPEED=PropertyMgr.getInt("tankSpeed");
    private Random random=new Random();
    public Group group=Group.GOOD;


    //解决每次碰撞时创建：一开始就创建对象，再跟随tank对象，移动和初始化时赋值
    private  Rectangle tankRect=new Rectangle();
//    private  Rectangle tankRect=new Rectangle(this.x,this.y,width,height);

    public Tank(int x, int y,Group group,Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        tankRect.x=this.x;
        tankRect.y=this.y;
        tankRect.width=width;
        tankRect.height=height;
        GameModel.getInstance().add(this);
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
            GameModel.getInstance().remove(this);
//            tf.explodes.remove()
//            Explode explode = new Explode(this.x, this.y, tf);
//            explode.paint(g);
        }
        Color c =g.getColor();
        g.setColor(Color.RED);
        //根据按键改变坦克炮铜方向
        switch(dir){
            case LEFT : g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankL:ResourceImg.badTankL,x,y,null);
                break;
            case RIGHT: g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankR:ResourceImg.badTankR,x,y,null);
                break;
            case UP: g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankU:ResourceImg.badTankU,x,y,null);
                break;
            case DOWN: g.drawImage(this.group==Group.GOOD?ResourceImg.goodTankD:ResourceImg.badTankD,x,y,null);
                break;
        }
        //g.fillRect(x,y,width,height);
        g.setColor(c);
        move();
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
        if (x<0||y<0||x>GameModel.getInstance().GAME_WIDTH||y>GameModel.getInstance().GAME_HEIGHT)living=false;
        //坦克随机掉子弹
        if (this.group==Group.BAD&&random.nextInt(100)>95)this.fire();
        //坦克随机动
        if (this.group==Group.BAD&&random.nextInt(100)>95)this.randomDir();
        //边界检测
        boundsCheck();
        //移动时更新矩形位置
        tankRect.x=this.x;
        tankRect.y=this.y;
    }

    private void boundsCheck() {
        if (this.x<5)x=5;
        if (this.y<25)y=25;
        if (this.x>GameModel.getInstance().GAME_WIDTH-this.width-5)x=GameModel.getInstance().GAME_WIDTH-this.width-5;
        if (this.y>GameModel.getInstance().GAME_HEIGHT-this.height-5)y=GameModel.getInstance().GAME_HEIGHT-this.height-5;

    }

    private void randomDir() {
        //Dir.values()等到枚举中值的一个数组
        this.dir=Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bx=this.x+Tank.width/2-Bullet.width/2;
        int by=this.y+Tank.height/2-Bullet.height/2;
        if (group==Group.GOOD){
            Dir[] dirs = Dir.values();
            for (Dir dir1:dirs) {
                new Bullet(bx,by,this.group,dir1);
            }
        }else {
            GODecorator god=new RectDecorator(new Bullet(bx,by,this.group,dir));
            GameModel.getInstance().add(god);
//            new Bullet(bx,by,this.group,dir);
        }
    }

    public void die() {
        this.living=false;
        int ex=this.x+Tank.width/2-Explode.width/2;
        int ey=this.y+Tank.height/2-Explode.height/2;
        //碰撞时加入爆炸
        new Explode(ex,ey);
    }

    public Rectangle getTankRect() {
        return tankRect;
    }

    public void setTankRect(Rectangle tankRect) {
        this.tankRect = tankRect;
    }
}
